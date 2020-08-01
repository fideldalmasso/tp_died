package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import tp.controller.InsumoController;
import tp.controller.InsumoLiquidoController;
import tp.controller.MarcaController;
import tp.controller.Mensaje;
import tp.controller.Utilidades;
import tp.enumerados.Unidad;

public class PanelInsumos extends PanelPersonalizado {

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administración de Insumos",SwingConstants.LEFT);
	JComboBox<String> comboBoxTipo = new JComboBox<String>();
	private InsumoLiquidoTM tableModel;
	private InsumoLiquidoController controller = new InsumoLiquidoController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	JComboBox<String> comboBox = new JComboBox<String>();//Es el dropDown de modificar en la Jtable
	JComboBox<String> comboBoxAgregarInsumo = new JComboBox<String>(Utilidades.enumToStringArray(Unidad.class));//Es el dropDown de agregar Insumo
	private JLabel texto_descripcion = new JLabel("Descripcion:",SwingConstants.RIGHT);
	private JLabel texto_unidad = new JLabel("Unidad:",SwingConstants.RIGHT);
	private JLabel texto_costo = new JLabel("Costo Por Unidad:",SwingConstants.RIGHT);
	
	private JLabel texto_tipo = new JLabel("Ver Insumos:",SwingConstants.RIGHT);
	private JLabel espacio = new JLabel("          ",SwingConstants.RIGHT);
	private JTextField campo_id = new JTextField();
	private JTextField campo_descripcion = new JTextField();
	private JTextField campo_unidad = new JTextField();
	private JTextField campo_costo = new JTextField();
	private JTextField dyp = new JTextField();
	private JButton boton_agregar = botonAgregar("Agregar Insumo");
	private JButton boton_eliminar = botonEliminar("Eliminar Insumo seleccionado");
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);
			
			int resultado = eliminarPopUp("¿Eliminar insumo:"+identificador+"?");
			if(resultado == JOptionPane.YES_OPTION) {
				notificacionPopUp(controller.delete(identificador));
				actualizarTabla();
				}
			}
	}
	
	private void actualizarTabla() {
		tableModel.fireTableDataChanged();
		tableModel.recargarTabla();
		tabla.repaint();
		//tabla.validate();
	}
	
	public PanelInsumos() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(Color.WHITE);
		this.comboBoxTipo.addItem("Líquidos");
		this.comboBoxTipo.addItem("Generales");
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new InsumoLiquidoTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hacé doble clic para editar el campo o presioná Supr para eliminar");
	    
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     // to detect doble click events
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
		               int column = target.getSelectedColumn(); // select a column
		               //JOptionPane.showMessageDialog(null, tabla.getValueAt(row, column)); // get the value of a row and column.
		               Object original = (Object)tabla.getValueAt(row, column);
		               Object nuevo = null;
				
		               switch(column) {

			            	  case 1:
			            		  		nuevo = ingresoPopUp("Ingresá otro valor para: "+original);	
						            	 if(nuevo == null) {
								           	 return ; // en caso de que seleccione cancelar
								          }
					            		  notificacionPopUp(controller.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)nuevo,Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4)));
					            		  break;
				            	   
			            	  case 2: 
					            		 nuevo = ingresoComboPopUp("Ingresá otro valor para: "+original,Utilidades.enumToStringArray(Unidad.class));
					            		 try {
					            			 notificacionPopUp(controller.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)nuevo),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4)));
					            		 }
					            		 catch(Exception ex) {
					            			 return;
					            		 }  
						            	 break;  
			            	  case 3:
			            		  		try {
						            	  nuevo = Double.parseDouble(ingresoPopUp("Ingresá otro valor para: "+original)); 
			            		  		}
						            	catch(Exception ex) {
						            	 return;// en caso de que seleccione cancelar
						            	 }
				            		  notificacionPopUp(controller.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)nuevo,(Double)tabla.getValueAt(row, 4)));
				            		   
				            	   break;
				            	   
			            	  case 4:
		            		  		try {
					            	  nuevo = Double.parseDouble(ingresoPopUp("Ingresá otro valor para: "+original)); 
		            		  		}
					            	catch(Exception ex) {
					            	 return;// en caso de que seleccione cancelar
					            	 }
			            		  notificacionPopUp(controller.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)nuevo));
			            		   
			            	   break;
			            	  }
		            	  actualizarTabla();  
		               
		            }
			}
		});
		
		tabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE)
					intentarEliminar();
			}
		});
		
		scroll_pane = new JScrollPane(tabla);
		//tabla.setFillsViewportHeight(true);

	//BOTON ELIMINAR------------------------------------------------------------------------------------------------
		//boton_eliminar.setForeground(Color.WHITE);
		//boton_eliminar.setBackground(Color.RED);
		boton_eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				intentarEliminar();
			}
		});

		
	//CAMPO NOMBRE------------------------------------------------------------------------------------------------
//		campo_nombre.setToolTipText("Presioná Enter para agregar");
//		campo_nombre.addActionListener( e->{
//			Mensaje m = controller.add(campo_nombre.getText());
//			notificacionPopUp(m);
//			if(m.exito()) 
//				actualizarTabla();
//		});
		
	//BOTON AGREGAR------------------------------------------------------------------------------------------------
		boton_agregar.setForeground(Color.WHITE);
		boton_agregar.setBackground(Color.BLUE);
		boton_agregar.addActionListener( e ->
		{	
			Object novo = this.comboBoxAgregarInsumo.getSelectedItem();
			Mensaje m = controller.add(campo_descripcion.getText().toString(),Unidad.valueOf((String)novo),Double.parseDouble(campo_costo.getText()),Double.parseDouble(dyp.getText()));
			notificacionPopUp(m);
			if(m.exito()) { 
				actualizarTabla();
				}
			
		}
	);
	
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.YELLOW);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
	//PANEL2------------------------------------------------------------------------------------------------
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setOpaque(false);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.YELLOW);
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar Insumo", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white);
		panel2.setBorder(borde2);
		texto_descripcion.setForeground(Color.white);
		texto_unidad.setForeground(Color.white);
		texto_costo.setForeground(Color.white);
		
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo);
		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_unidad);
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_descripcion);
		
		colocar(1,0,1,1,0,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo);
		colocar(1,1,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,this.comboBoxAgregarInsumo);
		colocar(3,0,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,campo_descripcion);
		colocar(3,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		texto_tipo.setForeground(Color.white);
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,this.titulo);
		colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,this,espacio);
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,this,texto_tipo);
		colocar(3,0,1,1,0,0,0,10 ,GridBagConstraints.HORIZONTAL,10,this,this.comboBoxTipo);
		colocar(0,1,4,1,1,1,0,0,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,4,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
	
	
}

