package entidades_dominio;

import java.time.LocalDateTime;
import java.util.List;

public class StockInsumo {
	private Planta planta;
	private Insumo insumo;
	private Integer stock;
	private Integer puntoDePedido;
	private List<Registro> historial;
	
	public StockInsumo(Planta planta, Insumo insumo, Integer stock, Integer puntoDePedido) {
		super();
		this.planta = planta;
		this.insumo = insumo;
		this.stock = stock;
		this.puntoDePedido = puntoDePedido;	
	}
	
	public void modificarStock(Integer stockNuevo, Integer puntoDePedido) {
		//Crea un registro donde guarda los datos q se modifican y lo agrega a la lista de registros
		this.historial.add(new Registro("Generar ID", LocalDateTime.now(), this.stock , stockNuevo - this.stock ));
		//modifica el Stock
		this.stock = stockNuevo;
		this.puntoDePedido = puntoDePedido;
	}
}
