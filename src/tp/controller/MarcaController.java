package tp.controller;

import java.util.List;

import tp.dao.MarcaDAO;
import tp.dominio.Marca;
import tp.service.MarcaService;

public class MarcaController {

	MarcaService service;
	
	public MarcaController() {
		service = new MarcaService();
	}
	
	public Mensaje add(String nombre) {
		if(nombre!=null && nombre.length()>0) 
			return service.add(nombre);
		else
			return new Mensaje(false,"Error: nombre inválido");
			
	}
	
	public Mensaje delete(String nombre) {
		return service.delete(nombre);
	}
	
	public Mensaje update(String original, String nueva) {
		if(nueva!=null && nueva.length()>0) 
			return service.update(original,nueva);
		else 
			return new Mensaje(false,"Error: nombre inválido");
	}
	
	public List<Marca> getAll(){
		return service.getAll();
	}
	
}
