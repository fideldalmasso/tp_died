package tp.controller;

import java.util.List;

import tp.dominio.Ruta;
import tp.service.RutaService;

public class RutaController {
	RutaService service;
	
	public RutaController() {
		service = new RutaService();
	}
	
	public Mensaje add(Ruta r1) {
		String error = "Error: ";
		if(r1.getDistancia_en_km()>0D && r1.getPeso_maximo_por_dia_en_kg()>0D && r1.getDuracion_en_minutos()>0D) 
			return service.add(r1);
		else if(r1.getDistancia_en_km()<=0D){
			error+="la distancia debe ser mayor a 0, ";
		}else if(r1.getPeso_maximo_por_dia_en_kg()<=0D) {
			error+="el peso maximo debe ser mayor a 0, ";
		}else if(r1.getDuracion_en_minutos()<=0D) {
			error+="la duracion debe ser mayor a 0. ";
		}
		return new Mensaje(false,error);
			
	}
	
	public Mensaje delete(String id_Ruta) {
		return service.delete(id_Ruta);
	}
	
	public Mensaje update(Ruta original, Ruta nueva) {
		String error = "Error: ";
		if(nueva.getDistancia_en_km()>0D && nueva.getPeso_maximo_por_dia_en_kg()>0D && nueva.getDuracion_en_minutos()>0D) 
			return service.update(original,nueva);
		else if(nueva.getDistancia_en_km()<=0D){
			error+="la distancia debe ser mayor a 0, ";
		}else if(nueva.getPeso_maximo_por_dia_en_kg()<=0D) {
			error+="el peso maximo debe ser mayor a 0, ";
		}else if(nueva.getDuracion_en_minutos()<=0D) {
			error+="la duracion debe ser mayor a 0. ";
		}
		return new Mensaje(false,error);
	}
	
	public List<Ruta> getAll(){
		return service.getAll();
	}
}
