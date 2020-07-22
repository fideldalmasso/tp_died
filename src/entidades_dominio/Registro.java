package entidades_dominio;

import java.time.LocalDateTime;

public class Registro {
	private String id;
	private LocalDateTime fechaRegistro;
	private Integer stock;
	private Integer variacion;
	
	public Registro(String id, LocalDateTime fechaRegistro, Integer stock, Integer variacion) {
		this.id = id;
		this.fechaRegistro = fechaRegistro;
		this.stock = stock;
		this.variacion = variacion;
	}
	
}
