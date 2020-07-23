package entidades_dominio;

public class Marca{
	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "[Marca]["+this.nombre+"]";
	}

}
