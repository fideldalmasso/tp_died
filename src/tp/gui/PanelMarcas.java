package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import tp.controller.MarcaController;
import tp.controller.Mensaje;

public class PanelMarcas extends JPanel {

	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	private JLabel titulo = new JLabel("Administración de Marcas",SwingConstants.CENTER);
	
	private MarcaTM tableModel;
	private MarcaController controller = new MarcaController();
	private JScrollPane scroll_pane;
	private JTable tabla;
	
	private JLabel nombre = new JLabel("Nombre:",SwingConstants.RIGHT);
	private JTextField campo_nombre = new JTextField();
	private JButton boton_agregar = new JButton("+ Agregar");
	
	public PanelMarcas() {
		super();
		this.armarPanel();
	}
	
	private void popUp(Mensaje m) {
		if(m.exito()) 
			JOptionPane.showMessageDialog(null, "Operación exitosa","Éxito",JOptionPane.INFORMATION_MESSAGE);			
		else
			JOptionPane.showMessageDialog(null, m.texto(),"Error",JOptionPane.WARNING_MESSAGE);
	}
	
    private void colocar( 
    		JComponent comp, 
    		int column, 
    		int row, 
    		int width, 
    		int height, 
    		double weightX, 
    		double weightY, 
            int ipadx, 
            int ipady, 
            int fill, 
            int anchor ){
    	//https://stackoverflow.com/questions/45175343/how-do-you-add-empty-cells-to-gridbaglayout
       //GridBagConstraints constraints = new GridBagConstraints();
       c.gridx = column;      // column to start
       c.gridy = row;         // row to start
       c.gridwidth = width;   // number of cells wide
       c.gridheight = height; // number of cells tall
       c.weightx = weightX;   // when size is changed, grow in x direction
       c.weighty = weightY;   // when size is changed, grow in y direction
       c.ipadx = ipadx;       // espacio extra en x
       c.ipady = ipady;		  // espacio extra en y
       c.fill = fill;         // 0 NONE, 1 BOTH, 2 HORIZONTAL, 3 VERTICAL
       c.anchor = anchor; 	  //10 CENTER
       //c.insets = new Insets( marginTop, marginLeft, marginBottom, marginRight );
       //gbl.setConstraints(comp, constraints);
       this.add(comp,c);  
    }
    
	private void armarPanel() {
		
		//this.setBackground(Color.GRAY);
		this.setLayout(gbl);
		
		titulo.setFont(new Font("Calibri", Font.BOLD, 24));
		
		tableModel = new MarcaTM();
		tabla = new JTable();
		tabla.setModel(tableModel);
		
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
		            	   tableModel.recargarTabla();
		               }
		               //tabla.repaint();
		               //campo_nombre.setText(tabla.getValueAt(row,0).toString()); 
		            }
			}
		});
		scroll_pane = new JScrollPane(tabla);
		//tabla.setFillsViewportHeight(true);

		nombre.setFont(new Font("Calibri", Font.BOLD, 16));

		campo_nombre.setFont(new Font("Calibri", Font.BOLD, 16));
		
		//titulo.setBorder(new LineBorder(Color.BLUE));
		
		boton_agregar.addActionListener( e ->
		{
			Mensaje m = controller.add(campo_nombre.getText());
			popUp(m);
			if(m.exito()) {
				tableModel.recargarTabla();
				this.repaint();
			}
				
			
		}
	);
		
		colocar(titulo,0,0,3,1,0,0,0,50,0,10);
		colocar(scroll_pane,0,1,3,1,1,1,0,0,1,10);
		colocar(nombre,0,2,1,1,0,0,0,0,0,10);
		colocar(campo_nombre,1,2,1,1,1,0,0,0,2,10);
		colocar(boton_agregar,2,2,1,1,0,0,0,0,0,10);
		
	}
	
}
