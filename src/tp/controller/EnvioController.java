package tp.controller;

import java.util.List;

import tp.dominio.Envio;
import tp.service.EnvioService;

public class EnvioController {
	EnvioService service;
	
	public EnvioController() {
		service = new EnvioService();
	}
	
	public Mensaje add(Envio env) {
			return service.add(env);
	}
	
	public Mensaje delete(String nombre) {
		return service.delete(nombre);
	}
	
	public Mensaje update(Envio original, Envio nuevo) {
		return service.update(original,nuevo);
	}
	
	public List<Envio> getAll(){
		return service.getAll();
	}
	
	public String getId(){
		return service.getId();
	}
}
