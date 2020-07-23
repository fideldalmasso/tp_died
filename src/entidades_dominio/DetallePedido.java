package entidades_dominio;

public class DetallePedido {
	private Insumo insumo;
	private Integer cantidad;
	private Double precio;
	
	public DetallePedido(Insumo insumo, Integer cantidad, Double precio) {
		/*El sistema automáticamente calculará el precio del item del
		pedido (precio del insumo * cantidad) y lo mostrará en
		pantalla*/
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.precio = insumo.costoPorUnidad * cantidad;
	}

	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}
