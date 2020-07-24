package tp.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tp.controller.MarcaController;

public class PanelMarcas extends JPanel {


	private JButton btn1 = new JButton("Boton 1");
	private JButton btn2 = new JButton("Boton 2");
	private JButton btn3 = new JButton("Boton 3");
	private JButton btn4 = new JButton("Boton 4");
	
	private MarcaTM tableModel;
	private MarcaController controller;
	private JScrollPane scrollPane;
	
	JTable tabla;
	
	public PanelMarcas() {
		super();
		this.armarPanel();
	}
	
	private void armarPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		controller = new MarcaController();
		this.setBackground(Color.GRAY);
		this.setLayout(gbl);
		
		c.fill = GridBagConstraints.BOTH;
		//c1.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight= 1;
		this.add(btn1,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight= 1;
		this.add(btn2,c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight= 2;
		this.add(btn3,c);
		
		//c1.ipady = 40;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight= 1;
		this.add(btn4,c);
		
		tableModel = new MarcaTM(controller.getAll());
		tabla = new JTable();
		tabla.setModel(tableModel);
		scrollPane = new JScrollPane(tabla);
		tabla.setFillsViewportHeight(true);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		this.add(scrollPane,c);
		
	}
	
}
