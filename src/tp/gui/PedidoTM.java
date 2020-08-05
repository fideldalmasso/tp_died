package tp.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import tp.controller.PedidoController;
import tp.dominio.Pedido;
import tp.enumerados.Estado;

public class PedidoTM extends AbstractTableModel {
	PedidoController controller;
	Estado estado;
	private List<Pedido> data;
	private String[] columnNames = {"Id Pedido","Destino","Fecha Solicitud","Fecha Maxima"};
	
	public PedidoTM(Estado estado) {
		this.estado=estado;
		if(this.estado==Estado.CREADA || this.estado==Estado.CANCELADA) {
			String[] aux = {"Id Pedido","Destino","Fecha Solicitud","Fecha Maxima"};
			columnNames = aux;
		}else if(this.estado==Estado.PROCESADA) {
			String[] aux = {"Id Pedido","Origen","Destino","Id_envio","Fecha Solicitud","Fecha Maxima","Costo Total"};
			columnNames = aux;
		}else if(this.estado==Estado.ENTREGADA){
			String[] aux = {"Id Pedido","Origen","Destino","Id_envio","Fecha Solicitud","Fecha Entrega","Fecha Maxima","Costo Total"};
			columnNames = aux;
		}
		recargarTabla();
	}
	
	
	public void recargarTabla() {
		PedidoController pc = new PedidoController();
		this.data = pc.getEstado(this.estado);
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
		if(this.estado==Estado.CREADA || this.estado==Estado.CANCELADA) {
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
		} else if (this.estado==Estado.PROCESADA) {
			switch(columnIndex) {
			case 0:
				return temp.getId_pedido();
			case 1:
				return temp.getPlanta_origen();
			case 2:
				return temp.getPlanta_destino();
			case 3:
				return temp.getEnvio().getId_envio();
			case 4:
				return temp.getFecha_solicitud();
			case 5:
				return temp.getFecha_maxima();
			case 6:
				return temp.getCosto_pedido();
			}
		} else {
			switch(columnIndex) {
			case 0:
				return temp.getId_pedido();
			case 1:
				return temp.getPlanta_origen();
			case 2:
				return temp.getPlanta_destino();
			case 3:
				return temp.getEnvio().getId_envio();
			case 4:
				return temp.getFecha_solicitud();
			case 5:
				return temp.getFecha_entrega();
			case 6:
				return temp.getFecha_maxima();
			case 7:
				return temp.getCosto_pedido();
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
