package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.PlantaDAO;
import tp.dominio.Planta;

public class PlantaService {
	PlantaDAO dao;
	
	public PlantaService(){
		dao = new PlantaDAO();
	}
	
	public Mensaje add(String nombre) {
		Planta m1 = new Planta(nombre);
		if(dao.add(m1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
	}
	
	public Mensaje delete(String nombre) {
		if(dao.delete(nombre))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(String original, String nueva) {
		Planta m1 = new Planta(original);
		Planta m2 = new Planta(nueva);
		
		if(dao.update(m1,m2))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	
	public List<Planta> getAll(){
		return dao.getAll();
	}
	
}
