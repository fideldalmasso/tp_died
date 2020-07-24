package tp.service;

import java.util.List;

import tp.dao.MarcaDAO;
import tp.dominio.Marca;

public class MarcaService {

	MarcaDAO dao;
	
	public MarcaService(){
		dao = new MarcaDAO();
	}
	
	public List<Marca> getAll(){
		return dao.getAll();
	}
	
	
}
