package tp.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import tp.controller.PedidoController;
import tp.dao.PedidoDAO;
import tp.dominio.Pedido;
import tp.enumerados.Estado;

public class PedidoTM extends AbstractTableModel {
	PedidoController controller;
	Estado estado;
	private List<Pedido> data;
	private String[] columnNames = {"Id Pedido","Destino","Fecha Solicitud","Fecha Maxima"};
	
	public PedidoTM(Estado estado) {
		this.estado=estado;
		recargarTabla();
	}
	
	public void recargarTabla() {
		PedidoDAO pd = new PedidoDAO();
		this.data = pd.getCreados();
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
		if(this.estado==Estado.CREADA) {
			switch(columnIndex) {
			case 0:
				return temp.getId_pedido();
			case 1:
				return temp.getPlanta_destino();
			case 2:
				return temp.getFecha_solicitud();
			case 3:
				return temp.getFecha_maxima();
			}
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
