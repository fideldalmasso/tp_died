package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
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

import tp.controller.CamionController;
import tp.controller.MarcaController;
import tp.controller.Mensaje;
import tp.controller.Utilidades;
import tp.dao.ModeloDAO;
import tp.dao.PlantaDAO;
import tp.dominio.Modelo;
import tp.dominio.Planta;
import tp.enumerados.Unidad;

public class PanelCamiones extends PanelPersonalizado {

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administraci�n de Camiones",SwingConstants.CENTER);
	
	private CamionTM tableModel;
	private CamionController controller = new CamionController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel texto_id_camion = new JLabel("Id camion:",SwingConstants.RIGHT);
	private JLabel texto_id_planta = new JLabel("Id planta:",SwingConstants.RIGHT);
	private JLabel texto_nombre_planta = new JLabel("Nombre:",SwingConstants.RIGHT);
	private JLabel texto_nombre_modelo = new JLabel("Modelo:",SwingConstants.RIGHT);
	private JLabel texto_marca = new JLabel("Marca:",SwingConstants.RIGHT);
	private JLabel texto_distancia = new JLabel("Distancia recorrida en km:",SwingConstants.RIGHT);
	private JLabel texto_costo_por_km = new JLabel("Costo por km:",SwingConstants.RIGHT);
	private JLabel texto_costo_por_hora = new JLabel("Costo por hora:",SwingConstants.RIGHT);
	private JLabel texto_fecha_de_compra = new JLabel("Fecha de compra:",SwingConstants.RIGHT);
	
	private JTextField campo_id_camion = new JTextField();
	private JComboBox<String> campo_id_planta = null;
	private JTextField campo_nombre_planta = new JTextField();
	private JComboBox<String> campo_nombre_modelo = null;
	private JTextField campo_marca = new JTextField();
	private JTextField campo_distancia = new JTextField();
	private JTextField campo_costo_por_km = new JTextField();
	private JTextField campo_costo_por_hora = new JTextField();
	private JTextField campo_fecha_de_compra = new JTextField();
	
//	private String []lista_plantas;
//	private String []lista_nombre_plantas;
//	private String []lista_modelos;
//	private String []lista_marcas;
	
	
	private JButton boton_agregar = botonAgregar("Agregar Camion");
	private JButton boton_eliminar = botonEliminar("Eliminar Camion seleccionado");
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);
			
			int resultado = eliminarPopUp("�Eliminar "+identificador+"?");
			if(resultado == JOptionPane.YES_OPTION) {
			//	notificacionPopUp(controller.delete(identificador));
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
	
	public PanelCamiones() {
		
		super();
		
		List<Planta> todasLasPlantas = new PlantaDAO().getAll();
		String [] lista_plantas = todasLasPlantas
							.stream()
							.map(p -> p.getId_planta().toString())
							.collect(Collectors.toList())
							.toArray(new String[0]);
		
		String [] lista_nombre_plantas = todasLasPlantas
							.stream()
							.map(m -> m.getNombre())
							.collect(Collectors.toList())
							.toArray(new String[0]);

		List<Modelo>  todosLosModelos = new ModeloDAO().getAll();
		
		
		String [] lista_modelos = todosLosModelos
							.stream()
							.map(m -> m.getNombre())
							.collect(Collectors.toList())
							.toArray(new String[0]);
		
		String [] lista_marcas = todosLosModelos
							.stream()
							.map(m -> m.getMarca().getNombre())
							.collect(Collectors.toList())
							.toArray(new String[0]);
		
		
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(Color.WHITE);
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new CamionTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.PLAIN,12));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hac� doble clic para editar el campo o presion� Supr para eliminar");
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     // to detect doble click events
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
		               int column = target.getSelectedColumn(); // select a column
		               //JOptionPane.showMessageDialog(null, tabla.getValueAt(row, column)); // get the value of a row and column.
		               String original = (String)tabla.getValueAt(row, column);
		               //String nuevo  = JOptionPane.showInputDialog(null, "Ingres� otro valor para: "+original); // get the value of a row and column.
		               String nuevo = ingresoPopUp("Ingres� otro valor para: "+original);
		               if(nuevo!=null && nuevo.length()>0) {
		            	  // notificacionPopUp(controller.update(original,nuevo));
		            	   actualizarTabla();
		               }
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
		campo_id_camion.setToolTipText("Presion� Enter para agregar");
		campo_id_camion.addActionListener( e->{
		//	Mensaje m = controller.add(campo_id_camion.getText());
			//notificacionPopUp(m);
			//if(m.exito()) 
				actualizarTabla();
		});
		
	//BOTON AGREGAR------------------------------------------------------------------------------------------------
		//boton_agregar.setForeground(Color.WHITE);
		//boton_agregar.setBackground(Color.BLUE);
		boton_agregar.addActionListener( e ->
		{
//			Mensaje m = controller.add(campo_id_camion.getText());
//			notificacionPopUp(m);
//			if(m.exito()) { 
				actualizarTabla();
//				}
			
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
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white);
		panel2.setBorder(borde2);
		texto_id_camion.setForeground(Color.white);
		texto_id_planta.setForeground(Color.white);
		texto_nombre_modelo.setForeground(Color.white);
		texto_distancia.setForeground(Color.white);
		texto_costo_por_km.setForeground(Color.white);
		texto_costo_por_hora.setForeground(Color.white);
		texto_fecha_de_compra.setForeground(Color.white);
		texto_marca.setForeground(Color.white);
		texto_nombre_planta.setForeground(Color.white);
		
		campo_id_planta = new JComboBox<String>(lista_plantas);
		campo_id_planta.setAutoscrolls(true);
		
		campo_id_planta.addItemListener( e-> {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				campo_nombre_planta.setText(lista_nombre_plantas[campo_id_planta.getSelectedIndex()]);
			}
		});
		//campo_nombre_planta.setEditable(false);
		campo_nombre_planta.setText(lista_nombre_plantas[0]);
		campo_nombre_planta.setFocusable(false);
		
		campo_nombre_modelo = new JComboBox<String>(lista_modelos);
		campo_nombre_modelo.addItemListener( e->{
			if(e.getStateChange() == ItemEvent.SELECTED) {
				campo_marca.setText(lista_marcas[campo_nombre_modelo.getSelectedIndex()]);
			}
		});
		//campo_marca.setEditable(false);
		campo_marca.setText(lista_marcas[0]);
		campo_marca.setFocusable(false);

		
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_id_camion);
		colocar(1,0,3,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_id_camion);

		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_id_planta);
		colocar(1,1,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_id_planta);
		
		colocar(2,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre_planta);
		colocar(3,1,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre_planta);
		
		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre_modelo);
		colocar(1,2,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre_modelo);
		
		colocar(2,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_marca);
		colocar(3,2,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_marca);
		
		colocar(0,3,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_distancia);
		colocar(1,3,3,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_distancia);
		
		colocar(0,4,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo_por_km);
		colocar(1,4,3,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo_por_km);
		
		colocar(0,5,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo_por_hora);
		colocar(1,5,3,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo_por_hora);
		
		colocar(0,6,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_fecha_de_compra);
		colocar(1,6,3,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_fecha_de_compra);
		
		colocar(0,7,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
	
	
}







