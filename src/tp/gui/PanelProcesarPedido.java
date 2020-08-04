package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.app.App;
import tp.controller.ASeguirEnController;
import tp.controller.CamionController;
import tp.controller.DetallePedidoController;
import tp.controller.EnvioController;
import tp.controller.Mensaje;
import tp.controller.PedidoController;
import tp.controller.PlantaController;
import tp.dao.PedidoDAO;
import tp.dominio.Camion;
import tp.dominio.DetallePedido;
import tp.dominio.Envio;
import tp.dominio.Pedido;
import tp.dominio.Planta;
import tp.dominio.Ruta;
import tp.enumerados.Estado;

public class PanelProcesarPedido extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Procesar Pedido",SwingConstants.CENTER);
	
	private Camion camion;
	private CaminoTM table_model_distancia;
	private JScrollPane scroll_pane_distancia;
	private JTable tabla_distancia;
	
	private CaminoTM table_model;
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel texto_planta_origen = new JLabel("Planta Origen:");
	private JComboBox<String> campo_planta_origen = new JComboBox<String>();
	private JButton boton_procesar_pedido = botonAgregar("Procesar Pedido");
	private JButton boton_seleccionar_camino= new JButton("Seleccionar Camino");
	private JButton boton_seleccionar_planta= new JButton("Seleccionar Planta");
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	private static Color color_letras = Color.BLACK;
	
	private void cambiarPanel(PanelPersonalizado p1) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        App app = (App) frame;
        app.cambiarPanel(p1);
	}
	
	private void actualizarTabla(JTable tabla, AbstractTableModel modelo) {
		modelo.fireTableDataChanged();
		tabla.repaint();
	}
	
	public PanelProcesarPedido(Pedido pedido) {
		super();
		PlantaController pc = new PlantaController();
		String[] items = pc.getAll(pedido.getId_pedido()).parallelStream().map(p->p.getNombre()).collect(Collectors.toList()).toArray(new String[0]);
		if(items.length==0) {
			notificacionPopUp(new Mensaje(false,"No hay plantas que puedan satisfacer el pedido."));
			
			cambiarPanel(new PanelPedidos());
		}else {
			this.setLayout(new GridBagLayout());
			this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
			this.fileFondo="icon/fondo2.png";
			
			//TITULO
			titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
			titulo.setForeground(color_titulo);
			
			//TABLA
			this.table_model_distancia = new CaminoTM();
			this.tabla_distancia = new JTable();
			this.tabla_distancia.setModel(this.table_model_distancia);
			this.tabla_distancia.setIgnoreRepaint(false);
			this.tabla_distancia.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla_distancia.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla_distancia.setRowHeight(20);
			tabla_distancia.getTableHeader().setReorderingAllowed(false);
			this.scroll_pane_distancia = new JScrollPane(this.tabla_distancia);
			
			this.table_model = new CaminoTM();
			this.tabla = new JTable();
			this.tabla.setModel(this.table_model);
			this.tabla.setIgnoreRepaint(false);
			this.tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla.setRowHeight(20);
			tabla.getTableHeader().setReorderingAllowed(false);
			this.scroll_pane = new JScrollPane(this.tabla);
			
			//BOTON SELECCIONAR PLANTA
			boton_seleccionar_planta.addActionListener(e->{
				String nombre_origen = (String) campo_planta_origen.getSelectedItem();
				String nombre_destino = pedido.getPlanta_destino().getNombre();
				CamionController cc = new CamionController();
				camion = cc.getDisponible(nombre_origen);
				if(camion==null) {
					notificacionPopUp(new Mensaje(false,"No hay camiones disponibles en este momento. Pruebe más tarde."));
				}else {
					table_model_distancia.setCamion(camion);
					table_model_distancia.recargarTabla(nombre_origen, nombre_destino);
					actualizarTabla(tabla_distancia,table_model_distancia);
				}
			});
			
			//BOTON SELECCIONAR CAMINO
			boton_seleccionar_camino.addActionListener(e->{
				Integer row = tabla_distancia.getSelectedRow();
				table_model.setData(table_model_distancia.getCamino(row));
				table_model.setCamion(table_model_distancia.getCamion());
				actualizarTabla(tabla,table_model);
			});
			
			//BOTON PROCESAR PEDIDO
			boton_procesar_pedido.addActionListener(e->{
				List<List<Ruta>> caminos = table_model.getData();
				if(caminos.size()==0) {
					notificacionPopUp(new Mensaje(false,"Seleccione al menos un camino para realizar el envío."));
				}else {
					Double costo = (Double) table_model.getValueAt(0,3);
					EnvioController ec = new EnvioController();
					ec.add(new Envio(null,table_model.getCamion(),costo));
					String id = ec.getId();
					ASeguirEnController asc = new ASeguirEnController();
					for(int i=0; i<caminos.get(0).size();i++) {
						asc.add(id,caminos.get(0).get(i).getId_ruta(),i);
					}
					PedidoController pec = new PedidoController();
					String nombre_planta = (String) campo_planta_origen.getSelectedItem();
					Planta planta = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_planta)).findFirst().orElse(null);
					Pedido nuevo = new Pedido(pedido.getId_pedido(),
							planta,
							pedido.getPlanta_destino(),
							new Envio(id,table_model.getCamion(),costo),
							pedido.getFecha_solicitud(),
							LocalDate.now(),
							pedido.getFecha_maxima(),
							Estado.PROCESADA,
							costo);
					notificacionPopUp(pec.update(pedido,nuevo));
					cambiarPanel(new PanelPedidos());
				}
			});
			
			//PANEL1
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridBagLayout());
			panel1.setOpaque(false);
			
			
			Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
			borde1 = BorderFactory.createTitledBorder(borde1, "Seleccionar Camino", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
			panel1.setBorder(borde1);
			
			campo_planta_origen = new JComboBox<String>(items);
			campo_planta_origen.setSelectedItem(null);
			AutoCompletion.enable(campo_planta_origen);
			
			colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel1, texto_planta_origen);
			colocar(1,0,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, campo_planta_origen);
			colocar(2,0,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, boton_seleccionar_planta);
			
			colocar(0,1,4,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_distancia);
			colocar(0,2,4,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane);
			colocar(0,3,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_seleccionar_camino);
			
			colocar(2,3,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_procesar_pedido);
			
			//ORGANIZACION DE PANELES
			
			colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
			colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		}
		
	}
}
