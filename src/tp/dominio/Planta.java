package tp.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import tp.dao.Registrable;
import tp.enumerados.Estado;

public class Planta{
	private String id_planta;
	private String nombre;
	private Double plant_rank;
	private Double peso;
	private List<Camion> lista_camiones;
	private List<StockInsumo> lista_stock_insumos;
	private List<Pedido> lista_pedidos;
	Comparator<Camion> compara_camion = Comparator.comparing(Camion:: getDistancia_recorrida_en_km);
	PriorityQueue<Camion> priority_queue = new PriorityQueue<Camion>( compara_camion );

	public Planta(String id_planta) {
		super();
		this.nombre = null;
		this.lista_camiones = new ArrayList<Camion>();
		this.lista_stock_insumos = new ArrayList<StockInsumo>();
		this.lista_pedidos = new ArrayList<Pedido>();
		this.id_planta = id_planta;
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
		this.lista_stock_insumos.add(new StockInsumo(this.getId_planta(), insumo.getId_insumo(), stock, puntoDePedido));
	}
	
	public void realizarPedido(String id, LocalDate fechaEntrega, LocalDate fechaMaxima,
			 Double costo, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen) {
		//Un usuario puede seleccionar una planta y registrar una orden de pedido donde se indique
		this.lista_pedidos.add(new Pedido(id,plantaOrigen, this, envio, LocalDate.now(), fechaEntrega, fechaMaxima, Estado.CREADA, costo));
	}
	
	public boolean nombreIgual(String nombre_planta) {
		return this.nombre==nombre_planta;
	}

	public Boolean puedoSatisfacer(Pedido p){								
		Integer contador = 0;
		for(DetallePedido dp : p.getLista_detalle_pedidos()) {
			for(StockInsumo si : this.lista_stock_insumos) {
				if(si.getInsumo().equals(dp.getInsumo()) && si.getStock() > dp.getCantidad_de_unidades())
					contador++;
			}
		}
		return contador.equals(p.getLista_detalle_pedidos().size());
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId_planta() {
		return id_planta;
	}

	public void setId_planta(String id_planta) {
		this.id_planta = id_planta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPlant_rank() {
		return plant_rank;
	}

	public void setPlant_rank(Double plant_rank) {
		this.plant_rank = plant_rank;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public List<Camion> getLista_camiones() {
		return lista_camiones;
	}

	public void setLista_camiones(List<Camion> lista_camiones) {
		this.lista_camiones = lista_camiones;
	}

	public List<StockInsumo> getLista_stock_insumos() {
		return lista_stock_insumos;
	}

	public void setLista_stock_insumos(List<StockInsumo> lista_stock_insumos) {
		this.lista_stock_insumos = lista_stock_insumos;
	}

	public List<Pedido> getLista_pedidos() {
		return lista_pedidos;
	}

	public void setLista_pedidos(List<Pedido> lista_pedidos) {
		this.lista_pedidos = lista_pedidos;
	} 
	
	public Camion getCamionConPrioridad(){
		return this.priority_queue.poll();
	}
	
}
