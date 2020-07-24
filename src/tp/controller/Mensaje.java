package tp.controller;

public class Mensaje {

	private String texto;
	private Boolean exito;
	
	public Mensaje(Boolean exito, String texto) {
		this.exito = exito;
		this.texto = texto;
	}
	
	public Boolean exito() {
		return exito;
	}
	
	public String texto() {
		return texto;
	}
	
}
