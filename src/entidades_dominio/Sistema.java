package entidades_dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import base_de_datos.*;


public class Sistema {
	BaseDeDatos db;
	List<Camion> lista_camiones;
	List<Planta> lista_plantas;
	List<Insumo> lista_insumos;
	List<Modelo> lista_modelos;
	List<Marca>  lista_marcas;
	List<Envio>  lista_envios;
	List<Pedido> lista_pedidos;
	List<Ruta>   lista_rutas;
	MarcaDAO marcaDAO;
	
	public Sistema() {
		lista_camiones = new ArrayList<Camion>();
		lista_plantas  = new ArrayList<Planta>();
		lista_insumos  = new ArrayList<Insumo>();
		lista_modelos  = new ArrayList<Modelo>();
		lista_marcas   = new ArrayList<Marca>();
		lista_envios   = new ArrayList<Envio>();
		lista_pedidos  = new ArrayList<Pedido>();
		lista_rutas    = new ArrayList<Ruta>();
		
		marcaDAO = new MarcaDAO();	
		//db = new BaseDeDatos();
		//db.inicializarDB();
	}
	
	
	
	public void agregarMarca(String nombre) {
		
		Marca m = new Marca(nombre);
		if(this.marcaDAO.getMarca(nombre).isEmpty())
			this.marcaDAO.add(m);
		System.out.println("Marca #"+nombre+" agregada con exito");

	}
//	public String agregarPlanta(String id, String nombre) {
//		if(db.existe("Planta", "id_planta", id)) 
//			return "La planta #"+id+" ya existe en la bd";
//		Planta temp = new Planta(id,nombre);
//		db.insert(temp);
//		this.lista_plantas.add(temp);
//		return "Planta #"+id+" agregada con exito";
//	}
//	public String agregarModelo(String nombre, Marca marca ) {
//		if(db.existe("Modelo", "nombre", nombre))
//			return "El Modelo #"+nombre+" ya existe en la bd";
//		Modelo temp = new Modelo(nombre, marca);
//		db.insert(temp);
//		this.lista_modelos.add(temp);
//		return "Modelo #"+nombre+" agregada con exito";
//	}
//	
//	public String agregarCamion(String id, Planta planta, Modelo modelo, Double costo_por_km, Double costo_por_hora, LocalDateTime fecha_de_compra) {
//		if(db.existe("Camion", "id_camion", id))
//			return "El camion #"+id+" ya existe en la bd";
//		Camion temp = new Camion(id,planta,modelo,costo_por_km,costo_por_hora,fecha_de_compra);
//		db.insert(temp);
//		this.lista_camiones.add(temp);
//		return "Camion #"+id+" agregado con exito";
//	}
}
