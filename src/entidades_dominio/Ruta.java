package entidades_dominio;

import java.time.Duration;

public class Ruta {
	private String id_ruta;
	private Double distancia_en_km;
	private Double duracion_en_minutos;
	private Double peso_maximo_dia;
	private Double peso_utilizado;
	private Planta planta_origen;
	private Planta planta_destino;
	
	public Ruta(String id,Double distancia,Double duracion,Double peso_maximo_dia,Double peso_utilizado,Planta planta_origen,Planta planta_destino) {
		super();
		this.id_ruta=id;
		this.distancia_en_km=distancia;
		this.duracion_en_minutos=duracion;
		this.peso_maximo_dia=peso_maximo_dia;
		this.peso_utilizado=peso_utilizado;
		this.planta_origen=planta_origen;
		this.planta_destino=planta_destino;
	}
	
	public Double getPeso() {
		return this.peso_maximo_dia-this.peso_utilizado;
	}
	public void setUtilizado() {
		this.peso_utilizado=0D;
	}
	public void disminuirCapacidad(Double utilizado) {
		this.peso_utilizado+=utilizado;
	}
	
	public Double getTiempo() {
		return this.duracion_en_minutos;
	}
	public Double getDistancia() {
		return this.distancia_en_km;
	}
	public Planta getOrigen() {
		return this.planta_origen;
	}
	public Planta getDestino() {
		return this.planta_destino;
	}

	
	//GETTERS Y SETTERS-----------------------------------------------
	public String getId_ruta() {
		return id_ruta;
	}

	public void setId_ruta(String id_ruta) {
		this.id_ruta = id_ruta;
	}

	public Double getDistancia_en_km() {
		return distancia_en_km;
	}

	public void setDistancia_en_km(Double distancia_en_km) {
		this.distancia_en_km = distancia_en_km;
	}

	public Double getDuracion_en_minutos() {
		return duracion_en_minutos;
	}

	public void setDuracion_en_minutos(Double duracion_en_minutos) {
		this.duracion_en_minutos = duracion_en_minutos;
	}

	public Double getPeso_maximo_dia() {
		return peso_maximo_dia;
	}

	public void setPeso_maximo_dia(Double peso_maximo_dia) {
		this.peso_maximo_dia = peso_maximo_dia;
	}

	public Double getPeso_utilizado() {
		return peso_utilizado;
	}

	public void setPeso_utilizado(Double peso_utilizado) {
		this.peso_utilizado = peso_utilizado;
	}

	public Planta getPlanta_origen() {
		return planta_origen;
	}

	public void setPlanta_origen(Planta planta_origen) {
		this.planta_origen = planta_origen;
	}

	public Planta getPlanta_destino() {
		return planta_destino;
	}

	public void setPlanta_destino(Planta planta_destino) {
		this.planta_destino = planta_destino;
	}
	
	
}
