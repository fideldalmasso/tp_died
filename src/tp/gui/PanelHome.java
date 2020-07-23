package tp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHome extends JPanel{

	
	JButton btn1 = new JButton("Boton 1");
	JButton btn2 = new JButton("Boton 299999999");
	JButton btn3 = new JButton("Boton 3");
	JButton btn4 = new JButton("Boton 4");
	
	
	public void inicializar() {
		
		BorderLayout bl = new BorderLayout(20,20);
		this.setLayout(bl);

		JPanel subPanelBotones = new JPanel();
		
		FlowLayout flowLm = new FlowLayout();
		flowLm.setHgap(50);
		flowLm.setAlignment(FlowLayout.RIGHT);
		
		subPanelBotones.setLayout(flowLm);
		subPanelBotones.setBackground(Color.BLUE);
		subPanelBotones.setPreferredSize(new Dimension(400,100));
		subPanelBotones.add(btn1);
		subPanelBotones.add(btn2);
		subPanelBotones.add(btn3);
		subPanelBotones.add(btn4);
		btn1.setSize(500, 20);
		btn2.setForeground(Color.GREEN);
		btn3.setSize(new Dimension(80, 40));
		
		// FLOW 
		btn4.setPreferredSize(new Dimension(280, 90));

		btn4.setMinimumSize(new Dimension(50, 25));
		btn4.setMaximumSize(new Dimension(150, 30));
		btn4.setSize(125, 50);

		this.add(subPanelBotones, BorderLayout.SOUTH);
		JLabel etiqueta1 = new JLabel("ETIQUETA 1");
		this.add(etiqueta1, BorderLayout.CENTER);
		JLabel etiqueta2 = new JLabel("ETIQUETA 2");
		this.add(etiqueta2, BorderLayout.CENTER);
		JLabel etiqueta3 = new JLabel("ETIQUETA 3");
		etiqueta3.setPreferredSize(new Dimension(400, 300));
		this.add(etiqueta3, BorderLayout.CENTER);
	
	}
	
}
