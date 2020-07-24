package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.MarcaDAO;
import tp.dominio.Marca;

public class MarcaService {

	MarcaDAO dao;
	
	public MarcaService(){
		dao = new MarcaDAO();
	}
	
	public Mensaje add(String nombre) {
		Marca m1 = new Marca(nombre);
		if(dao.add(m1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
	}
	
	public Mensaje update(String original, String nueva) {
		Marca m1 = new Marca(original);
		Marca m2 = new Marca(nueva);
		
		if(dao.update(m1,m2))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	
	public List<Marca> getAll(){
		return dao.getAll();
	}
	
	
}
