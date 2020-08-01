package tp.controller;

import java.util.List;

import tp.dao.MarcaDAO;
import tp.dominio.Insumo;
import tp.dominio.InsumoGeneral;
import tp.dominio.Marca;
import tp.enumerados.Unidad;
import tp.service.InsumoGeneralService;
import tp.service.InsumoService;
import tp.service.MarcaService;

public class InsumoGeneralController {

	InsumoGeneralService service;
	
	public InsumoGeneralController() {
		service = new InsumoGeneralService();
	}
	
	public Mensaje add(String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double peso) {
		if(unidadDeMedida == null) {
			return new Mensaje(false,"Error: Unidad vacía");
		}
		if(costoPorUnidad == null && costoPorUnidad > 0) {
			return new Mensaje(false,"Error: Costo vacío o nulo");
		}
		
			return service.add(descripcion, unidadDeMedida, costoPorUnidad, peso);
			
	}
	
	public Mensaje delete(String id) {
		return service.delete(id);
	}
	
	public Mensaje update(String id_original, String id_nuevo, String descripcion_nuevo, Unidad unidadDeMedida_nuevo, Double costoPorUnidad_nuevo, Double peso) {
		if(unidadDeMedida_nuevo == null) {
			return new Mensaje(false,"Error: Unidad vacía");
		}
		if(costoPorUnidad_nuevo == null && costoPorUnidad_nuevo > 0) {
			return new Mensaje(false,"Error: Costo vacío o nulo");
		}
		if(id_nuevo!=null && id_nuevo.length()>0) 
			return service.update(id_original,id_nuevo, descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo, peso);
		else 
			return new Mensaje(false,"Error: ID inválido");
	}
	
	public List<InsumoGeneral> getAll(){
		return service.getAll();
	}
	
}
