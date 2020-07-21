package entidades_dominio;

import enumerados.Unidad;

public class InsumoLiquido extends Insumo{
	private Double densidad;
	
	public InsumoLiquido(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double densidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.costoPorUnidad = costoPorUnidad;
		this.densidad = densidad;
	}
	
	public Double pesoPorUnidad() {
		return densidad;
	}
}
