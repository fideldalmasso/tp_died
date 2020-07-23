package tp.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tp.dao.Registrable;

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
			 Double costo, List<DetallePedido> insumos, Envio envio, Planta plantaOrigen) {
		//Un usuario puede seleccionar una planta y registrar una orden de pedido donde se indique
		this.lista_pedidos.add(new Pedido(id, fechaEntrega, fechaMaxima, costo, insumos, envio, plantaOrigen, this));
	}
	
	public boolean nombreIgual(String nombre_planta) {
		return this.nombre==nombre_planta;
	}

	public Boolean puedoSatisfacer(Pedido p){								
		Integer contador = 0;
		for(DetallePedido dp : p.getLista_detalle_pedidos()) {
			for(StockInsumo si : this.lista_stock_insumos) {
				if(si.getInsumo().equals(dp.getInsumo()) && si.getStock() > dp.getCantidad())
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
	
	
	
}
