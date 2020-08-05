package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.StockInsumoDAO;
import tp.dominio.Insumo;
import tp.dominio.Planta;
import tp.dominio.StockInsumo;

public class StockInsumoService {
	StockInsumoDAO dao;
	
	public StockInsumoService(){
		dao = new StockInsumoDAO();
	}
	
	public Mensaje add(StockInsumo s) {
		if(dao.add(s))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
	}
	
	public Mensaje addAll(Planta planta) {
		if(dao.addAll(planta)) {
			return new Mensaje(true,"");
		}else {
			return new Mensaje(false,"");
		}
	}
	
	public Mensaje addAll(Insumo insumo) {
		if(dao.addAll(insumo)) {
			return new Mensaje(true,"");
		}else {
			return new Mensaje(false,"");
		}
	}
	
	public Mensaje delete(StockInsumo s) {
		if(dao.delete(s))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Integer stockNuevo, Integer ppNuevo, Integer planta, Integer insumo) {
		
		if(dao.update(stockNuevo, ppNuevo, planta, insumo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Nombre ya existente");
				
	}
	
	public List<StockInsumo> getAll(){
		return dao.getAll();
	}
	
}

