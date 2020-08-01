package tp.controller;

import java.util.List;

import tp.dao.MarcaDAO;
import tp.dominio.Insumo;
import tp.dominio.InsumoLiquido;
import tp.dominio.Marca;
import tp.enumerados.Unidad;
import tp.service.InsumoLiquidoService;
import tp.service.InsumoService;
import tp.service.MarcaService;

public class InsumoLiquidoController {

	InsumoLiquidoService service;
	
	public InsumoLiquidoController() {
		service = new InsumoLiquidoService();
	}
	
	public Mensaje add(String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double densidad) {
		if(unidadDeMedida == null) {
			return new Mensaje(false,"Error: Unidad vac�a");
		}
		if(costoPorUnidad == null && costoPorUnidad > 0) {
			return new Mensaje(false,"Error: Costo vac�o o nulo");
		}
		
			return service.add(descripcion, unidadDeMedida, costoPorUnidad, densidad);
			
	}
	
	public Mensaje delete(String id) {
		return service.delete(id);
	}
	
	public Mensaje update(String id_original, String id_nuevo, String descripcion_nuevo, Unidad unidadDeMedida_nuevo, Double costoPorUnidad_nuevo, Double densidad) {
		if(unidadDeMedida_nuevo == null) {
			return new Mensaje(false,"Error: Unidad vac�a");
		}
		if(costoPorUnidad_nuevo == null && costoPorUnidad_nuevo > 0) {
			return new Mensaje(false,"Error: Costo vac�o o nulo");
		}
		if(id_nuevo!=null && id_nuevo.length()>0) 
			return service.update(id_original,id_nuevo, descripcion_nuevo, unidadDeMedida_nuevo, costoPorUnidad_nuevo, densidad);
		else 
			return new Mensaje(false,"Error: ID inv�lido");
	}
	
	public List<InsumoLiquido> getAll(){
		return service.getAll();
	}
	
}
