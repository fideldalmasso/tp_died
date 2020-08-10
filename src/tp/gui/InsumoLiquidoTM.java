package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.InsumoLiquidoController;
import tp.dominio.InsumoLiquido;

public class InsumoLiquidoTM extends AbstractTableModel {

	InsumoLiquidoController controller;
	
	private List<InsumoLiquido> data;
	private String[] columnNames = {"ID", "Descripción","Unidad","Costo x U.","Densidad","Stock total"};
	
	public InsumoLiquidoTM() {
		recargarTabla();
		
	}
	
	public void recargarTabla() {
		controller = new InsumoLiquidoController();
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
		InsumoLiquido temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getId_insumo();
		case 1: 
			return temp.getDescripcion();
		case 2:
			return temp.getUnidad_de_medida().toString();
		case 3: 
			return temp.getCosto_por_unidad();
		case 4: 
			return temp.getDensidad();
		case 5:
			return temp.getStockTotal();
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

}



