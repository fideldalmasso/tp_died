package tp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class PanelMarcas extends JPanel {

	private JTextField texto1;
	private JTextField texto2;
	JButton btn1 = new JButton("Boton 1");
	JButton btn2 = new JButton("Boton 2");
	JButton btn3 = new JButton("Boton 3");
	JButton btn4 = new JButton("Boton 4");
	JButton btn5 = new JButton("Boton 5");
	JButton btn6 = new JButton("Boton 6");
	JButton btn7 = new JButton("Boton 7");
	JButton btn8 = new JButton("Boton 8");
	
	JTable tablaCamiones;
	
	public PanelMarcas() {
		super();
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.setBackground(Color.GRAY);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c1 = new GridBagConstraints();
		this.setLayout(gbl);
		
		c1.fill = GridBagConstraints.BOTH;
		//c1.anchor = GridBagConstraints.CENTER;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 1;
		c1.gridheight= 1;
		this.add(btn1,c1);
		
		c1.gridx = 1;
		c1.gridy = 0;
		c1.gridwidth = 1;
		c1.gridheight= 1;
		this.add(btn2,c1);
		
		c1.gridx = 2;
		c1.gridy = 0;
		c1.gridwidth = 2;
		c1.gridheight= 2;
		this.add(btn3,c1);
		
		//c1.ipady = 40;
		c1.gridx = 0;
		c1.gridy = 1;
		c1.gridwidth = 2;
		c1.gridheight= 1;
		this.add(btn4,c1);
		
	}
	
}
