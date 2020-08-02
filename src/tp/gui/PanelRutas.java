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

import tp.controller.Mensaje;
import tp.controller.PlantaController;
import tp.controller.RutaController;
import tp.dominio.Planta;
import tp.dominio.Ruta;

public class PanelRutas extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administraci�n de Rutas",SwingConstants.CENTER);
	
	private RutaTM tableModel;
	private RutaController controller = new RutaController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JButton boton_eliminar = botonEliminar("Eliminar Ruta");
	
	private JLabel texto_nombre = new JLabel("Nombre:",SwingConstants.RIGHT);
	private JTextField campo_nombre = new JTextField();
	
	private JLabel texto_distancia = new JLabel("Distancia (Km):",SwingConstants.RIGHT);
	private JTextField campo_distancia = new JTextField();
	
	private JLabel texto_duracion = new JLabel("Duración (min):",SwingConstants.RIGHT);
	private JTextField campo_duracion = new JTextField();
	
	private JLabel texto_peso_maximo = new JLabel("Peso máximo (Kg):",SwingConstants.RIGHT);
	private JTextField campo_peso_maximo = new JTextField();

	private PlantaController pc;
	private String[] items;
	
	private JLabel texto_planta_origen = new JLabel("Origen: ",SwingConstants.RIGHT);
	private JComboBox<String> campo_planta_origen;
	
	private JLabel texto_planta_destino = new JLabel("Destino: ",SwingConstants.RIGHT);
	private JComboBox<String> campo_planta_destino;
	
	private JButton boton_agregar = botonAgregar("Agregar Ruta");
	
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	private static Color color_letras = Color.BLACK;
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);
			
			int resultado = eliminarPopUp("¿Eliminar "+identificador+"?");
			if(resultado == JOptionPane.YES_OPTION) {
				notificacionPopUp(controller.delete(identificador));
				actualizarTabla();
			}
		}
	}
	
	private void intentarAgregar() {
		pc=new PlantaController();
		String origen, destino;
		Integer indice_destino = campo_planta_destino.getSelectedIndex(), indice_origen=campo_planta_origen.getSelectedIndex();
		if(indice_origen==-1) 
			origen = ""; 
		else 
			origen = items[indice_origen];
		if(indice_destino==-1) 
			destino =""; 
		else 
			destino = items[indice_destino];
		
		notificacionPopUp(controller.add(campo_nombre.getText(),origen,destino,campo_distancia.getText(),campo_duracion.getText(), campo_peso_maximo.getText()));
		actualizarTabla();
	}
	
	private void actualizarTabla() {
		tableModel.fireTableDataChanged();
		tableModel.recargarTabla();
		tabla.repaint();
		//tabla.validate();
	}
	
	public PanelRutas() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214));
		this.fileFondo="icon/fondo2.png";
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new RutaTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hacé doble clic para editar el campo o presioná Supr para eliminar");
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow();
		               int column = target.getSelectedColumn();
		               String id = (String) tabla.getValueAt(row, 0);
		               String nombre_origen = (String) tabla.getValueAt(row, 1);
		               String nombre_destino = (String) tabla.getValueAt(row, 2);
		               Double distancia = (Double) tabla.getValueAt(row, 3);
		               Double duracion = (Double) tabla.getValueAt(row, 4);
		               Double peso_maximo = (Double) tabla.getValueAt(row, 5);
		               
		               switch(column) {
		               case 3:
		            	   distancia = Double.parseDouble(ingresoPopUp("Ingres� otro valor para: "+distancia));
		            	   break;
		               case 4:
		            	   duracion = Double.parseDouble(ingresoPopUp("Ingres� otro valor para: "+duracion));
			               break;
		               case 5:
		            	   peso_maximo = Double.parseDouble(ingresoPopUp("Ingres� otro valor para: "+peso_maximo));
		            	   break;
		               }
		               
		               notificacionPopUp(controller.update(id,nombre_origen,nombre_destino,Double.toString(distancia),Double.toString(duracion),Double.toString(peso_maximo)));
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

	//BOTON ELIMINAR------------------------------------------------------------------------------------------------
		
		boton_eliminar.addActionListener(e -> intentarEliminar());
		boton_agregar.addActionListener(e -> intentarAgregar());
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		colocar(1,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
		
	//PANEL2------------------------------------------------------------------------------------------------
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setOpaque(false);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel2.setBorder(borde2);
		
		texto_nombre.setForeground(color_letras);
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre);
		colocar(1,0,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre);
		
		texto_distancia.setForeground(color_letras);
		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_distancia);
		colocar(1,1,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_distancia);
		
		texto_duracion.setForeground(color_letras);
		colocar(2,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_duracion);
		colocar(3,1,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_duracion);
		
		texto_peso_maximo.setForeground(color_letras);
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_peso_maximo);
		colocar(3,0,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_peso_maximo);
		
		PlantaController pc = new PlantaController();
		items = pc.getAll().parallelStream().map(p->p.getNombre()).collect(Collectors.toList()).toArray(new String[0]);
		campo_planta_origen = new JComboBox<String>(items);
		campo_planta_destino = new JComboBox<String>(items);
		texto_planta_origen.setForeground(color_letras);
		campo_planta_origen.setSelectedItem(null);
		AutoCompletion.enable(campo_planta_origen);
		campo_planta_destino.setSelectedItem(null);
		AutoCompletion.enable(campo_planta_destino);
		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_planta_origen);
		colocar(1,2,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_planta_origen);
		
		texto_planta_destino.setForeground(color_letras);
		colocar(2,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_planta_destino);
		colocar(3,2,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_planta_destino);
		
		colocar(0,3,4,1,0,0,0,0,GridBagConstraints.CENTER,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,0,0,0,10,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
}
