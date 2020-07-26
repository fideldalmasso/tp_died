package tp.app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;

import javax.swing.*;

import tp.gui.*;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuAyuda;
	JMenuItem menuItemMarcas;
	JMenuItem menuItemSalir;
	JToolBar toolBar;
	JButton boton_home;

	private App() {
		this.setTitle("App");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(8,61,119));
		//app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		//MENU------------------------------------------------------------------------------------------------
		
		this.menuBar = new JMenuBar();

		this.menuArchivo = new JMenu("Archivo");
		this.menuItemSalir = new JMenuItem("Salir");
		this.menuItemSalir.addActionListener( e -> System.exit(0));
		this.menuArchivo.add(menuItemSalir);

		this.menuEntidades = new JMenu("Entidades");
		this.menuItemMarcas = new JMenuItem("Marcas");
		PanelMarcas panel = new PanelMarcas();
		this.menuItemMarcas.addActionListener( e -> {
			//this.setContentPane(panel);
			this.add(panel,BorderLayout.CENTER);
			this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuEntidades.add(menuItemMarcas);

		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		
		this.setJMenuBar(menuBar);
		
		//TOOLBAR------------------------------------------------------------------------------------------------
		Image imagen = new ImageIcon("icon/335-home.png").getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
		ImageIcon icono = new ImageIcon(imagen);
		//boton_home = new JButton("Home"+ new String(Character.toChars(0x1F349)),icono);
		boton_home = new JButton("Home \uD83D\uDC7D",icono);
		boton_home.setBackground(new Color(250, 216, 214));
		boton_home.setForeground(Color.BLACK);
		boton_home.setFocusable(false);
		boton_home.setFont(new Font("NotoColorEmoji", Font.PLAIN, 12));
		System.out.println(boton_home.getFont().getName());
		//boton_home.setPreferredSize(new Dimension(64, 64));
		boton_home.addActionListener( e->{
			this.remove(panel);
			this.pack();
			this.revalidate();
			this.repaint();
		});
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.add(boton_home);
		toolBar.add(new JButton("Home"));
		
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
					
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("font/NotoColorEmoji.ttf")));
					
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				new App().setVisible(true);

			}
		});

	}
}
