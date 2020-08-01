package tp.controller;

import java.util.List;

import tp.dominio.Insumo;
import tp.dominio.Planta;
import tp.dominio.StockInsumo;
import tp.service.StockInsumoService;

public class StockInsumoController {
	StockInsumoService service;
	
	public StockInsumoController() {
		service = new StockInsumoService();
	}
	
	public Mensaje add(StockInsumo s) {
		String error="Error: ";
		if(s.getPuntoDePedido()>=0 && s.getStock()>=0) {
			return service.add(s);
		}else if(s.getPuntoDePedido()<0) {
			error+="el punto de pedido no debe ser negativo, ";
		}else if(s.getStock()<0) {
			error+="el stock no debe ser negativo";
		}
		return new Mensaje(false,error);
	}
	
	public Mensaje addAll(Planta planta) {
		return service.addAll(planta);
	}
	
	public Mensaje addAll(Insumo insumo) {
		return service.addAll(insumo);
	}
	
	public Mensaje delete(StockInsumo stock_insumo) {
		return service.delete(stock_insumo);
	}
	
	public Mensaje update(StockInsumo original, StockInsumo nuevo) {
		String error="Error: ";
		if(nuevo.getPuntoDePedido()>=0 && nuevo.getStock()>=0) {
			return service.update(original,nuevo);
		}else if(nuevo.getPuntoDePedido()<0) {
			error+="el punto de pedido no debe ser negativo, ";
		}else if(nuevo.getStock()<0) {
			error+="el stock no debe ser negativo";
		}
		return new Mensaje(false,error);
	}
	
	public List<StockInsumo> getAll(){
		return service.getAll();
	}
}
