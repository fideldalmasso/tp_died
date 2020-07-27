package tp.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import tp.gui.*;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuAyuda;
	JMenuItem menuItemMarcas;
	JMenuItem menuItemInsumos;
	JMenuItem menuItemSalir;
	JToolBar toolBar;
	JButton boton_home;
	JPanel actual = null;
	

	private App() {
		this.setTitle("App");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(8,61,119));
		
		//app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		
		// Aca se crea el PANEL de HOME
		JPanel panelHome = new PanelInsumos();
		panelHome.setBackground(Color.BLUE);
		actual = panelHome;
		this.getContentPane().add(panelHome,BorderLayout.CENTER);
		this.pack();
		this.revalidate();
		this.repaint();
		
		
		//MENU------------------------------------------------------------------------------------------------
		
		this.menuBar = new JMenuBar();

		this.menuArchivo = new JMenu("Archivo");
		this.menuItemSalir = new JMenuItem("Salir");
		this.menuItemSalir.addActionListener( e -> System.exit(0));
		this.menuArchivo.add(menuItemSalir);

		this.menuEntidades = new JMenu("Entidades");
		this.menuItemMarcas = new JMenuItem("Marcas");
		this.menuItemInsumos = new JMenuItem("Insumos");
		
		
		
		//Setea el boton "Marcas"
		PanelMarcas panelMarcas = new PanelMarcas();
		this.menuItemMarcas.addActionListener( e -> {
			//this.setContentPane(panel);
			this.remove(actual);
			this.actual = panelMarcas;
			this.add(panelMarcas);
			this.pack();
			this.revalidate();
			this.repaint();
		});
		
		//Setea el boton "Insumos"
				PanelInsumos panelInsumos = new PanelInsumos();
				this.menuItemInsumos.addActionListener( e -> {
					//this.setContentPane(panel);
					this.remove(actual);
					this.actual = panelInsumos;
					this.add(panelInsumos);
					this.pack();
					this.revalidate();
					this.repaint();
				});
				
		this.menuEntidades.add(menuItemMarcas);
		this.menuEntidades.add(menuItemInsumos);

		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		
		this.setJMenuBar(menuBar);
		
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
			this.remove(actual);
			actual = panelHome;
			this.getContentPane().add(panelHome,BorderLayout.CENTER);
			this.pack();
			this.revalidate();
			this.repaint();
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
				new App().setVisible(true);

			}
		});

	}
}
