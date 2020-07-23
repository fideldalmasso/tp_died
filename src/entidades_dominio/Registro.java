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

	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getVariacion() {
		return variacion;
	}

	public void setVariacion(Integer variacion) {
		this.variacion = variacion;
	}

	
	
	
	
}
