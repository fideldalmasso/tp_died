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

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import tp.controller.StockInsumoController;
import tp.dominio.Planta;
import tp.dominio.StockInsumo;

public class PanelStockInsumo extends PanelPersonalizado{

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("",SwingConstants.CENTER);
	
	private StockInsumoTM tableModel;
	private StockInsumoController controller = new StockInsumoController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	
	
	//private JLabel texto_nombre = new JLabel("Nombre:");
	//private JLabel texto_stock = new JLabel("Stock:");
	//private JLabel texto_punto_de_pedido = new JLabel("Punto de pedido:");
	//private JTextField campo_nombre = new JTextField();
	//private JTextField campo_stock = new JTextField();
	//private JTextField campo_punto_de_pedido = new JTextField();
	
	//private JButton boton_agregar = botonAgregar("Agregar Stock Insumo");
	//private JButton boton_eliminar = botonEliminar("Eliminar Insumo");
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row,1);
			StockInsumo si = this.tableModel.getStockInsumo(row);
			int resultado = eliminarPopUp("¿Eliminar "+identificador+"?");
			if(resultado == JOptionPane.YES_OPTION) {
				notificacionPopUp(controller.delete(si));
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
	
	public PanelStockInsumo(Planta planta) {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		this.fileFondo="icon/fondo2.png";
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setText("Stock de Insumos de: "+planta.getNombre());
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new StockInsumoTM(planta);
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hace doble clic para editar el campo o presiona Supr para eliminar");
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     // to detect doble click events
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
		               int column = target.getSelectedColumn(); // select a column
		               //JOptionPane.showMessageDialog(null, tabla.getValueAt(row, column)); // get the value of a row and column.
		               
		               StockInsumo actual = tableModel.getStockInsumo(row);
		               //String nuevo  = JOptionPane.showInputDialog(null, "Ingres� otro valor para: "+original); // get the value of a row and column.
		             
		               switch(column) {
		               case 2:
		            	   Integer original = (Integer) tabla.getValueAt(row, column);
		            	   String nuevo1 = ingresoPopUp("Ingres� otro valor para: "+original);
			               if(nuevo1!=null && nuevo1.length()>0) {
			            	   notificacionPopUp(controller.update(actual,
			            			   new StockInsumo(actual.getPlanta(),actual.getInsumo(),Integer.parseInt(nuevo1),actual.getPuntoDePedido())));
			            	   actualizarTabla();
			               }
		            	   break;
		               case 3:
		            	   Integer original2 = (Integer) tabla.getValueAt(row, column);
		            	   String nuevo2 = ingresoPopUp("Ingres� otro valor para: "+original2);
			               if(nuevo2!=null && nuevo2.length()>0) {
			            	   notificacionPopUp(controller.update(actual,
			            			   new StockInsumo(actual.getPlanta(),actual.getInsumo(),actual.getStock(),Integer.parseInt(nuevo2))));
			            	   actualizarTabla();
			               }
		            	   break;
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
		/*boton_eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				intentarEliminar();
			}
		});
		*/
	//BOTON AGREGAR------------------------------------------------------------------------------------------------
		//boton_agregar.setForeground(Color.WHITE);
		//boton_agregar.setBackground(Color.BLUE);
		/*boton_agregar.addActionListener( e ->
		{
			Mensaje m = controller.add(new StockInsumo(null,campo_nombre.getText(),Integer.parseInt(campo_stock.getText()),Integer.parseInt(campo_punto_de_pedido.getText())));
			notificacionPopUp(m);
			if(m.exito()) { 
				actualizarTabla();
			}
			
		}
	);
	*/
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		//colocar(1,1,1,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
		
	/*	JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setOpaque(false);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.YELLOW);
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.white);
		panel2.setBorder(borde2);
		
		texto_nombre.setForeground(Color.white);
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre);
		colocar(1,0,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre);
		
		texto_stock.setForeground(Color.white);
		colocar(0,1,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_stock);
		colocar(1,1,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_stock);
		
		texto_punto_de_pedido.setForeground(Color.white);
		colocar(0,2,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_punto_de_pedido);
		colocar(1,2,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_punto_de_pedido);
		
		colocar(0,3,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
	*/
	
		//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		//colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
}
