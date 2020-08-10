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
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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

import tp.app.App;
import tp.controller.InsumoController;
import tp.controller.InsumoGeneralController;
import tp.controller.InsumoLiquidoController;
import tp.controller.MarcaController;
import tp.controller.Mensaje;
import tp.controller.Utilidades;
import tp.dao.InsumoDAO;
import tp.dao.InsumoLiquidoDAO;
import tp.dao.PlantaDAO;
import tp.dao.StockInsumoDAO;
import tp.dominio.Planta;
import tp.dominio.StockInsumo;
import tp.enumerados.Unidad;

public class PanelInsumos extends PanelPersonalizado {

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administraci�n de Insumos",SwingConstants.LEFT);
	
	private InsumoLiquidoTM tableModelLiquidos;
	private InsumoGeneralTM tableModelGenerales;
	private InsumoLiquidoController controllerIL = new InsumoLiquidoController();
	private InsumoGeneralController controllerIG = new InsumoGeneralController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel texto_descripcion = new JLabel("Descripcion:",SwingConstants.RIGHT);
	private JLabel texto_unidad = new JLabel("Unidad:",SwingConstants.RIGHT);
	private JLabel texto_costo = new JLabel("Costo Por Unidad:",SwingConstants.RIGHT);
	private JLabel texto_tipo = new JLabel("Ver Insumos:",SwingConstants.RIGHT);
	private JLabel texto_tipo2 = new JLabel("Tipo:",SwingConstants.RIGHT);
	private JLabel texto_densidad = new JLabel("Densidad(Kg./m3):",SwingConstants.RIGHT);
	private JLabel texto_peso = new JLabel("Peso(Kg.):",SwingConstants.RIGHT);
	private JLabel espacio = new JLabel("          ",SwingConstants.RIGHT);
	
	JComboBox<String> comboBox = new JComboBox<String>();
	JComboBox<String> comboBoxAgregarInsumo = new JComboBox<String>(Utilidades.enumToStringArray(Unidad.class));
	JComboBox<String> comboBoxTipo = new JComboBox<String>();
	JComboBox<String> comboBoxTipo2 = new JComboBox<String>();
	
	private JTextField campo_descripcion = new JTextField();
	private JTextField campo_costo = new JTextField();
	private JTextField dyp = new JTextField();
	
	private JButton boton_agregar = botonAgregar("Agregar Insumo");
	private JButton boton_eliminar = botonEliminar("Eliminar Insumo seleccionado");
	
