package tp.controller;


import java.util.List;

import tp.dao.PlantaDAO;
import tp.dominio.Camion;
import tp.dominio.Planta;
import tp.service.CamionService;

public class CamionController {

	CamionService service;

	public CamionController() {
		service = new CamionService();
	}

	public Mensaje add(String id_camion, String id_planta, String nombre_modelo, Double distancia,
			Double costo_por_km, Double costo_por_hora, String fecha_de_compra) {

		if(id_camion == null || id_camion.length()==0) 
			return new Mensaje(false,"Error: patente inválida");

		if(id_planta == null || id_planta.length()==0) 
			return new Mensaje(false,"Error: id_planta inválido");

		if(nombre_modelo == null || nombre_modelo.length()==0) 
			return new Mensaje(false,"Error: nombre_modelo inválido");

		if(distancia== null)
			return new Mensaje(false,"Error: distancia_recorrida_en_km inválida");

		if(costo_por_km == null)
			return new Mensaje(false,"Error: costo_por_km inválido");

		if(costo_por_hora == null) 
			return new Mensaje(false,"Error: costo_por_hora inválido");

		if(fecha_de_compra== null || fecha_de_compra.length()==0 || !Utilidades.esFecha(fecha_de_compra)) 
			return new Mensaje(false,"Error: fecha_de_compra inválida. El formato debe ser DD/MM/AAAA");


		return service.add(id_camion,
				id_planta,
				nombre_modelo,
				distancia,
				costo_por_km,
				costo_por_hora,
				Utilidades.parsearFecha(fecha_de_compra));

	}

	public Mensaje update(Camion original, Camion nuevo) {
		return service.update(original, nuevo);
	}
	
	public Mensaje update(int columna,String valorNuevo,Camion original) {
		Camion nuevo = new Camion(original);
				
		switch(columna) {
		case 1:
			Planta plantaTemp = new PlantaDAO().get(valorNuevo).get();
			nuevo.setPlanta(plantaTemp);
			break;
		case 3:
			if(Utilidades.esDouble(valorNuevo))
				nuevo.setDistancia_recorrida_en_km(Double.parseDouble(valorNuevo));
			else
				return new Mensaje(false,"La distancia ingresada es inválida");
			break;
		case 4:
			if(Utilidades.esDouble(valorNuevo))
				nuevo.setCosto_por_km(Double.parseDouble(valorNuevo));
			else
				return new Mensaje(false,"El costo por km ingresado es inválido");
			break;
		case 5:
			if(Utilidades.esDouble(valorNuevo))
				nuevo.setCosto_por_hora(Double.parseDouble(valorNuevo));
			else
				return new Mensaje(false,"El costo por hora ingresado es inválido");
			break;
		default:
			return new Mensaje(false, "Este campo no es modificable");
		}
		
		
		return service.update(original, nuevo);
	}

	public Mensaje delete(String nombre) {
		return service.delete(nombre);
	}
	
	public List<Camion> query(String[] parametros) {
		return service.query(parametros);
	}
	
	//	
	//	public Mensaje update(String original, String nueva) {
	//		if(nueva!=null && nueva.length()>0) 
	//			return service.update(original,nueva);
	//		else 
	//			return new Mensaje(false,"Error: nombre inválido");
	//	}
	//	
	public List<Camion> getAll(){
		return service.getAll();
	}

	public Camion getDisponible(String nombre_planta){
		return service.getDisponible(nombre_planta);
	}

}
