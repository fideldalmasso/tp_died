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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tp.controller.CamionController;
import tp.controller.Mensaje;
import tp.dao.ModeloDAO;
import tp.dao.PlantaDAO;
import tp.dominio.Modelo;
import tp.dominio.Planta;

public class PanelCamiones extends PanelPersonalizado {



	private static void setearFuente(JComponent[] lista) {
		for(JComponent c : lista){
			c.setForeground(Color.BLACK);
			c.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		}
	}

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administración de Camiones",SwingConstants.CENTER);

	private CamionTM tableModel;
	private CamionController controller = new CamionController();
	private JScrollPane scroll_pane;
	private JTable tabla;

	//labels
	private JLabel texto_id_camion = new JLabel("Patente:",SwingConstants.RIGHT);
	private JLabel texto_id_planta = new JLabel("Id planta:",SwingConstants.RIGHT);
	private JLabel texto_nombre_modelo = new JLabel("Modelo:",SwingConstants.RIGHT);
	private JLabel texto_distancia = new JLabel("Distancia recorrida en km:",SwingConstants.RIGHT);
	private JLabel texto_costo_por_km = new JLabel("Costo por km:",SwingConstants.RIGHT);
	private JLabel texto_costo_por_hora = new JLabel("Costo por hora:",SwingConstants.RIGHT);
	private JLabel texto_fecha_de_compra = new JLabel("Fecha de compra (dd/MM/yyyy):",SwingConstants.RIGHT);

	//campos de entrada
	private JTextField campo_id_camion = new JTextField();
	private JComboBox<String> campo_id_planta = null;
	private JComboBox<String> campo_nombre_modelo = null;
	private JFormattedTextField campo_distancia = crearCampoDouble();
	private JFormattedTextField campo_costo_por_km = crearCampoDinero();
	private JFormattedTextField campo_costo_por_hora = crearCampoDinero();
	private JFormattedTextField campo_fecha_de_compra =crearCampoFecha();

	private List<Planta> todasLasPlantas;
	String [] desplegable_plantas;

	private List<Modelo>  todosLosModelos;
	String [] desplegable_modelos;


	private JButton boton_agregar = botonAgregar("Agregar Camion");
	private JButton boton_eliminar = botonEliminar("Eliminar Camion seleccionado");


	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);

			int resultado = eliminarPopUp("¿Eliminar "+identificador+"?");
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
		
		this.fileFondo = "icon/fondo2.png";
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/

		//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(Color.decode("#dd1c1a"));

		//TABLA------------------------------------------------------------------------------------------------
		tableModel = new CamionTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,12));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.PLAIN,12));
		tabla.setRowHeight(20);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setToolTipText("Hacé doble clic para editar el campo o presioná Supr para eliminar");

		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow(); 
					int column = target.getSelectedColumn(); 
					String original = (String)tabla.getValueAt(row, column);
					String nuevo = "";
					Boolean procesar = true;
					switch (column) {
					case 1:
						nuevo = ingresoComboPopUp("Seleccioná una planta distinta a "+original, desplegable_plantas);
						break;
					default:
						procesar = false;
						notificacionPopUp(new Mensaje(false, "Este campo no es modificable"));
					}
					if(procesar && nuevo!=null && nuevo.length()>0) {
						//	Mensaje m = controller.update(column,nuevo,tableModel.getObject(row));
						//	notificacionPopUp(m);
						//	if(m.exito())
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


		//BOTON AGREGAR------------------------------------------------------------------------------------------------
		//boton_agregar.setForeground(Color.WHITE);
		//boton_agregar.setBackground(Color.BLUE);

		boton_agregar.addActionListener( e ->
		{
			Mensaje m = controller.add(
					campo_id_camion.getText(),
					todasLasPlantas.get(campo_id_planta.getSelectedIndex()).getId_planta(),
					todosLosModelos.get(campo_nombre_modelo.getSelectedIndex()).getNombre(),
					campo_distancia.getText(),
					campo_costo_por_km.getValue().toString(),
					campo_costo_por_hora.getValue().toString(),
					campo_fecha_de_compra.getText()
					);
			notificacionPopUp(m);
			if(m.exito()) { 
				actualizarTabla();
			}

		}
				);


		//COMBOBOX PLANTAS------------------------------------------------------------------------------------------------
		todasLasPlantas = new PlantaDAO().getAll();
		desplegable_plantas = todasLasPlantas
				.stream()
				.map(p -> p.getId_planta()+" ["+p.getNombre()+"]")
				.collect(Collectors.toList())
				.toArray(new String[0]);
		campo_id_planta = new JComboBox<String>(desplegable_plantas);
		campo_id_planta.setSelectedItem(null);
		AutoCompletion.enable(campo_id_planta);



		//COMBOBOX MODELOS------------------------------------------------------------------------------------------------
		todosLosModelos = new ModeloDAO().getAll();
		desplegable_modelos = todosLosModelos
				.stream()
				.map(m -> m.getNombre()+" ["+m.getMarca().getNombre()+"]")
				.collect(Collectors.toList())
				.toArray(new String[0]);
		campo_nombre_modelo = new JComboBox<String>(desplegable_modelos);
		campo_nombre_modelo.setSelectedItem(null);
		
		AutoCompletion.enable(campo_nombre_modelo);

		//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = crearPanelInterno("Editar/Eliminar");

		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);

		//PANEL2------------------------------------------------------------------------------------------------


		JPanel panel2 = crearPanelInterno("Agregar");

		setearFuente(new JComponent[]{texto_id_camion,texto_id_planta,texto_nombre_modelo,texto_distancia,texto_costo_por_km,texto_costo_por_hora,texto_fecha_de_compra});

		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_id_camion);
		colocar(1,0,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_id_camion);

		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_id_planta);
		colocar(1,1,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_id_planta);

		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre_modelo);
		colocar(1,2,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre_modelo);

		colocar(0,3,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_distancia);
		colocar(1,3,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_distancia);

		colocar(0,4,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo_por_km);
		colocar(1,4,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo_por_km);

		colocar(0,5,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_costo_por_hora);
		colocar(1,5,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_costo_por_hora);

		colocar(0,6,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_fecha_de_compra);
		colocar(1,6,2,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_fecha_de_compra);

		colocar(1,7,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);


		//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	

		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
	}


}







