package entidades_dominio;

public class DetallePedido {
	private Insumo insumo;
	private Integer cantidad;
	private Double precio;
	
	public DetallePedido(Insumo insumo, Integer cantidad, Double precio) {
		/*El sistema autom�ticamente calcular� el precio del item del
		pedido (precio del insumo * cantidad) y lo mostrar� en
		pantalla*/
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.precio = insumo.costoPorUnidad * cantidad;
	}
	
	
}
