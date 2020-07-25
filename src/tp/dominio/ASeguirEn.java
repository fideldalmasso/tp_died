package tp.dominio;

public class ASeguirEn {

	private Envio envio;
	private Ruta ruta;
	private Integer orden;
	
	public ASeguirEn(Envio envio, Ruta ruta, Integer orden) {
		this.envio = envio;
		this.ruta = ruta;
		this.orden = orden;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
	
	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	
	
}
