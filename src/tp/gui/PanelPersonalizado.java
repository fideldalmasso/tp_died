package tp.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.controller.Mensaje;

public abstract class PanelPersonalizado extends JPanel{

	private static final long serialVersionUID = 1L;


	static public void popUp(Mensaje m) {
		if(m.exito()) 
			JOptionPane.showMessageDialog(null, "Operación exitosa","Éxito",JOptionPane.INFORMATION_MESSAGE);			
		else
			JOptionPane.showMessageDialog(null, m.texto(),"Error",JOptionPane.ERROR_MESSAGE);
	}

	
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
	
	
}
