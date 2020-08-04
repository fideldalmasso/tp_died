package tp.controller;

import java.util.List;

import tp.dominio.ASeguirEn;
import tp.dominio.Envio;
import tp.dominio.Ruta;
import tp.service.ASeguirEnService;

public class ASeguirEnController {
	ASeguirEnService service;
	
	public ASeguirEnController() {
		service = new ASeguirEnService();
	}
	
	public Mensaje add(String id_envio, String id_ruta, Integer orden) { 
		Envio envio = new Envio(id_envio,null,null);
		Ruta ruta = new Ruta(id_ruta,null,null,null,null,null);
		return service.add(new ASeguirEn(envio,ruta,orden));
	}
	
	public Mensaje delete(String id_envio) {
		return service.delete(id_envio);
	}
	
	public Mensaje update(String id_envio, String id_ruta, Integer orden) {
		Envio envio = new Envio(id_envio,null,null);
		Ruta ruta = new Ruta(id_ruta,null,null,null,null,null);
		return service.update(new ASeguirEn(envio,ruta,orden));
	}
	
	public List<ASeguirEn> getAll(){
		return service.getAll();
	}
}
