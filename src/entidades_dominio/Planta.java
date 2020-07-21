package entidades_dominio;

import java.time.LocalDateTime;
import java.util.List;

import enumerados.Estado;

public class Planta {
	private String id;
	private String nombre;
	private List<Camion> camiones;
	private List<StockInsumo> insumos;
	private List<Pedido> pedidos;
	
	public Planta(String id, String nombre, List<Camion> camiones, List<StockInsumo> insumos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.camiones = camiones;
		this.insumos = insumos;
	}
	
	public void agregarInsumo(Insumo insumo, Integer stock, Integer puntoDePedido) {
		// Agregar un insumo e indicar la cantidad de insumo y el punto de pedido que indica para esa planta
		this.insumos.add(new StockInsumo(this, insumo, stock, puntoDePedido));
	}
	
	public void realizarPedido(String id, LocalDateTime fechaEntrega, LocalDateTime fechaMaxima,
			 Double costo, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen,
			Planta plantaDestino) {
		//Un usuario puede seleccionar una planta y registrar una orden de pedido donde se indique
		this.pedidos.add(new Pedido(id, fechaEntrega, fechaMaxima, costo, insumos, envio, plantaOrigen, plantaDestino));
	}
	
}
