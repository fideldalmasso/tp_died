package tp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PanelHome extends PanelPersonalizado {
	
	private JButton boton_insumos = botonPrincipal("Insumos", "icon/insumo.png");
	private JButton boton_plantas = botonPrincipal("Plantas", "icon/planta.png");
	private JButton boton_pedidos = botonPrincipal("Pedidos", "icon/pedido.png");
	private JButton boton_base_de_datos = botonPrincipal("Base de Datos", "icon/database.png");
	private JButton boton_envios= botonPrincipal("Envios", "icon/envio.png");
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
	

	public PanelHome() {
		
		this.setLayout(new GridLayout(0,3,20,20));
		
		this.add(boton_insumos);
		this.add(boton_plantas);
		this.add(boton_pedidos);
		this.add(boton_base_de_datos);
		this.add(boton_envios);
		this.add(boton_camiones);
		this.add(boton_rutas);
		this.add(boton_salir);
		
		//this.setBackground(Color.BLACK);
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






