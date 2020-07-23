package entidades_dominio;

import java.time.Duration;

public class Ruta {
	private String id;
	private Double distancia;
	private Double duracion;
	private Double peso_maximo_dia;
	private Double peso_utilizado;
	private Planta planta_origen;
	private Planta planta_destino;
	
	public Ruta(String id,Double distancia,Double duracion,Double peso_maximo_dia,Double peso_utilizado,Planta planta_origen,Planta planta_destino) {
		super();
		this.id=id;
		this.distancia=distancia;
		this.duracion=duracion;
		this.peso_maximo_dia=peso_maximo_dia;
		this.peso_utilizado=peso_utilizado;
		this.planta_origen=planta_origen;
		this.planta_destino=planta_destino;
	}
	public Double getTiempo() {
		return this.duracion;
	}
	public Double getDistancia() {
		return this.distancia;
	}
	public Planta getOrigen() {
		return this.planta_origen;
	}
	public Planta getDestino() {
		return this.planta_destino;
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
	
}
