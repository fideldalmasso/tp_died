package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.app.App;
import tp.controller.DetallePedidoController;

public class PanelDetallePedido extends PanelPersonalizado{
	private static final long serialVersionUID = 2L;

	private JLabel titulo = new JLabel("Pedidos Creados",SwingConstants.CENTER);
	
	private DetallePedidoTM tableModel;
	private DetallePedidoController controller = new DetallePedidoController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JButton boton_volver = new JButton("Volver");
	
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	private static Color color_letras = Color.BLACK;
	
	private void cambiarPanel(PanelPersonalizado p1) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        App app = (App) frame;
        app.cambiarPanel(p1);
	}
	
	private void actualizarTabla(String id_planta) {
		tableModel.fireTableDataChanged();
		tableModel.recargarTabla(id_planta);
		tabla.repaint();
	}
	
	public PanelDetallePedido(String id_planta) {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214));
		
		//TITULO
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
		//TABLA
		tableModel = new DetallePedidoTM();
		tableModel.recargarTabla(id_planta);
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hacé doble clic para editar el campo o presioná Supr para eliminar");
		tabla.getTableHeader().setReorderingAllowed(false);
		
		scroll_pane = new JScrollPane(tabla);
		
		//BOTON VOLVER
		boton_volver.addActionListener( e-> cambiarPanel(new PanelPedidos()));
		
		//PANEL1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		boton_volver.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_volver);
		
		//ORGANIZACION DE PANELES
		colocar(0,0,1,1,1,1,0,10,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,9,1,1,1,0,0,GridBagConstraints.BOTH,10,this,panel1);
		
	}
}
