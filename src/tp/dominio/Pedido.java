package tp.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.enumerados.Estado;

public class Pedido {
	private String id;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	private LocalDate fechaMaxima;
	private Estado estado;
	private Double costo_pedido;
	private List<DetallePedido> lista_detalle_pedidos;
	private Envio envio;
	private Planta plantaOrigen;
	private Planta plantaDestino;
	
	public Pedido(String id,LocalDate fechaSolicitud, LocalDate fechaEntrega, LocalDate fechaMaxima,
			 Double costo_pedido, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen,
			Planta plantaDestino) {
		super();
		this.id = id;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaEntrega = fechaEntrega;
		this.fechaMaxima = fechaMaxima;
		this.estado = Estado.CREADA;
		this.costo_pedido = costo_pedido;
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
		todos los productos se mostrar� un mensaje de error y el pedido
		pasa a estado CANCELADO*/
		if(plantasConStock.isEmpty()){
			this.estado = Estado.CANCELADA;
		}
		return plantasConStock;
	}
	
	public void crearEnvio(){
		//falta crear metodo CalcularCostoEnvio
		Boolean eligeLaMasCorta = null;
		Boolean eligeLaMasRapida = null;
		List<Ruta> rutaSeleccionada = null ;
		Double costoEnvio = 0d;
		Camion camionConPrioridad = this.plantaOrigen.getCamionConPrioridad();
		if(eligeLaMasCorta) {
		//  costoEnvio = empresa.obtenerCamino(this.plantaOrigen, this.plantaDestino).CalcularCostoEnvio;
		}else if (eligeLaMasRapida) {
		//  costoEnvio = empresa.obtenerCamino(this.plantaOrigen, this.plantaDestino).CalcularCostoEnvio;
		}
		this.envio = new Envio("un id", camionConPrioridad, costoEnvio , rutaSeleccionada) ;
		//camionConPrioridad.agregarKm(kilometers); agrega los kilometros del camino
		this.estado = Estado.PROCESADA;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public LocalDate getFechaMaxima() {
		return fechaMaxima;
	}
	public void setFechaMaxima(LocalDate fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Double getCosto() {
		return costo_pedido;
	}
	public void setCosto(Double costo_pedido) {
		this.costo_pedido = costo_pedido;
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
