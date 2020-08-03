package tp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import tp.dominio.DetallePedido;
import tp.dominio.Envio;
import tp.dominio.Pedido;
import tp.dominio.Planta;
import tp.enumerados.Estado;
import tp.service.PedidoService;

public class PedidoController {

	PedidoService service;
	
	public PedidoController() {
		service = new PedidoService();
	}
	
	public Mensaje add(String nombre_destino, String fecha_maxima, List<DetallePedido> detalles) {
		String error = "Error: ";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(nombre_destino.length()>0 &&
			fecha_maxima.length()>0) {
			PlantaController pc = new PlantaController();
			Planta destino = pc.getAll().parallelStream().filter(p->p.getNombre().equals(nombre_destino)).findFirst().orElse(null);
			return service.add(new Pedido(null,null,destino,null,LocalDate.now(), null, LocalDate.parse(fecha_maxima,formato),Estado.CREADA,null));
		}else {
			if(nombre_destino.length()==0) {
				error+="debe ingresar la planta de destino, ";
			} else if(fecha_maxima.length()==0) {
				error+="debe ingresar la fecha maxima, ";
			} else if(detalles.size()==0) {
				error+="debe seleccionar al menos un item.";
			}
			return new Mensaje(false,error);
		}
	}
	
	public Mensaje delete(String id_pedido) {
		return service.delete(id_pedido);
	}
	
	public Mensaje update(Pedido original, Pedido nuevo) {
		String error = "Error: ";
		if(nuevo.getLista_detalle_pedidos().size()>0 &&
			nuevo.getFecha_maxima()!=null &&
			nuevo.getPlanta_destino()!=null) {
			return service.add(nuevo);
		}else {
			if(nuevo.getLista_detalle_pedidos().size()==0) {
				error+="debe seleccionar al menos un item, ";
			} else if(nuevo.getFecha_maxima()==null) {
				error+="debe ingresar la fecha maxima ";
			} else if(nuevo.getPlanta_destino()==null) {
				error+="debe ingresar la planta de destino";
			}
			return new Mensaje(false,error);
		}
	}
	
	public List<Pedido> getAll(){
		return service.getAll();
	}
}
