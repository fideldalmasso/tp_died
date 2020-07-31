package tp.controller;

import java.util.List;

import tp.dominio.Planta;
import tp.service.PlantaService;

public class PlantaController {
	PlantaService service;
	
	public PlantaController() {
		service = new PlantaService();
	}
	
	public Mensaje add(Planta p1) {
		if(p1.getNombre()!=null && p1.getNombre().length()>0) 
			return service.add(p1);
		else
			return new Mensaje(false,"Error: nombre inv�lido");
			
	}
	
	public Mensaje delete(String id_planta) {
		return service.delete(id_planta);
	}
	
	public Mensaje update(Planta original, Planta nueva) {
		if(nueva!=null && nueva.getNombre().length()>0) 
			return service.update(original,nueva);
		else 
			return new Mensaje(false,"Error: nombre inv�lido");
	}
	
	public List<Planta> getAll(){
		return service.getAll();
	}
}
