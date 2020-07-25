package tp.dominio;

import java.time.LocalDateTime;

public class Registro {
	private String id_planta;
	private Insumo insumo;
	private LocalDateTime fecha_registro;
	private Integer stock;
	private Integer punto_de_pedido;
	private Integer variacion;
	
	public Registro(String id, Insumo insumo, LocalDateTime fechaRegistro, Integer stock, Integer variacion, Integer punto_de_pedido) {
		this.id_planta = id;
		this.fecha_registro = fechaRegistro;
		this.stock = stock;
		this.variacion = variacion;
	}

	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId() {
		return id_planta;
	}

	public void setId(String id) {
		this.id_planta = id;
	}

	public LocalDateTime getFechaRegistro() {
		return fecha_registro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fecha_registro = fechaRegistro;
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
