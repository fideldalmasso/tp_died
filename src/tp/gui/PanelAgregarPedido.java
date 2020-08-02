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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.controller.PedidoController;
import tp.controller.PlantaController;

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
	
	private JLabel texto_fecha_maxima = new JLabel("Planta Destino:");
	private JTextField campo_fecha_maxima = new JTextField("Fecha Maxima:");
	private JLabel texto_planta_destino = new JLabel("Planta Destino:");
	private JComboBox<String> campo_planta_destino = new JComboBox<String>();
	private JButton boton_agregar_pedido = botonAgregar("Agregar Pedido");
	private JButton boton_agregar_insumo = botonEliminar("Agregar Insumo");
	private JButton boton_quitar_insumo = botonEliminar("Quitar Insumo");
	
	
	private void actualizarTabla(JTable tabla) {
		tabla.repaint();
	}
	
	public PanelAgregarPedido() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(Color.WHITE);
		
	//TABLA------------------------------------------------------------------------------------------------
		this.table_model_detalle_pedido = new DetallePedidoTM();
		this.tablaDetallePedido = new JTable();
		this.tablaDetallePedido.setModel(this.table_model_detalle_pedido);
		this.tablaDetallePedido.setIgnoreRepaint(false);
		this.tablaDetallePedido.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		this.tablaDetallePedido.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		this.tablaDetallePedido.setRowHeight(20);
		
		this.table_model_insumo = new InsumoTM();
		this.tablaInsumo = new JTable();
		this.tablaInsumo.setModel(this.table_model_insumo);
		this.tablaInsumo.setIgnoreRepaint(false);
		this.tablaInsumo.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		this.tablaInsumo.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		this.tablaInsumo.setRowHeight(20);
		
		this.scroll_pane_detalle_pedido = new JScrollPane(this.tablaDetallePedido);
		this.scroll_pane_insumo = new JScrollPane(this.tablaInsumo);
	
		
		//PANEL1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.YELLOW);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white);
		panel1.setBorder(borde1);
		
		PlantaController pc = new PlantaController();
		String[] items = pc.getAll().parallelStream().map(p->p.getNombre()).collect(Collectors.toList()).toArray(new String[0]);
		campo_planta_destino = new JComboBox<String>(items);
		AutoCompletion.enable(campo_planta_destino);
		
		colocar(0,0,1,1,1,1,0,0,GridBagConstraints.NONE, 10, panel1, texto_planta_destino);
		colocar(1,0,1,1,1,1,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, campo_planta_destino);
		
		colocar(3,0,1,1,1,1,0,0,GridBagConstraints.NONE, 10, panel1, texto_fecha_maxima);
		colocar(4,0,1,1,1,1,0,0,GridBagConstraints.HORIZONTAL, 10, panel1, campo_fecha_maxima);
		
		colocar(0,1,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_detalle_pedido);
		colocar(3,1,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane_insumo);

		colocar(2,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_quitar_insumo);
		colocar(2,2,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_agregar_insumo);
		
		colocar(2,3,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_agregar_pedido);
		
		
		//ORGANIZACION DE PANELES
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		
	}
}
