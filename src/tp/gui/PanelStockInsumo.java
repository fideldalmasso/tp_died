package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row,1);
			StockInsumo si = this.tableModel.getStockInsumo(row);
			int resultado = eliminarPopUp("Â¿Eliminar "+identificador+"?");
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
		this.setBackground(new Color(250, 216, 214));
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
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); 
		               int column = target.getSelectedColumn();
		               
		               StockInsumo actual = tableModel.getStockInsumo(row);
		               
		               switch(column) {
		               case 2:
		            	   Integer original = (Integer) tabla.getValueAt(row, column);
		            	   String nuevo1 = ingresoPopUp("Ingresá otro valor para: "+original);
			               if(nuevo1!=null && nuevo1.length()>0) {
			            	   notificacionPopUp(controller.update(actual,
			            			   new StockInsumo(actual.getPlanta(),actual.getInsumo(),Integer.parseInt(nuevo1),actual.getPuntoDePedido())));
			            	   actualizarTabla();
			               }
		            	   break;
		               case 3:
		            	   Integer original2 = (Integer) tabla.getValueAt(row, column);
		            	   String nuevo2 = ingresoPopUp("Ingresá otro valor para: "+original2);
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
		
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		
		//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		
		
	}
}
