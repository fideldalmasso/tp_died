package tp.dominio;

import java.time.LocalDate;

public class Registro {
	private String id_planta;
	private String id_insumo;
	private LocalDate fecha_registro;
	private Integer stock;
	private Integer punto_de_pedido;
	private Integer variacion;
	
	public Registro(String id_planta, String id_insumo, LocalDate fechaRegistro, Integer stock, Integer variacion, Integer punto_de_pedido) {
		this.id_planta = id_planta;
		this.id_insumo = id_insumo;
		this.fecha_registro = fechaRegistro;
		this.stock = stock;
		this.variacion = variacion;
		this.punto_de_pedido = punto_de_pedido;
	}

	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getPlanta() {
		return id_planta;
	}

	public void setId(String id_planta) {
		this.id_planta = id_planta;
	}
	
	public String getInsumo() {
		return id_insumo;
	}
	
	public void getInsumo(String id_insumo) {
		this.id_insumo = id_insumo;
	}

	public LocalDate getFechaRegistro() {
		return fecha_registro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
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

	public Integer getPuntoDePedido() {
		return this.punto_de_pedido;
	}
	
	public void setPuntoDePedido(Integer punto_de_pedido) {
		this.punto_de_pedido=punto_de_pedido;
	}
	
	
	
}
