package tp.dominio;


public class Ruta {
	private String id_ruta;
	private Planta planta_origen;
	private Planta planta_destino;
	private Double distancia_en_km;
	private Double duracion_en_minutos;
	private Double peso_maximo_por_dia_en_kg;
	private Double peso_utilizado;
	
	
	public Ruta(String id_ruta, Planta planta_origen, Planta planta_destino, Double distancia_en_km,
			Double duracion_en_minutos, Double peso_maximo_por_dia_en_kg) {
		this.id_ruta = id_ruta;
		this.planta_origen = planta_origen;
		this.planta_destino = planta_destino;
		this.distancia_en_km = distancia_en_km;
		this.duracion_en_minutos = duracion_en_minutos;
		this.peso_maximo_por_dia_en_kg = peso_maximo_por_dia_en_kg;
		this.peso_utilizado = 0.0;
	}
	
	public Double getPeso() {
		return this.peso_maximo_por_dia_en_kg-this.peso_utilizado;
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

	@Override
	public String toString() {
		return this.id_ruta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_ruta == null) ? 0 : id_ruta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruta other = (Ruta) obj;
		if (id_ruta == null) {
			if (other.id_ruta != null)
				return false;
		} else if (!id_ruta.equals(other.id_ruta))
			return false;
		return true;
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

	public Double getPeso_maximo_por_dia_en_kg() {
		return peso_maximo_por_dia_en_kg;
	}

	public void setPeso_maximo_dia(Double peso_maximo_dia) {
		this.peso_maximo_por_dia_en_kg = peso_maximo_dia;
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
