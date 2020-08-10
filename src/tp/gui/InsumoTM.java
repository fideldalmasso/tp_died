package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import tp.controller.InsumoController;
import tp.dominio.DetallePedido;
import tp.dominio.Insumo;

public class InsumoTM extends AbstractTableModel {

	InsumoController controller;
	
	private List<Insumo> data;
	private String[] columnNames = {"ID", "Descripción","Unidad","Costo"};
	
	public InsumoTM() {
		recargarTabla();
		
	}
	
	public void recargarTabla() {
		controller = new InsumoController();
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
		Insumo temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getId_insumo();
		case 1: 
			return temp.getDescripcion();
		case 2:
			return temp.getUnidad_de_medida().toString();
		case 3: 
			return temp.getCosto_por_unidad();
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
	
	public Insumo getInsumo(Integer row) {
		return data.get(row);
	}
	
	public void addInsumo(DetallePedido dp) {
		data.add(dp.getInsumo());
	}
	
	public void deleteInsumo(Integer row) {
		data.remove(data.get(row));
	}

}



