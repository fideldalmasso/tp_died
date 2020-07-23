package tp.dominio;

import tp.enumerados.Unidad;

public class InsumoGeneral extends Insumo{
	private Double pesoPorUnidad;


	public InsumoGeneral(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double pesoPorUnidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.costoPorUnidad = costoPorUnidad;
		this.pesoPorUnidad = pesoPorUnidad;
	}
	
	@Override
	public Double pesoPorUnidad() {
		return this.pesoPorUnidad;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public Double getPesoPorUnidad() {
		return pesoPorUnidad;
	}
	public void setPesoPorUnidad(Double pesoPorUnidad) {
		this.pesoPorUnidad = pesoPorUnidad;
	}
	
}
