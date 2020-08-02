package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.controller.Mensaje;
import tp.controller.PedidoController;

public class PanelPedidos extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Pedidos Creados",SwingConstants.CENTER);
	
	private PedidoTM tableModel;
	private PedidoController controller = new PedidoController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JButton boton_cancelar = botonEliminar("Cancelar Pedido");
	private JButton boton_ver_detalle = botonBusqueda("Ver Detalle");
	private JButton boton_agregar = botonAgregar("Agregar Pedido");
	private JButton boton_procesar = new JButton("Procesar Pedido",emoji("icon/envio.png",24,24));
	private JLabel texto_estados = new JLabel("Cambiar vista:");
	private JComboBox<String> campo_estados = new JComboBox<String>(); 
	private JLabel espacio = new JLabel(" ");
	
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
	
	private void actualizarTabla() {
		tableModel.fireTableDataChanged();
		tableModel.recargarTabla();
		tabla.repaint();
		//tabla.validate();
	}
	
	public PanelPedidos() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214));
		this.fileFondo="icon/fondo2.png";
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new PedidoTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hacé doble clic para editar el campo o presioná Supr para eliminar");
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE)
					intentarEliminar();
			}
		});
		
		scroll_pane = new JScrollPane(tabla);
		
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		colocar(0,0,4,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_agregar);
		colocar(1,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_ver_detalle);
		boton_procesar.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		colocar(2,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_procesar);
		colocar(3,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_cancelar);
		
		String[] items = {"Creados","Procesados","Entregados","Cancelados"};
		campo_estados = new JComboBox<String>(items);
		
	//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,1,1,0,10,GridBagConstraints.NONE,10,this,titulo);
		colocar(3,0,4,1,1,1,0,0,GridBagConstraints.HORIZONTAL,10,this,espacio);
		colocar(7,0,1,1,1,1,0,0,GridBagConstraints.EAST,10,this,texto_estados);
		colocar(8,0,1,1,1,1,0,0,GridBagConstraints.HORIZONTAL,10,this,campo_estados);
		colocar(0,1,9,1,1,1,0,0,GridBagConstraints.BOTH,10,this,panel1);
		
	}
}