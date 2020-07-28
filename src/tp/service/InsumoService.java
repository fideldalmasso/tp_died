package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.InsumoDAO;
import tp.dao.MarcaDAO;
import tp.dominio.Insumo;
import tp.dominio.Marca;
import tp.enumerados.Unidad;

public class InsumoService {

	InsumoDAO dao;
	
	public InsumoService(){
		dao = new InsumoDAO();
	}
	
	public Mensaje add(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad) {
		Insumo m1 = new Insumo(id, descripcion, unidadDeMedida, costoPorUnidad);
		if(dao.add(m1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"ID ya existente");
	}
	
	public Mensaje delete(String id) {
		if(dao.delete(id))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(String id_original, String id_nuevo, String descripcion_nuevo, Unidad unidadDeMedida_nuevo, Double costoPorUnidad_nuevo) {
		InsumoDAO insumoDAO = new InsumoDAO() ;
		Insumo m1 = insumoDAO.get(id_original).get() ;
		Insumo m2 = new Insumo(id_nuevo, descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo);
		
		if(dao.update(m1,m2))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"ID ya existente");
				
	}
	
	
	public List<Insumo> getAll(){
		return dao.getAll();
	}
	
	
}
