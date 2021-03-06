package tp.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.DetallePedidoController;
import tp.dominio.DetallePedido;
import tp.dominio.Insumo;

public class DetallePedidoTM extends AbstractTableModel{
	DetallePedidoController controller = new DetallePedidoController();
	
	private List<DetallePedido> data = new ArrayList<DetallePedido>();
	private String[] columnNames = {"Nombre", "Precio unitario","Cantidad","Monto total"};
	
	public void recargarTabla(String id_pedido) {
		controller = new DetallePedidoController();
		this.data = controller.getAll(id_pedido);
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
	
	public List<DetallePedido> getAll(){
		return data;
	}
	
	public DetallePedido getDetallePedido(Integer row) {
		return data.get(row);
	}
	
	public void addDetallePedido(Insumo insumo) {
		data.add(new DetallePedido(insumo,null,0));
	}
	
	public void deleteDetallePedido(Integer row) {
		data.remove(data.get(row));
	}
}
