package tp.dominio;

import tp.enumerados.Unidad;

public class InsumoGeneral extends Insumo{
	
	
	private Double peso_por_unidad;


	public InsumoGeneral(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double pesoPorUnidad) {
		this.id_insumo = id;
		this.descripcion = descripcion;
		this.unidad_de_medida = unidadDeMedida;
		this.costo_por_unidad = costoPorUnidad;
		this.peso_por_unidad = pesoPorUnidad;
	}
	
	@Override
	public Double pesoPorUnidad() {
		return this.peso_por_unidad;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public Double getPesoPorUnidad() {
		return peso_por_unidad;
	}
	public void setPesoPorUnidad(Double pesoPorUnidad) {
		this.peso_por_unidad = pesoPorUnidad;
	}
	
}
