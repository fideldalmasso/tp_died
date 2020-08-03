package tp.controller;

import java.util.List;

import tp.dominio.DetallePedido;
import tp.service.DetallePedidoService;

public class DetallePedidoController {
	DetallePedidoService service;
	
	public DetallePedidoController() {
		service = new DetallePedidoService();
	}
	
	public Mensaje add(DetallePedido dp) {
		if(dp.getCantidad_de_unidades()>0) {
			return service.add(dp);
		}else {
			return new Mensaje(false,"Error: la cantidad de unidades debe ser mayor a 0.");
		}
	}
	
	public Mensaje delete(String ...id_pedido) {
		return service.delete(id_pedido);
	}
	
	public Mensaje update(DetallePedido nuevo) {
		return service.add(nuevo);
	}
	
	public List<DetallePedido> getAll(){
		return service.getAll();
	}
}
