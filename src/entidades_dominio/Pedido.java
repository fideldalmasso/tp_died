package entidades_dominio;

import java.time.LocalDateTime;
import java.util.List;

import enumerados.Estado;

public class Pedido {
	private String id;
	private LocalDateTime fechaSolicitud;
	private LocalDateTime fechaEntrega;
	private LocalDateTime fechaMaxima;
	private Estado estado;
	private Double costo;
	private List<DetallePedido> insumos;
	private Envio envio;
	private Planta plantaOrigen;
	private Planta plantaDestino;
	
	public Pedido(String id, LocalDateTime fechaEntrega, LocalDateTime fechaMaxima,
			 Double costo, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen,
			Planta plantaDestino) {
		super();
		this.id = id;
		this.fechaSolicitud = LocalDateTime.now();
		this.fechaEntrega = fechaEntrega;
		this.fechaMaxima = fechaMaxima;
		this.estado = Estado.CREADA;
		this.costo = costo;
		this.insumos = insumos;
		this.envio = envio;
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
	}
	
	
}
