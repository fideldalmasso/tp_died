package entidades_dominio;

import enumerados.Unidad;

public class Insumo {
	protected String id;
	protected String descripcion;
	protected Unidad unidadDeMedida;
	protected Double costoPorUnidad;
	
	public Insumo() {}
	
	public Insumo(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.unidadDeMedida = unidadDeMedida;
		this.costoPorUnidad = costoPorUnidad;
	}
	
	public Double pesoPorUnidad(){
		return 0.0d;
	};
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Unidad getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(Unidad unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public Double getCostoPorUnidad() {
		return costoPorUnidad;
	}
	public void setCostoPorUnidad(Double costoPorUnidad) {
		this.costoPorUnidad = costoPorUnidad;
	}
}
