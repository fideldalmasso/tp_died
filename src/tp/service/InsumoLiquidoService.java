package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.InsumoDAO;
import tp.dao.InsumoLiquidoDAO;
import tp.dao.MarcaDAO;
import tp.dominio.Insumo;
import tp.dominio.InsumoLiquido;
import tp.dominio.Marca;
import tp.enumerados.Unidad;

public class InsumoLiquidoService {

	InsumoLiquidoDAO dao;
	InsumoDAO insumoDao;
	InsumoService insumoSer = new InsumoService();
	public InsumoLiquidoService(){
		dao = new InsumoLiquidoDAO();
	}
	
	public Mensaje add(String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double densidad) {
		
		Insumo insumo = new Insumo(descripcion, unidadDeMedida, costoPorUnidad);
		insumoDao.add(insumo);
		InsumoLiquido m1 = new InsumoLiquido( insumoDao.getID(insumo.getDescripcion()) ,descripcion, unidadDeMedida, costoPorUnidad,densidad);
		if(dao.add(m1))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"ID ya existente");
	}
	
	public Mensaje delete(String id) {
		return insumoSer.delete(id);
//		if(dao.delete(id))
//			return new Mensaje(true,"");
//		else
//			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(String id_original, String id_nuevo, String descripcion_nuevo, Unidad unidadDeMedida_nuevo, Double costoPorUnidad_nuevo,Double densidad) {
		
		insumoSer.update(id_original, id_nuevo, descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo);
		InsumoLiquidoDAO insumoLiquidoDAO = new InsumoLiquidoDAO() ;
		InsumoLiquido m1 = new InsumoLiquido(id_original," ",unidadDeMedida_nuevo,0d,0d);
		InsumoLiquido m2 = new InsumoLiquido( id_nuevo,descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo, densidad);
		
		if(dao.update(m1,m2))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Error al actualizar la tabla");
				
	}
	
	
	public List<InsumoLiquido> getAll(){
		return dao.getAll();
	}
	
	
}
