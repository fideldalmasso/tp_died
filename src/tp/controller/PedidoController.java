package tp.controller;

import java.util.List;

import tp.dominio.Pedido;
import tp.service.PedidoService;

public class PedidoController {

	PedidoService service;
	
	public PedidoController() {
		service = new PedidoService();
	}
	
	public Mensaje add(Pedido pedido) {
		String error = "Error: ";
		if(pedido.getLista_detalle_pedidos().size()>0 &&
			pedido.getFecha_maxima()!=null &&
			pedido.getPlanta_destino()!=null) {
			return service.add(pedido);
		}else {
			if(pedido.getLista_detalle_pedidos().size()==0) {
				error+="debe seleccionar al menos un item, ";
			} else if(pedido.getFecha_maxima()==null) {
				error+="debe ingresar la fecha maxima ";
			} else if(pedido.getPlanta_destino()==null) {
				error+="debe ingresar la planta de destino";
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
