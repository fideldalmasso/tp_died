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
	
}
