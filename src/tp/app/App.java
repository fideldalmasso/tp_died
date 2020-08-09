package tp.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

import tp.controller.Mensaje;
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
	JButton boton_volver;
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
		
		if(p.getClass() == PanelHome.class && !DataBase.funciona) {
			Mensaje m = DataBase.leerJson();
			
			if(m.exito())
				DataBase.inicializarTablas();
			else {
				PanelPersonalizado.notificacionPopUp(m);
				cambiarPanel(new PanelDataBase());
			}
		}
		
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

		//TOOLBAR------------------------------------------------------------------------------------------------

		boton_home = new JButton("Home",PanelPersonalizado.emoji("icon/home.png", 32,32));
		boton_home.setFocusable(false);
		boton_home.addActionListener( e->{
			cambiarPanel(home);
		});
		
		boton_volver = new JButton("Volver",PanelPersonalizado.emoji("icon/back.png", 32, 32));
		boton_volver.setFocusable(false);
		boton_volver.addActionListener( e->{
			String panelActual = actual.getClass().toString().replace("class tp.gui.", "");
//			System.out.println(panelActual);
			switch(panelActual) {
			case "PanelHome":
				break;
			case "PanelAgregarPedido":
			case "PanelProcesarPedido":
			case "PanelDetallePedido":
				cambiarPanel(new PanelPedidos());
				break;
			
			default:
				cambiarPanel(new PanelHome());
			}
			
		});
		
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.setFloatable(false);

		toolBar.add(boton_home);
		toolBar.add(boton_volver);
		


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
