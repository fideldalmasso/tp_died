package tp.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.enumerados.Estado;

public class Pedido {
	private String id_pedido;
	private Planta planta_origen;
	private Planta planta_destino;
	private Envio envio;
	private LocalDate fecha_solicitud;
	private LocalDate fecha_entrega;
	private LocalDate fecha_maxima;
	private Estado estado_pedido;
	private Double costo_pedido;
	
	private List<DetallePedido> lista_detalle_pedidos;
	
	public Pedido() {}
	
	public Pedido(String id_pedido,Planta planta_origen, Planta planta_destino, Envio envio,
			 LocalDate fecha_solicitud, LocalDate fecha_entrega, LocalDate fecha_maxima, Estado estado_pedido,
			Double costo_pedido) {
		super();
		this.id_pedido = id_pedido;
		this.fecha_solicitud = fecha_solicitud;
		this.fecha_entrega = fecha_entrega;
		this.fecha_maxima = fecha_maxima;
		this.estado_pedido = estado_pedido;
		this.costo_pedido = costo_pedido;
		this.envio = envio;
		this.planta_origen = planta_origen;
		this.planta_destino = planta_destino;
		this.lista_detalle_pedidos = new ArrayList<DetallePedido>();
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
			this.estado_pedido = Estado.CANCELADA;
		}
		return plantasConStock;
	}
	
	public void crearEnvio(){
		//falta crear metodo CalcularCostoEnvio
		Boolean eligeLaMasCorta = null;
		Boolean eligeLaMasRapida = null;
		List<Ruta> rutaSeleccionada = null ;
		Double costoEnvio = 0d;
		Camion camionConPrioridad = this.planta_origen.getCamionConPrioridad();
		if(eligeLaMasCorta) {
		//  costoEnvio = empresa.obtenerCamino(this.plantaOrigen, this.plantaDestino).CalcularCostoEnvio;
		}else if (eligeLaMasRapida) {
		//  costoEnvio = empresa.obtenerCamino(this.plantaOrigen, this.plantaDestino).CalcularCostoEnvio;
		}
		this.envio = new Envio("un id", camionConPrioridad, costoEnvio , rutaSeleccionada) ;
		//camionConPrioridad.agregarKm(kilometers); agrega los kilometros del camino
		this.estado_pedido = Estado.PROCESADA;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(String id) {
		this.id_pedido = id;
	}
	public LocalDate getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(LocalDate fechaSolicitud) {
		this.fecha_solicitud = fechaSolicitud;
	}
	public LocalDate getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(LocalDate fechaEntrega) {
		this.fecha_entrega = fechaEntrega;
	}
	public LocalDate getFecha_maxima() {
		return fecha_maxima;
	}
	public void setFecha_maxima(LocalDate fechaMaxima) {
		this.fecha_maxima = fechaMaxima;
	}
	public Estado getEstado_pedido() {
		return estado_pedido;
	}
	public void setEstado_pedido(Estado estado) {
		this.estado_pedido = estado;
	}
	public Double getCosto_pedido() {
		return costo_pedido;
	}
	public void setCosto_pedido(Double costo_pedido) {
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
	public Planta getPlanta_origen() {
		return planta_origen;
	}
	public void setPlanta_origen(Planta plantaOrigen) {
		this.planta_origen = plantaOrigen;
	}
	public Planta getPlanta_destino() {
		return planta_destino;
	}
	public void setPlanta_destino(Planta plantaDestino) {
		this.planta_destino = plantaDestino;
	}
	
	
	
	
}
