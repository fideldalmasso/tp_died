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
	private List<DetallePedido> lista_detalle_pedidos;
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
		this.lista_detalle_pedidos = insumos;
		this.envio = envio;
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
	}
	//recibe como parametro una lista con todas las plantas del sistema
	public List<Planta> buscarPlantasConStock(List<Planta> plantas){
		List<Planta> plantasConStock = new ArrayList<>();
		for (Planta planta : plantas){
			if(planta.puedoSatisfacer(this)){
				plantasConStock.add(planta);	
			}
		}
		/*Si no existe una planta que tenga disponible stock de
		todos los productos se mostrará un mensaje de error y el pedido
		pasa a estado CANCELADO*/
		if(plantasConStock.isEmpty()){
			this.estado = Estado.CANCELADA;
		}	
		return plantasConStock;
	}
	
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public LocalDateTime getFechaMaxima() {
		return fechaMaxima;
	}
	public void setFechaMaxima(LocalDateTime fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public List<DetallePedido> getLista_detalle_pedidos() {
		return lista_detalle_pedidos;
	}
	public void setInsumos(List<DetallePedido> insumos) {
		this.lista_detalle_pedidos = insumos;
	}
	public Envio getEnvio() {
		return envio;
	}
	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	public Planta getPlantaOrigen() {
		return plantaOrigen;
	}
	public void setPlantaOrigen(Planta plantaOrigen) {
		this.plantaOrigen = plantaOrigen;
	}
	public Planta getPlantaDestino() {
		return plantaDestino;
	}
	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}
	
	
	
	
}
