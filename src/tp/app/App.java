package tp.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

import tp.dao.DataBase;
import tp.gui.*;

public class App extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuAyuda;
	JMenuItem menuItemMarcas;
	JMenuItem menuItemInsumos;
	JMenuItem menuItemPlantas;
	JMenuItem menuItemSalir;
	JToolBar toolBar;
	JButton boton_home;
	JPanel actual = null;


	public void cambiarPanel(PanelPersonalizado p) {
		//this.setContentPane(panel);
		Dimension d = this.getSize();
		this.remove(actual);
		this.actual = p;
		this.add(this.actual);
		this.pack();
		this.revalidate();
		this.repaint();
		this.setSize(d);
	}

	private App() {
		this.setTitle("App");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	//	this.setPreferredSize(new Dimension(800,600));
		this.setMinimumSize(new Dimension(800,800));
		//this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
		
	
		//https://stackoverflow.com/questions/2781939/setting-minimum-size-limit-for-a-window-in-java-swing
		this.addComponentListener(new ComponentAdapter(){
	        public void componentResized(ComponentEvent e){
	            Dimension d=getSize();
	            Dimension minD=getMinimumSize();
	            if(d.width<minD.width)
	                d.width=minD.width;
	            if(d.height<minD.height)
	                d.height=minD.height;
	            setSize(d);
	        }
	    });
		

		// Aca se crea el PANEL de HOME
		
		this.actual = new JPanel();
		PanelHome home =new PanelHome(); 
		this.cambiarPanel(home);
		
		//MENU------------------------------------------------------------------------------------------------

		/*
		 * this.menuBar = new JMenuBar();
		 * 
		 * this.menuArchivo = new JMenu("Archivo"); this.menuItemSalir = new
		 * JMenuItem("Salir"); this.menuItemSalir.addActionListener( e ->
		 * System.exit(0)); this.menuArchivo.add(menuItemSalir);
		 * 
		 * this.menuEntidades = new JMenu("Entidades"); this.menuItemMarcas = new
		 * JMenuItem("Marcas"); this.menuItemInsumos = new JMenuItem("Insumos");
		 * this.menuItemPlantas = new JMenuItem("Plantas");
		 * 
		 * 
		 * //Setea el boton "Marcas"
		 * 
		 * this.menuItemMarcas.addActionListener( e -> { cambiarPanel(new
		 * PanelMarcas()); });
		 * 
		 * //Setea el boton "Insumos"
		 * 
		 * this.menuItemInsumos.addActionListener( e -> { cambiarPanel(new
		 * PanelInsumos()); });
		 * 
		 * 
		 * //Setea el botón "Plantas" this.menuItemPlantas.addActionListener( e -> {
		 * cambiarPanel(new PanelPlantas()); });
		 * 
		 * this.menuEntidades.add(menuItemMarcas);
		 * this.menuEntidades.add(menuItemInsumos);
		 * this.menuEntidades.add(menuItemPlantas);
		 * 
		 * menuBar.add(this.menuArchivo); menuBar.add(this.menuEntidades );
		 * 
		 * this.setJMenuBar(menuBar);
		 */

		//TOOLBAR------------------------------------------------------------------------------------------------

		//boton_home = new JButton("Home"+ new String(Character.toChars(0x1F349)),icono);
		boton_home = new JButton("Home",PanelPersonalizado.emoji("icon/home.png", 32,32));
		//boton_home.setBackground(new Color(250, 216, 214));
		//boton_home.setForeground(Color.BLACK);
		boton_home.setFocusable(false);
		//boton_home.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		//System.out.println(boton_home.getFont().getName());
		//boton_home.setPreferredSize(new Dimension(64, 64));
		boton_home.addActionListener( e->{
			cambiarPanel(home);
		});
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.add(boton_home);
		//toolBar.add(new JButton("Home"));

		toolBar.setFloatable(false);

		this.add(toolBar,BorderLayout.NORTH);

	}



	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					//										UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			    
					//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

					//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					//ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/NotoColorEmoji.ttf")));

				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				DataBase.resetDB();
				new App().setVisible(true);
				
			}
		});

	}
}
