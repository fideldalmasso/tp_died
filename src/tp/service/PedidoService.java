package tp.service;

import java.util.List;

import tp.controller.Mensaje;
import tp.dao.PedidoDAO;
import tp.dominio.Pedido;

public class PedidoService {

	PedidoDAO dao;
	
	public PedidoService(){
		dao = new PedidoDAO();
	}
	
	public Mensaje add(Pedido pedido) {
		if(dao.add(pedido))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede insertar la fila (Error en la DB).");
	}
	
	public Mensaje delete(String id_pedido) {
		if(dao.delete(id_pedido))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede eliminar la fila (error en la DB).");
	}
	
	public Mensaje update(Pedido original, Pedido nuevo) {
		if(dao.update(original,nuevo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede actualizar la fila (Error en la DB)");
				
	}
	
	public Mensaje updateEstado(Pedido original, Pedido nuevo) {
		if(dao.updateEstado(original,nuevo))
			return new Mensaje(true,"");
		else
			return new Mensaje(false,"No se puede actualizar la fila (Error en la DB)");
	}
	
	public List<Pedido> getAll(){
		return dao.getAll();
	}
}
