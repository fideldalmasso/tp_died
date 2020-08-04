package tp.controller;


import java.util.List;
import tp.dominio.Camion;
import tp.service.CamionService;

public class CamionController {

	CamionService service;

	public CamionController() {
		service = new CamionService();
	}

	public Mensaje add(String id_camion, String id_planta, String nombre_modelo, String distancia,
			String costo_por_km, String costo_por_hora, String fecha_de_compra) {

		if(id_camion == null || id_camion.length()==0) 
			return new Mensaje(false,"Error: id_camion inv�lido");

		if(id_planta == null || id_planta.length()==0) 
			return new Mensaje(false,"Error: id_planta inv�lido");

		if(nombre_modelo == null || nombre_modelo.length()==0) 
			return new Mensaje(false,"Error: nombre_modelo inv�lido");

		if(distancia== null || distancia.length()==0)// || !Utilidades.esDouble(distancia)) 
			return new Mensaje(false,"Error: distancia_recorrida_en_km inv�lida");

		if(costo_por_km == null || costo_por_km.length()==0)// !Utilidades.esDouble(costo_por_km)) 
			return new Mensaje(false,"Error: costo_por_km inv�lido");

		if(costo_por_hora == null || costo_por_hora.length()==0)// !Utilidades.esDouble(costo_por_hora)) 
			return new Mensaje(false,"Error: costo_por_hora inv�lido");

		if(fecha_de_compra== null || fecha_de_compra.length()==0 || !Utilidades.esFecha(fecha_de_compra)) 
			return new Mensaje(false,"Error: fecha_de_compra inv�lida. El formato debe ser DD/MM/AAAA");


		return service.add(id_camion,
				id_planta,
				nombre_modelo,
				Double.parseDouble(distancia),
				Double.parseDouble(costo_por_km),
				Double.parseDouble(costo_por_hora),
				Utilidades.parsearFecha(fecha_de_compra));

	}

	public Mensaje update(Camion original, Camion nuevo) {
		return service.update(original, nuevo);
	}
	
	//	public Mensaje delete(String nombre) {
	//		return service.delete(nombre);
	//	}
	//	
	//	public Mensaje update(String original, String nueva) {
	//		if(nueva!=null && nueva.length()>0) 
	//			return service.update(original,nueva);
	//		else 
	//			return new Mensaje(false,"Error: nombre inv�lido");
	//	}
	//	
	public List<Camion> getAll(){
		return service.getAll();
	}
	
	public Camion getDisponible(String nombre_planta){
		return service.getDisponible(nombre_planta);
	}

}
