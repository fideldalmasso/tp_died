package tp.controller;

import java.util.List;

import tp.dominio.Planta;
import tp.service.PlantaService;

public class PlantaController {
	PlantaService service;
	
	public PlantaController() {
		service = new PlantaService();
	}
	
	public Mensaje add(String nombre) {
		if(nombre!=null && nombre.length()>0) 
			return service.add(nombre);
		else
			return new Mensaje(false,"Error: nombre inv�lido");
			
	}
	
	public Mensaje delete(String nombre) {
		return service.delete(nombre);
	}
	
	public Mensaje update(String original, String nueva) {
		if(nueva!=null && nueva.length()>0) 
			return service.update(original,nueva);
		else 
			return new Mensaje(false,"Error: nombre inv�lido");
	}
	
	public List<Planta> getAll(){
		return service.getAll();
	}
}
