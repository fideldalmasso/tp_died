package tp.controller;

import java.util.List;

import tp.dominio.Planta;
import tp.dominio.Ruta;
import tp.service.RutaService;

public class RutaController {
	RutaService service;
	
	public RutaController() {
		service = new RutaService();
	}
	
	public Mensaje add(String id_ruta, String nombre_origen, String nombre_destino, String distancia, String duracion, String peso_maximo) {
		String error = "Error: jeje";
		if(id_ruta.length()!=0 && nombre_origen.length()!=0 && nombre_destino.length()!=0 && distancia.length()!=0 
		&& Double.parseDouble(distancia)>0D && duracion.length()!=0 && Double.parseDouble(duracion)>0D && peso_maximo.length()!=0 && Double.parseDouble(peso_maximo)>0D) {
			PlantaController pc = new PlantaController();
			Planta origen = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_origen)).findFirst().orElse(null);
			Planta destino = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_destino)).findFirst().orElse(null);
			return(service.add(new Ruta(id_ruta,origen,destino,Double.parseDouble(distancia),Double.parseDouble(duracion),Double.parseDouble(peso_maximo))));
		}
		if(id_ruta.length()==0) {
			error+="debe ingresar el nombre de la ruta, ";
		}
		if(nombre_origen.length()==0){
			error+="debe ingresar el nombre de la planta de origen, ";
		}
		if(nombre_destino.length()==0){
			error+="debe ingresar el nombre de la planta de destino, ";
		}
		if(distancia.length()==0) {
			error+="debe ingresar la distancia, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="la distancia debe ser mayor a 0, ";
		}
		if(duracion.length()==0) {
			error+="debe ingresar la duracion, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="la duracion debe ser mayor a 0, ";
		}
		if(peso_maximo.length()==0) {
			error+="debe ingresar el peso maximo, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="el peso maximo por dia debe ser mayor a 0, ";
		}
		
		return new Mensaje(false,error);	
	}
	
	public Mensaje delete(String id_Ruta) {
		return service.delete(id_Ruta);
	}
	
	public Mensaje update(String id_ruta, String nombre_origen, String nombre_destino, String distancia, String duracion, String peso_maximo) {
		String error = "Error: jeje";
		if(id_ruta.length()!=0 && nombre_origen.length()!=0 && nombre_destino.length()!=0 && distancia.length()!=0 
		&& Double.parseDouble(distancia)>0D && duracion.length()!=0 && Double.parseDouble(duracion)>0D && peso_maximo.length()!=0 && Double.parseDouble(peso_maximo)>0D) {
			PlantaController pc = new PlantaController();
			Planta origen = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_origen)).findFirst().orElse(null);
			Planta destino = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_destino)).findFirst().orElse(null);
			return(service.update(new Ruta(id_ruta,origen,destino,Double.parseDouble(distancia),Double.parseDouble(duracion),Double.parseDouble(peso_maximo))));
		}
		if(id_ruta.length()==0) {
			error+="debe ingresar el nombre de la ruta, ";
		}
		if(nombre_origen.length()==0){
			error+="debe ingresar el nombre de la planta de origen, ";
		}
		if(nombre_destino.length()==0){
			error+="debe ingresar el nombre de la planta de destino, ";
		}
		if(distancia.length()==0) {
			error+="debe ingresar la distancia, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="la distancia debe ser mayor a 0, ";
		}
		if(duracion.length()==0) {
			error+="debe ingresar la duracion, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="la duracion debe ser mayor a 0, ";
		}
		if(peso_maximo.length()==0) {
			error+="debe ingresar el peso maximo, ";
		}else if(Double.parseDouble(distancia)<=0D) {
			error+="el peso maximo por dia debe ser mayor a 0, ";
		}
		return new Mensaje(false,error);	
	}
	
	public List<Ruta> getAll(){
		return service.getAll();
	}
}
