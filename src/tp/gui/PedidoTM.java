package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.PedidoController;
import tp.dominio.Pedido;

public class PedidoTM extends AbstractTableModel {
	PedidoController controller;
	
	private List<Pedido> data;
	private String[] columnNames = {"Id Pedido","Destino","Fecha Solicitud","Fecha Maxima"};
	
	public PedidoTM() {
		recargarTabla();
		
	}
	
	public void recargarTabla() {
		controller = new PedidoController();
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
		Pedido temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getId_pedido();
		case 1:
			return temp.getPlanta_origen();
		case 2:
			return temp.getPlanta_destino();
		case 3:
			return temp.getFecha_solicitud();
		case 4:
			return temp.getFecha_maxima();
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
	
	public Pedido getPedido(Integer row) {
		return data.get(row);
	}
}