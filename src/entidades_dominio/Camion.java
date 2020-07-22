package entidades_dominio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class Camion implements Registrable{
	private String id_camion;
	private Planta planta;
	private Modelo modelo;
	private Integer distancia_recorrida_en_km;
	private Double costo_por_km;
	private Double costo_por_hora;
	private LocalDateTime fecha_de_compra;
	
	public Camion(String id_camion, Planta planta, Modelo modelo, Double costo_por_km, Double costo_por_hora, LocalDateTime fecha_de_compra) {
		this.planta = planta;
		this.id_camion = id_camion;
		this.modelo = modelo;
		this.distancia_recorrida_en_km= 0;
		this.costo_por_km = costo_por_km;
		this.costo_por_hora = costo_por_hora;
		this.fecha_de_compra = fecha_de_compra;
	}
	
	static public void agregarCamion() {
		
	}
	
	
	@Override
	public String getSentenciaInsert() {
		return Registrable.getSentenciaInsert2("Camion",Arrays.asList(
				id_camion,
				planta.getId_planta(),
				modelo.getNombre(),
				distancia_recorrida_en_km,
				costo_por_km,
				costo_por_hora,
				fecha_de_compra));
		
	}

	@Override
	public String getSentenciaDelete() {
		return "DELETE FROM Camion WHERE id_camion = '"+this.id_camion+"';";
	}

	@Override
	public String getSentenciaUpdate(Object camion2) {
		if(camion2.getClass().getSimpleName()!= "Camion") {
			System.out.println("Error en la actualizacion, el objeto indicado no es un camion!");
			return "xd";
		}
		else
			return "xd";

	}
	

	
}
