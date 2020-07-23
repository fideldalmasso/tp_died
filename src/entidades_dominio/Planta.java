package entidades_dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import base_de_datos.Registrable;

public class Planta{
	private String id_planta;
	private String nombre;
	private Double plant_rank;
	private Double peso;
	private List<Camion> lista_camiones;
	private List<StockInsumo> lista_stock_insumos;
	private List<Pedido> lista_pedidos;

	public Planta(String nombre) {
		super();
		this.nombre = nombre;
		this.lista_camiones = new ArrayList<Camion>();
		this.lista_stock_insumos = new ArrayList<StockInsumo>();
		this.lista_pedidos = new ArrayList<Pedido>();
	}
	
	public Planta(String id, String nombre) {
		this.id_planta = id;
		this.nombre = nombre;
		this.lista_camiones = new ArrayList<Camion>();
		this.lista_stock_insumos = new ArrayList<StockInsumo>();
		this.lista_pedidos = new ArrayList<Pedido>();
	}
	
	public Planta(String id, String nombre, List<Camion> camiones, List<StockInsumo> insumos) {
		super();
		this.id_planta = id;
		this.nombre = nombre;
		this.lista_camiones = camiones;
		this.lista_stock_insumos = insumos;
	}
	
	public void agregarInsumo(Insumo insumo, Integer stock, Integer puntoDePedido) {
		// Agregar un insumo e indicar la cantidad de insumo y el punto de pedido que indica para esa planta
		this.lista_stock_insumos.add(new StockInsumo(this, insumo, stock, puntoDePedido));
	}
	
	public void realizarPedido(String id, LocalDateTime fechaEntrega, LocalDateTime fechaMaxima,
			 Double costo, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen,
			Planta plantaDestino) {
		//Un usuario puede seleccionar una planta y registrar una orden de pedido donde se indique
		this.lista_pedidos.add(new Pedido(id, fechaEntrega, fechaMaxima, costo, insumos, envio, plantaOrigen, plantaDestino));
	}
	
	public String getId_planta() {
		return id_planta;
	}
	
	public void setPeso(Double peso) {
		this.peso=peso;
	}
	
	public Double getPeso() {
		return this.peso;
	}


	public boolean nombreIgual(String nombre_planta) {
		return this.nombre==nombre_planta;
	}

	public void setPlantRank(Double plantRank) {
		this.plant_rank=plantRank;
	}
	
	
}
