package entidades_dominio;

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
}
