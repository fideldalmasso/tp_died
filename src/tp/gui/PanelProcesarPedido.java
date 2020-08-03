package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import tp.controller.DetallePedidoController;
import tp.controller.Mensaje;
import tp.controller.PedidoController;
import tp.controller.PlantaController;
import tp.dao.PedidoDAO;
import tp.dominio.DetallePedido;
import tp.dominio.Pedido;

public class PanelProcesarPedido extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Procesar Pedido",SwingConstants.CENTER);
	
	private CaminoTM table_model_duracion;
	private CaminoTM table_model_distancia;
	private PedidoController pedidoController = new PedidoController();
	private JScrollPane scroll_pane_duracion;
	private JScrollPane scroll_pane_distancia;
	private JTable tabla_duracion;
	private JTable tabla_distancia;
	
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
			this.table_model_distancia = new CaminoTM(0);
			this.tabla_distancia = new JTable();
			this.tabla_distancia.setModel(this.table_model_distancia);
			this.tabla_distancia.setIgnoreRepaint(false);
			this.tabla_distancia.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla_distancia.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla_distancia.setRowHeight(20);
			tabla_distancia.getTableHeader().setReorderingAllowed(false);
			this.table_model_duracion = new CaminoTM(1);
			this.tabla_duracion = new JTable();
			this.tabla_duracion.setModel(this.table_model_duracion);
			this.tabla_duracion.setIgnoreRepaint(false);
			this.tabla_duracion.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
			this.tabla_duracion.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
			this.tabla_duracion.setRowHeight(20);
			tabla_duracion.getTableHeader().setReorderingAllowed(false);
			
			this.scroll_pane_distancia = new JScrollPane(this.tabla_distancia);
			this.scroll_pane_duracion= new JScrollPane(this.tabla_duracion);
			
			//CAMPO PLANTA ORIGEN
			campo_planta_origen.addItemListener(e->{
				String nombre_origen = (String) campo_planta_origen.getSelectedItem();
				String nombre_destino = pedido.getPlanta_destino().getNombre();
				table_model_distancia.recargarTabla(nombre_origen, nombre_destino);
				table_model_duracion.recargarTabla(nombre_origen, nombre_destino);
				actualizarTabla(tabla_distancia,table_model_distancia);
				actualizarTabla(tabla_duracion,table_model_duracion);
			});
			boton_seleccionar_planta.addActionListener(e->{
				System.out.println("AAA");
				String nombre_origen = (String) campo_planta_origen.getSelectedItem();
				String nombre_destino = pedido.getPlanta_destino().getNombre();
				table_model_distancia.recargarTabla(nombre_origen, nombre_destino);
				table_model_duracion.recargarTabla(nombre_origen, nombre_destino);
				actualizarTabla(tabla_distancia,table_model_distancia);
				actualizarTabla(tabla_duracion,table_model_duracion);
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
			colocar(0,2,4,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_duracion);

			colocar(1,2,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_seleccionar_camino);
			
			colocar(0,4,4,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_procesar_pedido);
			
			//ORGANIZACION DE PANELES
			
			colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
			colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		}
		
	}
}
