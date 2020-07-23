package tp.dominio;

import java.time.*;
import java.util.Arrays;
import java.util.List;


public class Camion{
	private String id_camion;
	private Planta planta;
	private Modelo modelo;
	private Double distancia_recorrida_en_km;
	private Double costo_por_km;
	private Double costo_por_hora;
	private LocalDate fecha_de_compra;
	
	public Camion(String id_camion, Planta planta, Modelo modelo, Double distancia_recorrida_en_km,
			Double costo_por_km, Double costo_por_hora, LocalDate fecha_de_compra) {
		this.id_camion = id_camion;
		this.planta = planta;
		this.modelo = modelo;
		this.distancia_recorrida_en_km = distancia_recorrida_en_km;
		this.costo_por_km = costo_por_km;
		this.costo_por_hora = costo_por_hora;
		this.fecha_de_compra = fecha_de_compra;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public String getId_camion() {
		return id_camion;
	}
	public void setId_camion(String id_camion) {
		this.id_camion = id_camion;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Double getDistancia_recorrida_en_km() {
		return distancia_recorrida_en_km;
	}
	public void setDistancia_recorrida_en_km(Double distancia_recorrida_en_km) {
		this.distancia_recorrida_en_km = distancia_recorrida_en_km;
	}
	public Double getCosto_por_km() {
		return costo_por_km;
	}
	public void setCosto_por_km(Double costo_por_km) {
		this.costo_por_km = costo_por_km;
	}
	public Double getCosto_por_hora() {
		return costo_por_hora;
	}
	public void setCosto_por_hora(Double costo_por_hora) {
		this.costo_por_hora = costo_por_hora;
	}
	public LocalDate getFecha_de_compra() {
		return fecha_de_compra;
	}
	public void setFecha_de_compra(LocalDate fecha_de_compra) {
		this.fecha_de_compra = fecha_de_compra;
	}
	
}
