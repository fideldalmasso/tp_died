package tp.dominio;

public class Marca{
	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}
	
	//GETTERS Y SETTERS-----------------------------------------------
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