	PlantaDAO plantaDao = new PlantaDAO();
	StockInsumoDAO sid = new StockInsumoDAO();
	InsumoDAO insumodao = new InsumoDAO();
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);
			
			int resultado = eliminarPopUp("�Eliminar insumo:"+identificador+"?");
			if(resultado == JOptionPane.YES_OPTION) {
				if(this.texto_densidad.isVisible()) {
				notificacionPopUp(controllerIL.delete(identificador));
				}else {
					notificacionPopUp(controllerIG.delete(identificador));
				}
				actualizarTabla();
				}
			}
	}
	private void cambiarTabla(String tipo) {
		if(tipo == "L�quidos") {
			tabla.setModel(this.tableModelLiquidos);
			texto_peso.setVisible(false);
			texto_densidad.setVisible(true);
			
		}else {
			tabla.setModel(this.tableModelGenerales);
			texto_peso.setVisible(true);
			texto_densidad.setVisible(false);
		}
	}
	
	public void actualizarTabla() {
		tableModelGenerales.fireTableDataChanged();
		tableModelGenerales.recargarTabla();
		tabla.repaint();
		//tabla.validate();
	}
	
	public PanelInsumos() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214));
		
		//TITULO
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(Color.decode("#dd1c1a"));
		this.comboBoxTipo.addItem("L�quidos");
		this.comboBoxTipo.addItem("Generales");
		
		//TABLA
		tableModelLiquidos = new InsumoLiquidoTM();
		tableModelGenerales = new InsumoGeneralTM();
		tabla = new JTable();
		tabla.setModel(tableModelLiquidos);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.PLAIN,12));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hac� doble clic para editar el campo o presion� Supr para eliminar");
	    this.texto_peso.setVisible(false);
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     // to detect doble click events
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
		               int column = target.getSelectedColumn(); // select a column
		               Object original = (Object)tabla.getValueAt(row, column);
		               Object nuevo = null;
				
		               switch(column) {
			            	  case 1:
			            		  	nuevo = ingresoPopUp("Ingres� otro valor para: "+original);	
						            if(nuevo == null) {
						            	return ; // en caso de que seleccione cancelar
						            }
						            if( texto_densidad.isVisible()) {
						            	notificacionPopUp(controllerIL.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)nuevo,Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4)));
						            }else {
						            	notificacionPopUp(controllerIG.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)nuevo,Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4)));
						            }
						            break;
				            	   
			            	  case 2: 
			            		  nuevo = ingresoComboPopUp("Ingres� otro valor para: "+original,Utilidades.enumToStringArray(Unidad.class));
			            		  try {
			            			  if( texto_densidad.isVisible()) {
			            				  notificacionPopUp(controllerIL.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)nuevo),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4)));
			            			  }else {
			            				  notificacionPopUp(controllerIG.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)nuevo),(Double)tabla.getValueAt(row, 3),(Double)tabla.getValueAt(row, 4))); 
			            			  }
			            		  }catch(Exception ex) {
			            			  return;
			            		  }  
			            		  break;  
			            	  case 3:
			            		  try {
			            			  nuevo = Double.parseDouble(ingresoPopUp("Ingres� otro valor para: "+original));
			            		  }catch(Exception ex) {
			            			  return;// en caso de que seleccione cancelar
			            		  }
			            		  if( texto_densidad.isVisible()) {
			            			  notificacionPopUp(controllerIL.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)nuevo,(Double)tabla.getValueAt(row, 4)));
			            		  }else {
			            			  notificacionPopUp(controllerIG.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)nuevo,(Double)tabla.getValueAt(row, 4)));
			            		  }
			            		  break;
			            	  case 4:
			            		  try {
			            			  nuevo = Double.parseDouble(ingresoPopUp("Ingres� otro valor para: "+original));
			            		  }catch(Exception ex) {
			            			  return;// en caso de que seleccione cancelar
			            		  }
			            		  if( texto_densidad.isVisible()) {
			            			  notificacionPopUp(controllerIL.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)nuevo));
			            		  }else {
			            			  notificacionPopUp(controllerIG.update((String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 0),(String)tabla.getValueAt(row, 1),Unidad.valueOf((String)tabla.getValueAt(row, 2)),(Double)tabla.getValueAt(row, 3),(Double)nuevo));
			            		  }
			            		  break;
		               }
		               actualizarTabla();
				}
			}});
		
		tabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE)
					intentarEliminar();
			}
		});
		
		scroll_pane = new JScrollPane(tabla);

		//BOTON ELIMINAR
		boton_eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				intentarEliminar();
			}
		});
		
		String estado = this.comboBoxTipo.getSelectedItem().toString();
		this.comboBoxTipo.addActionListener(e ->{
			cambiarTabla(this.comboBoxTipo.getSelectedItem().toString());
		});
		
		//BOTON AGREGAR
		
		List<Planta> lista = plantaDao.getAll();
		boton_agregar.setForeground(Color.WHITE);
		boton_agregar.setBackground(Color.BLUE);
		boton_agregar.addActionListener( e ->{	
			Object novo = this.comboBoxAgregarInsumo.getSelectedItem().toString();
			Mensaje m ;
			if( this.comboBoxTipo2.getSelectedItem().toString()== "L�quido") {
				m = controllerIL.add(campo_descripcion.getText().toString(),Unidad.valueOf((String)novo ),Double.parseDouble(campo_costo.getText()),Double.parseDouble(dyp.getText()));
				  }else {
					m =  controllerIG.add(campo_descripcion.getText().toString(),Unidad.valueOf((String)novo),Double.parseDouble(campo_costo.getText()),Double.parseDouble(dyp.getText()));
					   }
			notificacionPopUp(m);
			if(m.exito()) {
				//Le asigna un stock a cada planta
				actualizarTabla();
				for(Planta planta : lista) {
					sid.add(new StockInsumo(planta.getId_planta(),insumodao.getID(campo_descripcion.getText().toString()),0,0));
				}
				
				}
			
		});
	
	//PANEL1
		
		JPanel panel1 = crearPanelInterno("Editar/Eliminar");
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
	//PANEL2
		
		JPanel panel2 = crearPanelInterno("Agregar Insumo");
		
	
		this.comboBoxTipo2.addItem("L�quido");
		this.comboBoxTipo2.addItem("General");
		
		setearFuente(new JComponent[]{texto_descripcion,texto_unidad,texto_costo,texto_densidad,texto_peso,texto_tipo2,texto_tipo});

		
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_descripcion);
		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_unidad);
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo);
		colocar(2,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_densidad);
		colocar(2,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_peso);
		colocar(4,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_tipo2);
		colocar(1,0,1,1,0,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,campo_descripcion);
		colocar(1,1,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,this.comboBoxAgregarInsumo);
		colocar(3,0,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo);
		colocar(3,1,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,this.dyp);
		colocar(5,0,1,1,1,0,0,10,GridBagConstraints.HORIZONTAL,10,panel2,this.comboBoxTipo2);
		colocar(4,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,this.titulo);
		colocar(1,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,this,espacio);
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,this,texto_tipo);
		colocar(3,0,1,1,0,0,0,0 ,GridBagConstraints.NONE,GridBagConstraints.WEST,this,this.comboBoxTipo);
		colocar(0,1,4,1,1,1,0,0,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,4,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
	
	
	
}

