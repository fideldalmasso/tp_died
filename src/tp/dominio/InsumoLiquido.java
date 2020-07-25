package tp.dominio;

import tp.enumerados.Unidad;

public class InsumoLiquido extends Insumo{
	private Double densidad;
	
	public InsumoLiquido(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad, Double densidad) {
		this.id_insumo = id;
		this.descripcion = descripcion;
		this.unidad_de_medida = unidadDeMedida;
		this.costo_por_unidad = costoPorUnidad;
		this.densidad = densidad;
	}
	
	@Override
	public Double pesoPorUnidad() {
		return densidad;
	}
	//GETTERS Y SETTERS-----------------------------------------------

	public Double getDensidad() {
		return densidad;
	}
	public void setDensidad(Double densidad) {
		this.densidad = densidad;
	}
	
	
	
	
}
