package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.DetallePedidoDAO;
import tp.dominio.DetallePedido;

public class DetallePedidoService {
	DetallePedidoDAO dao;
	
	public DetallePedidoService(){
		dao = new DetallePedidoDAO();
	}
	
	public Mensaje add(DetallePedido detalle_pedido) {
		if(dao.add(detalle_pedido))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede insertar la fila (Error en la DB).");
	}
	
	public Mensaje delete(String ...id_detalle_pedido) {
		if(dao.delete(id_detalle_pedido))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(DetallePedido original, DetallePedido nuevo) {
		if(dao.update(original,nuevo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede actualizar la fila (Error en la DB)");
				
	}
	
	
	public List<DetallePedido> getAll(){
		return dao.getAll();
	}
	
	public List<DetallePedido> getAll(String id_pedido){
		return dao.getAll();
	}
}
