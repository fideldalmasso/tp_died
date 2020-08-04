package tp.service;

import java.time.LocalDate;
import java.util.List;

import tp.controller.Mensaje;
import tp.dao.CamionDAO;
import tp.dao.ModeloDAO;
import tp.dao.PlantaDAO;
import tp.dominio.Camion;
import tp.dominio.Modelo;
import tp.dominio.Planta;

public class CamionService {

	CamionDAO dao;
	
	public CamionService(){
		dao = new CamionDAO();
	}
	
	public Mensaje add(String id_camion, String id_planta, String nombre_modelo, Double distancia_recorrida_en_km,
			Double costo_por_km, Double costo_por_hora, LocalDate fecha_de_compra) {
		
		Planta plantaTemp = new PlantaDAO().get(id_planta).get();
		Modelo modeloTemp= new ModeloDAO().get(nombre_modelo).get();
		
		Camion c1 = new Camion(id_camion, 
								plantaTemp, 
								modeloTemp, 
								distancia_recorrida_en_km, 
								costo_por_km, 
								costo_por_hora, 
								fecha_de_compra);
		if(dao.add(c1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"id_camion ya existente");
	}
	
	public Mensaje delete(String id_camion) {
		if(dao.delete(id_camion))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Camion original, Camion nuevo) {
		return new Mensaje(dao.update(original, nuevo),"No se puedo actualizar la fila (error en la DB).");
	}
	
//	public Mensaje update(String original, String nueva) {
//		Marca m1 = new Marca(original);
//		Marca m2 = new Marca(nueva);
//		
//		if(dao.update(m1,m2))
//			return new Mensaje(true,"");
//		else
//			return new Mensaje(false,"Nombre ya existente");
//				
//	}
	
	
	public List<Camion> getAll(){
		return dao.getAll();
	}
	
	public Camion getDisponible(String nombre_planta){
		return dao.getDisponible(nombre_planta);
	}
	
}
