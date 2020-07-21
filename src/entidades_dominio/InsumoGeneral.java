package entidades_dominio;

import enumerados.Unidad;

public class InsumoGeneral extends Insumo{
	private Double pesoPorUnidad;


	public InsumoGeneral(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double pesoPorUnidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.costoPorUnidad = costoPorUnidad;
		this.pesoPorUnidad = pesoPorUnidad;
	}
	
	public Double pesoPorUnidad() {
		return this.pesoPorUnidad;
	}
}
