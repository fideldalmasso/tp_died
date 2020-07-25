package tp.dominio;

public class DetallePedido {
	private Insumo insumo;
	private Pedido pedido;
	private Integer cantidad_de_unidades;
	private Double precio;
	
	public DetallePedido(Insumo insumo, Pedido pedido, Integer cantidad_de_unidades) {
		/*El sistema automáticamente calculará el precio del item del
		pedido (precio del insumo * cantidad) y lo mostrará en
		pantalla*/
		this.insumo = insumo;
		this.pedido = pedido;
		this.cantidad_de_unidades = cantidad_de_unidades;
		this.precio = insumo.costo_por_unidad * cantidad_de_unidades;
	}

	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Integer getCantidad_de_unidades() {
		return cantidad_de_unidades;
	}
	public void setCantidad_de_unidades(Integer cantidad) {
		this.cantidad_de_unidades = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
