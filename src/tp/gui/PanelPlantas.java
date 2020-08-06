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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import tp.app.App;
import tp.controller.Mensaje;
import tp.controller.PlantaController;
import tp.dominio.Planta;

public class PanelPlantas extends PanelPersonalizado{
	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administraci�n de Plantas",SwingConstants.CENTER);
	
	private PlantaTM tableModel;
	private PlantaController controller = new PlantaController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel texto_nombre = new JLabel("Nombre:",SwingConstants.RIGHT);
	private JTextField campo_nombre = new JTextField();
	private JButton boton_agregar = botonAgregar("Agregar Planta");
	private JButton boton_eliminar = botonEliminar("Eliminar Planta");
	private JButton boton_stock_insumo = botonBusqueda("Ver Stock Insumo");
	
	private static Color color_borde =  Color.decode("#33658a");
	private static Color color_titulo =  Color.decode("#dd1c1a");
	private static Color color_letras = Color.BLACK;
	
	private void cambiarPanel(PanelPersonalizado p1) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        App app = (App) frame;
        app.cambiarPanel(p1);
	}
	
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
	}
	
	public PanelPlantas() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214));
		
		//TITULO
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		titulo.setForeground(color_titulo);
		
		//TABLA
		tableModel = new PlantaTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		tabla.setToolTipText("Hac� doble clic para editar el campo o presion� Supr para eliminar");
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow();
		               int column = target.getSelectedColumn();
		               String original = (String)tabla.getValueAt(row, column);
		               
		               switch(column) {
		               case 0:
		            	   break;
		               case 1:
		            	   String nuevo = ingresoPopUp("Ingres� otro valor para: "+original);
			               if(nuevo!=null && nuevo.length()>0) {
			            	   notificacionPopUp(controller.update(tableModel.getPlanta(row),new Planta(null,nuevo)));
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

		//BOTON ELIMINAR
		boton_eliminar.addActionListener(e->intentarEliminar());
		
		//BOTON VER STOCK
		boton_stock_insumo.addActionListener(e->{
				Integer row = tabla.getSelectedRow();
				if(row==-1) {
					notificacionPopUp(new Mensaje(false, "Ninguna fila seleccionada"));
				}else {
					cambiarPanel(new PanelStockInsumo(tableModel.getPlanta(row)));
				}
		});
		
		//BOTON AGREGAR
		boton_agregar.addActionListener( e ->{
			Mensaje m = controller.add(new Planta(null,campo_nombre.getText()));
			notificacionPopUp(m);
			if(m.exito()) {
				actualizarTabla();
			}
		});
	
	//PANEL1
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setOpaque(false);
		
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH,10,panel1,scroll_pane);
		colocar(0,1,1,1,1,1,0,0,GridBagConstraints.NONE,10,panel1,boton_stock_insumo);
		colocar(1,1,1,1,1,1,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
	//PANEL2

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setOpaque(false);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, color_borde);
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), color_borde);
		panel2.setBorder(borde2);
		texto_nombre.setForeground(color_letras);
		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre);
		colocar(1,0,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
}
