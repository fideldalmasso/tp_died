package entidades_dominio;

public class Marca implements Registrable{
	private String nombre;

	public Marca(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getSentenciaInsert() {
		return "INSERT INTO tp.Marca VALUES ('"+this.nombre+"');";
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
