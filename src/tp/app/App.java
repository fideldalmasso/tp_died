package tp.app;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import tp.dominio.*;
import tp.gui.*;

public class App extends JFrame {
	
	JMenuBar menuBar;
	JMenu menuArchivo;
	JMenu menuEntidades;
	JMenu menuAyuda;
	JMenuItem menuItemMarcas;
	JMenuItem menuItemSalir;
	
	
	private App() {}
	
	public void run() {
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
			            // Set System L&F
//			          UIManager.setLookAndFeel(
//			          UIManager.getSystemLookAndFeelClassName());
//					  UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");			    }
					  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			    }
					  
			    catch (UnsupportedLookAndFeelException e) {
			       // handle exception
			    }
			    catch (ClassNotFoundException e) {
			       // handle exception
			    }
			    catch (InstantiationException e) {
			       // handle exception
			    }
			    catch (IllegalAccessException e) {
			       // handle exception
			    }
					App app = new App();
					app.setTitle("Titulo");
					app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					app.setPreferredSize(new Dimension(800, 600));
					app.setSize(800, 600);
					app.run();
					//app.setExtendedState(app.getExtendedState() | JFrame.MAXIMIZED_BOTH);
					app.setVisible(true);
			}
		});
		
		

		
	}
}
