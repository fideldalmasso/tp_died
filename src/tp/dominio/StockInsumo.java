package tp.dominio;

import java.time.LocalDate;
import java.util.List;

public class StockInsumo {
	private String id_planta;
	private String id_insumo;
	private Integer stock;
	private Integer puntoDePedido;
	private List<Registro> historial;
	
	public StockInsumo(String planta, String insumo, Integer stock, Integer puntoDePedido) {
		super();
		this.id_planta = planta;
		this.id_insumo = insumo;
		this.stock = stock;
		this.puntoDePedido = puntoDePedido;	
	}
	
	public void modificarStock(Integer stockNuevo, Integer puntoDePedido) {
		//Crea un registro donde guarda los datos q se modifican y lo agrega a la lista de registros
		this.historial.add(new Registro("Generar ID", this.id_insumo, LocalDate.now() , this.stock, stockNuevo - this.stock, puntoDePedido ));
		//modifica el Stock
		this.stock = stockNuevo;
		this.puntoDePedido = puntoDePedido;
	}
	
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getPlanta() {
		return id_planta;
	}

	public void setPlanta(String id_planta) {
		this.id_planta = id_planta;
	}

	public String getInsumo() {
		return id_insumo;
	}

	public void setInsumo(String id_insumo) {
		this.id_insumo = id_insumo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPuntoDePedido() {
		return puntoDePedido;
	}

	public void setPuntoDePedido(Integer puntoDePedido) {
		this.puntoDePedido = puntoDePedido;
	}

	public List<Registro> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Registro> historial) {
		this.historial = historial;
	}
}
