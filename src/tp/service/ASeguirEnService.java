package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.ASeguirEnDAO;
import tp.dominio.ASeguirEn;

public class ASeguirEnService {
	ASeguirEnDAO dao;
	
	public ASeguirEnService(){
		dao = new ASeguirEnDAO();
	}
	
	public Mensaje add(ASeguirEn as) {
		if(dao.add(as))
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
	
	public Mensaje update(ASeguirEn nuevo) {
		if(dao.update(nuevo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	public List<ASeguirEn> getAll(){
		return dao.getAll();
	}
}
