package tp.gui;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import tp.controller.MarcaController;
import tp.dominio.Marca;

public class MarcaTM extends AbstractTableModel {

	MarcaController controller;
	
	private List<Marca> data;
	private String[] columnNames = {"Nombre"};
	
	public MarcaTM() {
		recargarTabla();
		
	}
	
	public void recargarTabla() {
		controller = new MarcaController();
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
		Marca temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getNombre();
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



