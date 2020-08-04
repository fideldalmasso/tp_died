package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.PlantaDAO;
import tp.dao.StockInsumoDAO;
import tp.dominio.Planta;

public class PlantaService {
	PlantaDAO dao;
	
	public PlantaService(){
		dao = new PlantaDAO();
	}
	
	public Mensaje add(Planta p1) {
		
		if(dao.add(p1)) {
			StockInsumoDAO sid = new StockInsumoDAO();
			sid.addAll(p1);
				return new Mensaje(true,"");
			}
		else
			return new Mensaje(false,"Nombre ya existente");
	}
	
	public Mensaje delete(String id_planta) {
		if(dao.delete(id_planta))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Planta original, Planta nueva) {
		
		if(dao.update(original,nueva))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	
	public List<Planta> getAll(){
		return dao.getAll();
	}
	
}
