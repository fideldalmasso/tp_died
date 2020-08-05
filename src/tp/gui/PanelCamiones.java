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
	private JTextField			campo_id_camion = new JTextField();
	private JComboBox<String> 	campo_id_planta = null;
	private JComboBox<String>	campo_nombre_modelo = null;
	private JFormattedTextField campo_distancia = crearCampoDouble();
	private JFormattedTextField campo_costo_por_km = crearCampoDinero();
	private JFormattedTextField campo_costo_por_hora = crearCampoDinero();
	private JFormattedTextField campo_fecha_de_compra =crearCampoFecha();

	private List<Planta> todasLasPlantas;
	String [] desplegable_plantas;

	private List<Modelo>  todosLosModelos;
	String [] desplegable_modelos;


	private JButton boton_eliminar = botonEliminar("Eliminar Camion seleccionado");
	private JButton boton_buscar = botonBusqueda("Filtrar por campos");
	private JButton boton_limpiar = botonLimpiar("Limpiar campos");
	private JButton boton_agregar = botonAgregar("Agregar Camion");


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


	private void actualizarTabla() {
		tableModel.recargarTabla();
		tableModel.fireTableDataChanged();
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
					String valorOriginal = (String)tabla.getValueAt(row, column);
					String valorNuevo = "";
					Boolean continuar = true;
					switch (column) {
					case 1:
						int indice = ingresoComboPopUpInt("Seleccioná una planta distinta a "+valorOriginal, desplegable_plantas);
						if (indice!= -1)
							valorNuevo = todasLasPlantas.get(indice).getId_planta();
						break;
					case 3:
					case 4:
					case 5:
						valorNuevo = ingresoPopUp("Ingresá un valor distinto para "+valorOriginal);
						break;
					default:
						notificacionPopUp(new Mensaje(false, "Este campo no es modificable"));
						continuar = false;
					}
					if(continuar && valorNuevo!=null && valorNuevo.length()>0) {
						Mensaje m = controller.update(column,valorNuevo,tableModel.getObject(row));
						notificacionPopUp(m);
						if(m.exito())
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
		boton_eliminar.addActionListener(e ->{
			intentarEliminar();
		});

		//BOTON BUSCAR------------------------------------------------------------------------------------------------
		
		boton_buscar.addActionListener( e-> {
			int indicePlanta = campo_id_planta.getSelectedIndex();
			int indiceModelo = campo_nombre_modelo.getSelectedIndex();
			Long distancia = (Long) campo_distancia.getValue();
			Double costo_km = (Double) campo_costo_por_km.getValue();
			Double costo_hora = (Double) campo_costo_por_hora.getValue();
			
			String parametros[] = {
					campo_id_camion.getText(),
					indicePlanta==-1?null:todasLasPlantas.get(indicePlanta).getId_planta(),
					indiceModelo==-1?null:todosLosModelos.get(indiceModelo).getNombre(),
					distancia==null?null:((Double)distancia.doubleValue()).toString(),
					costo_km==null?null:costo_km.toString(),
					costo_hora==null?null:costo_hora.toString(),
					campo_fecha_de_compra.getText().length()==0?null:campo_fecha_de_compra.getText()
			};
			
			
			tableModel.setData(controller.query(parametros));
			tableModel.fireTableDataChanged();
			tabla.repaint();
			
		});
		
		//BOTON LIMPIAR------------------------------------------------------------------------------------------------
		boton_limpiar.addActionListener( e-> {
			
			campo_id_camion.setText(null);
			campo_id_planta.setSelectedIndex(-1);
			campo_nombre_modelo.setSelectedIndex(-1);
			campo_distancia.setValue(null);
			campo_costo_por_km.setValue(null);
			campo_costo_por_hora.setValue(null);
			campo_fecha_de_compra.setText(null);
		});
		
		//BOTON AGREGAR------------------------------------------------------------------------------------------------
		

		boton_agregar.addActionListener( e ->
		{
			int indicePlanta = campo_id_planta.getSelectedIndex();
			int indiceModelo = campo_nombre_modelo.getSelectedIndex();
			Long distancia = (Long) campo_distancia.getValue();
			Double costo_km = (Double) campo_costo_por_km.getValue();
			Double costo_hora = (Double) campo_costo_por_hora.getValue();
			
			Mensaje m = controller.add(
					campo_id_camion.getText(),
					indicePlanta==-1?null:todasLasPlantas.get(indicePlanta).getId_planta(),
					indiceModelo==-1?null:todosLosModelos.get(indiceModelo).getNombre(),
					distancia==null?null:distancia.doubleValue(),
					costo_km==null?null:costo_km,
					costo_hora==null?null:costo_hora,
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


		JPanel panel2 = crearPanelInterno("Agregar/Buscar");

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

		colocar(0,7,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,boton_buscar);
		colocar(1,7,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.WEST,panel2,boton_limpiar);
		colocar(2,7,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,boton_agregar);


		//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	

		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
	}


}







