package tp.dominio;

import tp.enumerados.Unidad;

public class Insumo {
	protected String id_insumo;
	protected String descripcion;
	protected Unidad unidad_de_medida;
	protected Double costo_por_unidad;
	
	public Insumo() {}
	
	public Insumo(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad) {
		this.id_insumo = id;
		this.descripcion = descripcion;
		this.unidad_de_medida = unidadDeMedida;
		this.costo_por_unidad = costoPorUnidad;
	}
	
	public Double pesoPorUnidad(){
		return 0.0d;
	};
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id_insumo;
	}
	public void setId(String id) {
		this.id_insumo = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Unidad getUnidad_de_medida() {
		return unidad_de_medida;
	}
	public void setUnidad_de_medida(Unidad unidadDeMedida) {
		this.unidad_de_medida = unidadDeMedida;
	}
	public Double getCosto_por_unidad() {
		return costo_por_unidad;
	}
	public void setCosto_por_unidad(Double costoPorUnidad) {
		this.costo_por_unidad = costoPorUnidad;
	}
}
