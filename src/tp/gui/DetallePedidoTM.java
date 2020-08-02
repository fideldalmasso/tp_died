package tp.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.InsumoController;
import tp.dominio.DetallePedido;
import tp.dominio.Insumo;

public class DetallePedidoTM extends AbstractTableModel{
	
	
	private List<DetallePedido> data = new ArrayList<DetallePedido>();
	private String[] columnNames = {"Nombre", "Precio unitario","Cantidad","Monto total"};
	
	
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
		DetallePedido temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getInsumo().getDescripcion();
		case 1: 
			return temp.getInsumo().getCosto_por_unidad();
		case 2:
			return temp.getCantidad_de_unidades();
		case 3: 
			return temp.getPrecio();
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
