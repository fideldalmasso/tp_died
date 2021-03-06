package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.RutaDAO;
import tp.dominio.Ruta;

public class RutaService {
	RutaDAO dao;
	
	public RutaService(){
		dao = new RutaDAO();
	}
	
	public Mensaje add(Ruta r1) {
		
		if(dao.add(r1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Ruta ya existente");
	}
	
	public Mensaje delete(String id_Ruta) {
		if(dao.delete(id_Ruta))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Ruta nueva) {
		
		if(dao.update(nueva))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se pudo actualizar la fila (error en la DB).");
				
	}
	
	public List<Ruta> getAll(){
		return dao.getAll();
	}
}
