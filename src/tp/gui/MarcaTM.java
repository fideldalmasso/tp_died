package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.dominio.Marca;

public class MarcaTM extends AbstractTableModel {

	
	private List<Marca> data;
	private String[] columnNames = {"Nombre"};
	
	public MarcaTM(List<Marca> lista) {
		this.data = lista;
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
