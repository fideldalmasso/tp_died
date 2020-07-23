package entidades_dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	//recibe como parametro una lista con todas las plantas del sistema
	public void buscarPlantasConStock(List<Planta> plantas){
		List<Planta> listaDePlantasConStock = new ArrayList<>();// Lista q retorna (falta modificar retorno)
		List<Insumo> listaDeInsumosPedido = new ArrayList<>();// En esta lista se guardan los insumos del pedido
		for (DetallePedido detalle : this.insumos) {
			listaDeInsumosPedido.add(detalle.getInsumo()); //Guarda los insumos del pedido
		}
		for(Planta planta : plantas) {
			List<Insumo> listaDeInsumosPlanta = new ArrayList<>();
			// se guardan los insumos de la planta
			for(StockInsumo stock : planta.getLista_stock_insumos()) {
				listaDeInsumosPlanta.add(stock.getInsumo());
			}
			 if(listaDeInsumosPlanta.containsAll(listaDeInsumosPedido)) {
				 listaDePlantasConStock.add(planta);
			 }
		}
		
	}
	
	
}
