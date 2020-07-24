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
	
	public String update(String original, String nueva) {
		if(nueva!=null && nueva.length()>0) {
			//
		}
		return "xd";
	}
	
	public List<Marca> getAll(){
		return service.getAll();
	}
	
}
