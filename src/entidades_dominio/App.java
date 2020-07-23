package entidades_dominio;


public class App {
	public static void main (String[] args){

	Sistema sistema = new Sistema();
	//sistema.eliminarMarca("Ferrari");
	Marca marca1 = sistema.agregarMarca("Ferrari").get(); //hasta aca funciona
	//sistema.eliminarModelo("Roma");
	Modelo modelo1 = sistema.agregarModelo("Roma",marca1).get();
	}
}
