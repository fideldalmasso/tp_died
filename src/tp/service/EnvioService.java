package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.EnvioDAO;
import tp.dominio.Envio;

public class EnvioService {
	EnvioDAO dao;
	
	public EnvioService(){
		dao = new EnvioDAO();
	}
	
	public Mensaje add(Envio env) {
		if(dao.add(env))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
	}
	
	public Mensaje delete(String id_envio) {
		if(dao.delete(id_envio))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Envio original, Envio nuevo) {
		if(dao.update(original,nuevo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	public List<Envio> getAll(){
		return dao.getAll();
	}
	
	public String getId(){
		return dao.getId();
	}
}
