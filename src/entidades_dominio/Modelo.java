package entidades_dominio;

public class Modelo{
	private String nombre;
	private Marca marca;
	
	public Modelo(String nombre, Marca marca) {
		this.nombre = nombre;
		this.marca = marca;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	

}
