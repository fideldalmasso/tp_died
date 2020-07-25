package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import tp.controller.MarcaController;
import tp.controller.Mensaje;

public class PanelMarcas extends PanelPersonalizado {

	private static final long serialVersionUID = 1L;

	private JLabel titulo = new JLabel("Administración de Marcas",SwingConstants.CENTER);
	
	private MarcaTM tableModel;
	private MarcaController controller = new MarcaController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel texto_nombre = new JLabel("Nombre:",SwingConstants.RIGHT);
	private JTextField campo_nombre = new JTextField();
	private JButton boton_agregar = new JButton("Agregar Marca");
	private JButton boton_eliminar = new JButton("Eliminar Marca seleccionada");
	
	private void intentarEliminar() {
		int row = tabla.getSelectedRow();
		if(row == -1)
			popUp(new Mensaje(false, "Ninguna fila seleccionada"));
		else {
			String identificador = (String) tabla.getValueAt(row, 0);
			
			int resultado = JOptionPane.showOptionDialog(null, "¿Quiere eliminar "+identificador+"?", "Eliminar",JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resultado == JOptionPane.YES_OPTION) {
				popUp(controller.delete(identificador));
				actualizarTabla();
			}
			}
	}
	
	
	private void actualizarTabla() {
		tabla.repaint();
		tableModel.recargarTabla();
	}
	
	public PanelMarcas() {
		super();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(250, 216, 214)); //https://coolors.co/
		
	//TITULO------------------------------------------------------------------------------------------------
		titulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 24));
		//titulo.setOpaque(true);
		//titulo.setBackground(new Color(244,211,94));
		titulo.setForeground(new Color(42, 12, 78));
		
	//TABLA------------------------------------------------------------------------------------------------
		tableModel = new MarcaTM();
		tabla = new JTable();
		//tabla.setPreferredSize(new Dimension(300,200));
		tabla.setModel(tableModel);
		tabla.setIgnoreRepaint(false);
		tabla.setFont(new Font("Comic Sans MS",Font.PLAIN,16));
		tabla.getTableHeader().setFont(new Font("Comic Sans MS",Font.BOLD,17));
		tabla.setRowHeight(20);
		
		tabla.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {     // to detect doble click events
		               JTable target = (JTable)e.getSource();
		               int row = target.getSelectedRow(); // select a row
		               int column = target.getSelectedColumn(); // select a column
		               //JOptionPane.showMessageDialog(null, tabla.getValueAt(row, column)); // get the value of a row and column.
		               String original = (String)tabla.getValueAt(row, column);
		               String nuevo  = JOptionPane.showInputDialog(null, "Ingrese otro valor para: "+original); // get the value of a row and column.
		               
		               if(nuevo!=null && nuevo.length()>0) {
		            	   popUp(controller.update(original,nuevo));
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
		boton_eliminar.setForeground(Color.WHITE);
		boton_eliminar.setBackground(Color.RED);
		boton_eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				intentarEliminar();
			}
		});

		
	//CAMPO NOMBRE------------------------------------------------------------------------------------------------
		campo_nombre.addActionListener( e->{
			Mensaje m = controller.add(campo_nombre.getText());
			popUp(m);
			if(m.exito()) 
				actualizarTabla();
		});
		
	//BOTON AGREGAR------------------------------------------------------------------------------------------------
		boton_agregar.setForeground(Color.WHITE);
		boton_agregar.setBackground(Color.BLUE);
		boton_agregar.addActionListener( e ->
		{
			Mensaje m = controller.add(campo_nombre.getText());
			popUp(m);
			if(m.exito()) 
				actualizarTabla();
			
		}
	);
	
	//PANEL1------------------------------------------------------------------------------------------------
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setBackground(Color.WHITE);
		
		Border borde1 = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK);
		borde1 = BorderFactory.createTitledBorder(borde1, "Editar / Eliminar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.DARK_GRAY);
		panel1.setBorder(borde1);
		
		colocar(0,0,2,1,1,1,0,0,GridBagConstraints.BOTH, 10, panel1, scroll_pane);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel1,boton_eliminar);
		
	//PANEL2------------------------------------------------------------------------------------------------
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setBackground(Color.WHITE);
		
		Border borde2 = BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(48, 50, 61));
		borde2 = BorderFactory.createTitledBorder(borde2, "Agregar", TitledBorder.LEFT, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), Color.DARK_GRAY);
		panel2.setBorder(borde2);

		colocar(0,0,1,1,0,0,0,0,GridBagConstraints.NONE,GridBagConstraints.EAST,panel2,texto_nombre);
		colocar(1,0,1,1,1,0,0,0,GridBagConstraints.HORIZONTAL,10,panel2,campo_nombre);
		colocar(0,1,2,1,0,0,0,0,GridBagConstraints.NONE,10,panel2,boton_agregar);
		
	//ORGANIZACION DE PANELES------------------------------------------------------------------------------------------------	
		
		colocar(0,0,1,1,0,0,0,10 ,GridBagConstraints.NONE,10,this,titulo);
		colocar(0,1,1,1,1,1,0,0  ,GridBagConstraints.BOTH,10,this,panel1);
		colocar(0,2,1,1,0,0,200,0,GridBagConstraints.NONE,10,this,panel2);
		
	}
	
	
}







