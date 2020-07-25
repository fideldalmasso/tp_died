package tp.app;

import java.awt.Color;
import java.awt.Dimension;

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


	private App() {
		this.setTitle("App");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(8,61,119));
		//app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		this.menuBar = new JMenuBar();

		this.menuArchivo = new JMenu("Archivo");
		this.menuItemSalir = new JMenuItem("Salir");
		this.menuItemSalir.addActionListener( e -> System.exit(0));
		this.menuArchivo.add(menuItemSalir);

		this.menuEntidades = new JMenu("Entidades");
		this.menuItemMarcas = new JMenuItem("Marcas");
		this.menuItemMarcas.addActionListener( e -> {
			PanelMarcas panel = new PanelMarcas();
			this.setContentPane(panel);	
			this.pack();
			this.revalidate();
			this.repaint();
		});
		this.menuEntidades.add(menuItemMarcas);

		menuBar.add(this.menuArchivo);
		menuBar.add(this.menuEntidades );
		this.setJMenuBar(menuBar);
	}



	public static void main (String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					//					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			    
					//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			    
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				new App().setVisible(true);

			}
		});

	}
}
