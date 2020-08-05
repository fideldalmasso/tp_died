package tp.gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import tp.app.App;

public class PanelHome extends PanelPersonalizado {
	
	private JButton boton_insumos = botonPrincipal("Insumos", "icon/insumo.png");
	private JButton boton_plantas = botonPrincipal("Plantas", "icon/planta.png");
	private JButton boton_pedidos = botonPrincipal("Pedidos", "icon/pedido.png");
	private JButton boton_base_de_datos = botonPrincipal("Base de Datos", "icon/database.png");
	private JButton boton_envios= botonPrincipal("Analisis", "icon/grafico.png");
	private JButton boton_camiones = botonPrincipal("Camiones", "icon/truck.png");
	private JButton boton_rutas= botonPrincipal("Rutas", "icon/ruta.png");
	private JButton boton_salir = botonPrincipal("Salir", "icon/salir.png");
	
	static public JButton botonPrincipal(String mensaje, String filename) {
		JButton boton = new JButton(mensaje,PanelPersonalizado.emoji(filename, 64,64));
		boton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		boton.setFocusable(false);
		boton.setHorizontalTextPosition(SwingConstants.CENTER);
		boton.setVerticalTextPosition(SwingConstants.BOTTOM);
		//boton.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		return boton;
	}
	
	private void cambiarPanel(PanelPersonalizado p1) {
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        App app = (App) frame;
        app.cambiarPanel(p1);
	}

	public PanelHome() {
		super();
		this.setLayout(new GridLayout(0,3,20,20));
		this.fileFondo="icon/fondo.png";
		this.add(boton_insumos);
		this.add(boton_plantas);
		this.add(boton_pedidos);
		this.add(boton_base_de_datos);
		this.add(boton_envios);
		this.add(boton_camiones);
		this.add(boton_rutas);
		this.add(boton_salir);
		
		boton_insumos.addActionListener( e-> cambiarPanel(new PanelInsumos()));
		boton_camiones.addActionListener( e-> cambiarPanel(new PanelCamiones()));
		boton_plantas.addActionListener( e-> cambiarPanel(new PanelPlantas()));
		boton_rutas.addActionListener(e -> cambiarPanel(new PanelRutas()));
		boton_pedidos.addActionListener(e -> cambiarPanel(new PanelPedidos()));
		boton_base_de_datos.addActionListener(e -> cambiarPanel(new PanelDataBase()));
		boton_envios.addActionListener(e -> cambiarPanel(new PanelAnalisis()));
		boton_salir.addActionListener(e -> System.exit(0));
		
	}
	
	public JButton getBoton_rutas() {
		return boton_rutas;
	}

	public JButton getBoton_insumos() {
		return boton_insumos;
	}


	public JButton getBoton_plantas() {
		return boton_plantas;
	}


	public JButton getBoton_pedidos() {
		return boton_pedidos;
	}


	public JButton getBoton_base_de_datos() {
		return boton_base_de_datos;
	}


	public JButton getBoton_envios() {
		return boton_envios;
	}


	public JButton getBoton_camiones() {
		return boton_camiones;
	}


	public JButton getBoton_salir() {
		return boton_salir;
	}
	
}
