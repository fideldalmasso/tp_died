package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.InsumoDAO;
import tp.dao.InsumoGeneralDAO;
import tp.dominio.Insumo;
import tp.dominio.InsumoGeneral;
import tp.enumerados.Unidad;

public class InsumoGeneralService {

	InsumoGeneralDAO dao;
	InsumoDAO insumoDao;
	InsumoService insumoSer ;
	public InsumoGeneralService(){
		dao = new InsumoGeneralDAO();
		insumoDao = new InsumoDAO();
		insumoSer = new InsumoService();
	}
	
	public Mensaje add(String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double peso) {
		
		Insumo insumo = new Insumo(descripcion, unidadDeMedida, costoPorUnidad);
		insumoDao.add(insumo);
		InsumoGeneral m1 = new InsumoGeneral( insumoDao.getID(insumo.getDescripcion()) ,descripcion, unidadDeMedida, costoPorUnidad,peso);
		if(dao.add(m1)) {
			return new Mensaje(true,"");}
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
	
	public Mensaje update(String id_original, String id_nuevo, String descripcion_nuevo, Unidad unidadDeMedida_nuevo, Double costoPorUnidad_nuevo,Double peso) {
		
		insumoSer.update(id_original, id_nuevo, descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo);
		InsumoGeneral m1 = new InsumoGeneral(id_original," ",unidadDeMedida_nuevo,0d,0d);
		InsumoGeneral m2 = new InsumoGeneral( id_nuevo,descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo, peso);
		
		if(dao.update(m1,m2))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"Error al actualizar la tabla");
				
	}
	
	
	public List<InsumoGeneral> getAll(){
		return dao.getAll();
	}
	
	
}
