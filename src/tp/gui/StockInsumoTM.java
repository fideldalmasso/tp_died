package tp.gui;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import tp.controller.StockInsumoController;
import tp.dao.InsumoDAO;
import tp.dominio.Insumo;
import tp.dominio.Planta;
import tp.dominio.StockInsumo;

public class StockInsumoTM extends AbstractTableModel{
	StockInsumoController controller;

	private List<StockInsumo> data;
	private String[] columnNames = {"Id Insumo","Nombre Insumo","Stock","Punto de Pedido"};
	
	public StockInsumoTM(Planta planta) {
		controller = new StockInsumoController();
		this.data = controller.getAll()
				.parallelStream().filter(si -> si.getPlanta().equals(planta.getId_planta()))
				.collect(Collectors.toList());
	}
	
	public StockInsumoTM() {
		controller = new StockInsumoController();
		recargarTabla();
	}
	
	public void recargarTabla() {
		this.data = controller.getAll();
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StockInsumo temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getInsumo();
		case 1:
			InsumoDAO dao = new InsumoDAO();
			Insumo insumo = dao.get(temp.getInsumo()).orElse(new Insumo());
			return insumo.getDescripcion();
		case 2:
			return temp.getStock();
		case 3:
			return temp.getPuntoDePedido();
		}
		return null;
	}
	
	@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public StockInsumo getStockInsumo(Integer row) {
		return data.get(row);
	}
}
