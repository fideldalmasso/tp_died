package tp.gui;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import tp.controller.InsumoController;
import tp.controller.InsumoGeneralController;
import tp.controller.InsumoLiquidoController;
import tp.controller.MarcaController;
import tp.dominio.Insumo;
import tp.dominio.InsumoGeneral;
import tp.dominio.InsumoLiquido;
import tp.dominio.Marca;

public class InsumoGeneralTM extends AbstractTableModel {

	InsumoGeneralController controller;
	
	private List<InsumoGeneral> data;
	private String[] columnNames = {"ID", "Descripción","Unidad","Costo x U.","Peso x U.","Stock total"};
	
	public InsumoGeneralTM() {
		recargarTabla();
		
	}
	
	public void recargarTabla() {
		controller = new InsumoGeneralController();
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
		InsumoGeneral temp = data.get(rowIndex);
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
			return temp.getPesoPorUnidad();
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



