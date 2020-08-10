package tp.dominio;

import java.io.Serializable;
import java.util.List;
import tp.dao.StockInsumoDAO;
import tp.enumerados.Unidad;

public class Insumo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id_insumo;
	protected String descripcion;
	protected Unidad unidad_de_medida;
	protected Double costo_por_unidad;
	
	public Insumo() {}
	
	public Insumo( String descripcion, Unidad unidadDeMedida, Double costoPorUnidad) {
	
		this.descripcion = descripcion;
		this.unidad_de_medida = unidadDeMedida;
		this.costo_por_unidad = costoPorUnidad;
	}
	
	public Insumo(String id, String descripcion, Unidad unidadDeMedida, Double costoPorUnidad) {
		this.id_insumo = id;
		this.descripcion = descripcion;
		this.unidad_de_medida = unidadDeMedida;
		this.costo_por_unidad = costoPorUnidad;
	}
	
	public Double pesoPorUnidad(){
		return 0.0d;
	};
	
	public Integer getStockTotal() {
			
			StockInsumoDAO StockInsumoDao = new StockInsumoDAO();
			List<StockInsumo> lista = StockInsumoDao.getAll();
			Integer stock = 0;
			for(StockInsumo StockInsumo: lista) {
						if(StockInsumo.getInsumo().equals(this.getId_insumo())) {
					stock += StockInsumo.getStock();
				}
			}
		
		return stock;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId_insumo() {
		return id_insumo;
	}
	public void setId_insumo(String id) {
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
