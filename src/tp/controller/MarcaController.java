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
	
	public List<Marca> getAll(){
		return service.getAll();
	}
	
}
