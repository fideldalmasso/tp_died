package entidades_dominio;

import java.util.List;

public class Envio {
	private String id_envio;
	private Double costo_envio;
	private Camion camion;
	private List<Ruta> lista_caminos;
	
	public Envio(String id_envio, Double costo_envio, Camion camion, List<Ruta> lista_caminos) {
		this.id_envio = id_envio;
		this.costo_envio = costo_envio;
		this.camion = camion;
		this.lista_caminos = lista_caminos;
	}
	
	public String getId_envio() {
		return id_envio;
	}
	public void setId_envio(String id_envio) {
		this.id_envio = id_envio;
	}
	public Double getCosto_envio() {
		return costo_envio;
	}
	public void setCosto_envio(Double costo_envio) {
		this.costo_envio = costo_envio;
	}
	public Camion getCamion() {
		return camion;
	}
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	public List<Ruta> getLista_caminos() {
		return lista_caminos;
	}
	public void setLista_caminos(List<Ruta> lista_caminos) {
		this.lista_caminos = lista_caminos;
	}
}
