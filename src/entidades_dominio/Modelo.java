package entidades_dominio;

public class Modelo implements Registrable{
	private String nombre;
	private Marca marca;
	
	public Modelo(String nombre,Marca marca) {
		this.nombre = nombre;
		this.marca = marca;
	}
	
	public String getNombre(){
		return this.nombre;
	}

	@Override
	public String getSentenciaInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSentenciaDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSentenciaUpdate(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
