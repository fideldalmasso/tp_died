package tp.gui;

import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.controller.Mensaje;

public abstract class PanelPersonalizado extends JPanel{

	private static final long serialVersionUID = 1L;


	static public void notificacionPopUp(Mensaje m) {
		if(m.exito()) 
			JOptionPane.showMessageDialog(null, "Operación exitosa","Éxito",JOptionPane.INFORMATION_MESSAGE,emoji("icon/success.png", 24,24));			
		else
			JOptionPane.showMessageDialog(null, m.texto(),"Error",JOptionPane.ERROR_MESSAGE,PanelPersonalizado.emoji("icon/error.png", 24,24));
	}
	
	static public String ingresoPopUp(String mensaje) {
		 return (String) JOptionPane.showInputDialog(null, mensaje, "Ingreso", JOptionPane.OK_CANCEL_OPTION, emoji("icon/pencil.png", 24,24), null, null);	 
	}
	
	static public int eliminarPopUp(String mensaje) {
		return JOptionPane.showOptionDialog(null, mensaje, "Eliminar",JOptionPane.OK_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE, PanelPersonalizado.emoji("icon/warning.png", 24,24), null, null);
	}
	
	static public String seleccionPopUp(String[] valores) {
		return (String) JOptionPane.showInputDialog(null, "Seleccione un valor de la lista", "Ingreso",JOptionPane.OK_CANCEL_OPTION, emoji("icon/pencil.png", 24,24), valores, null);
	}
	
	static public JButton botonEliminar(String mensaje) {
		return new JButton(mensaje,emoji("icon/trash.png", 24,24));
	}
	
	static public JButton botonAgregar(String mensaje) {
		return new JButton(mensaje,emoji("icon/save.png", 24,24));
	}
	
	static public JButton botonEditar(String mensaje) {
		return new JButton(mensaje,emoji("icon/pencil.png",24,24));
	}
	

	//USAR ESTE MÉTODO SOLO CON JPANEL QUE TENGAN GRIDBAGLAYOUT
    static public void colocar(
    		int column,
    		int row, 
    		int width, 
    		int height, 
    		double weightX, 
    		double weightY, 
    		int ipadx, 
    		int ipady, 
            int fill, 
            int anchor, 
            JPanel panel, 
            JComponent comp ){
    	//https://stackoverflow.com/questions/45175343/how-do-you-add-empty-cells-to-gridbaglayout
       //GridBagConstraints constraints = new GridBagConstraints();
    	GridBagConstraints c1 = new GridBagConstraints();
       c1.gridx = column;      // column to start
       c1.gridy = row;         // row to start
       c1.gridwidth = width;   // number of cells wide
       c1.gridheight = height; // number of cells tall
       c1.weightx = weightX;   // when size is changed, grow in x direction
       c1.weighty = weightY;   // when size is changed, grow in y direction
       c1.ipadx = ipadx;       // espacio extra en x
       c1.ipady = ipady;		  // espacio extra en y
       c1.fill = fill;         // 0 NONE, 1 BOTH, 2 HORIZONTAL, 3 VERTICAL
       c1.anchor = anchor; 	  //10 CENTER
       c1.insets = new Insets(5,10,5,10);
       //c.insets = new Insets( marginTop, marginLeft, marginBottom, marginRight );
       panel.add(comp,c1);  
       
    }
    
    static public ImageIcon emoji(String fileName, int width, int height) {
    	Image imagen = new ImageIcon(fileName).getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
    }
	
	
}
