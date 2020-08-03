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
import tp.dominio.DetallePedido;
import tp.dominio.Pedido;
import tp.dominio.Planta;
import tp.controller.PedidoController;
import tp.controller.PlantaController;
import tp.dao.PedidoDAO;

public class PanelAgregarPedido extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Agregar Pedido",SwingConstants.CENTER);
	
	private InsumoTM table_model_insumo;
	private DetallePedidoTM table_model_detalle_pedido;
	private PedidoController pedidoController = new PedidoController();
	private JScrollPane scroll_pane_detalle_pedido;
	private JScrollPane scroll_pane_insumo;
	private JTable tablaInsumo;
	private JTable tablaDetallePedido;
	
	private JLabel texto_fecha_maxima = new JLabel("Fecha Maxima:");
	private JTextField campo_fecha_maxima = new JTextField();
	private JLabel texto_planta_destino = new JLabel("Planta Destino:");
	private JComboBox<String> campo_planta_destino = new JComboBox<String>();
	private JButton boton_agregar_pedido = botonAgregar("Agregar Pedido");
	private JButton boton_agregar_insumo = new JButton("Agregar Insumo",emoji("icon/flecha_abajo.png",24,24));
	private JButton boton_quitar_insumo= new JButton("Quitar Insumo",emoji("icon/flecha_arriba.png",24,24));
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
	
	public PanelAgregarPedido() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		this.fileFondo="icon/fondo2.png";
		
		//TITULO
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
		//TABLA
		this.table_model_detalle_pedido = new DetallePedidoTM();
		this.tablaDetallePedido = new JTable();
		this.tablaDetallePedido.setModel(this.table_model_detalle_pedido);
		this.tablaDetallePedido.setIgnoreRepaint(false);
		this.tablaDetallePedido.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		this.tablaDetallePedido.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		this.tablaDetallePedido.setRowHeight(20);
		tablaDetallePedido.getTableHeader().setReorderingAllowed(false);
		this.table_model_insumo = new InsumoTM();
		this.tablaInsumo = new JTable();
		this.tablaInsumo.setModel(this.table_model_insumo);
		this.tablaInsumo.setIgnoreRepaint(false);
		this.tablaInsumo.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		this.tablaInsumo.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		this.tablaInsumo.setRowHeight(20);
		tablaInsumo.getTableHeader().setReorderingAllowed(false);
		
		this.scroll_pane_detalle_pedido = new JScrollPane(this.tablaDetallePedido);
		this.scroll_pane_insumo = new JScrollPane(this.tablaInsumo);
		
		//TABLA DETALLE PEDIDO
		tablaDetallePedido.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow();
		               int column = target.getSelectedColumn();
		              
		               
		               switch(column) {
		               case 2:
		            	   Integer cantidad = (Integer) tablaDetallePedido.getValueAt(row, column);
		            	   String cantidad_nueva = ingresoPopUp("Ingres� otro valor para: "+cantidad);
			               if(cantidad_nueva!=null && cantidad_nueva.length()>0) {
			            	   table_model_detalle_pedido.getDetallePedido(row).setCantidad_de_unidades(Integer.parseInt(cantidad_nueva));
			            	   actualizarTabla(tablaDetallePedido,table_model_detalle_pedido);
			               }
			               break;
		               }
				}
			}
		});
		
		//BOTON AGREGAR INSUMO
		boton_agregar_insumo.addActionListener(e->{
			Integer row = tablaInsumo.getSelectedRow();
			if(row!=-1) {
				table_model_detalle_pedido.addDetallePedido(table_model_insumo.getInsumo(row));
				table_model_insumo.deleteInsumo(row);
				actualizarTabla(tablaInsumo,table_model_insumo);
				actualizarTabla(tablaDetallePedido,table_model_detalle_pedido);
			}else {
				notificacionPopUp(new Mensaje(false, "Ningún insumo seleccionado."));
			}
		});
		//BOTON QUITAR INSUMO
		boton_quitar_insumo.addActionListener(e->{
			Integer row = tablaDetallePedido.getSelectedRow();
			if(row!=-1) {
				table_model_insumo.addInsumo(table_model_detalle_pedido.getDetallePedido(row));
				table_model_detalle_pedido.deleteDetallePedido(row);
				actualizarTabla(tablaInsumo,table_model_insumo);
				actualizarTabla(tablaDetallePedido,table_model_detalle_pedido);
			}else {
				notificacionPopUp(new Mensaje(false, "Ningún insumo seleccionado."));
			}
		});
		//BOTON AGREGAR PEDIDO
		boton_agregar_pedido.addActionListener(e->{
			String nombre_planta_destino = (String) campo_planta_destino.getSelectedItem();
			String fecha_maxima = (String) campo_fecha_maxima.getText();
			List<DetallePedido> detalles = table_model_detalle_pedido.getAll().parallelStream().filter(dp->dp.getCantidad_de_unidades()>0).collect(Collectors.toList());
			PedidoController controller = new PedidoController();
			DetallePedidoController cdp = new DetallePedidoController();
			if(detalles.parallelStream().filter(d->d.getCantidad_de_unidades()>0).count()>0) {
				Mensaje m = controller.add(nombre_planta_destino,fecha_maxima,detalles);
				if(m.exito()) {
					PedidoDAO pdd = new PedidoDAO();
					String id = pdd.getId();
					Pedido pedido = new Pedido();
					pedido.setId_pedido(id);
					for(int i=0; i<detalles.size();i++) {
						detalles.get(i).setPedido(pedido);
						cdp.add(detalles.get(i));
					}
					cambiarPanel(new PanelPedidos());
				}else {
					notificacionPopUp(m);
				}
			}else {
				notificacionPopUp(new Mensaje(false,"Error: al menos un insumo debe tener una cantidad mayor a 0."));
			}
		});
			
		//PANEL1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		PlantaController pc = new PlantaController();
		String[] items = pc.getAll().parallelStream().map(p->p.getNombre()).collect(Collectors.toList()).toArray(new String[0]);
		campo_planta_destino = new JComboBox<String>(items);
		AutoCompletion.enable(campo_planta_destino);
		
		boton_agregar_insumo.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		boton_quitar_insumo.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel1, texto_planta_destino);
		colocar(1,0,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, campo_planta_destino);
		
		colocar(2,0,1,1,0,0,0,0,GridBagConstraints.NONE, 10, panel1, texto_fecha_maxima);
		colocar(3,0,1,1,0,0,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, campo_fecha_maxima);
		
		colocar(0,1,4,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_insumo);
		colocar(0,3,4,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_detalle_pedido);
		

		colocar(3,2,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_quitar_insumo);
		colocar(1,2,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_agregar_insumo);
		
		colocar(0,4,4,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_agregar_pedido);
		
		
		//ORGANIZACION DE PANELES
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		
	}
}
