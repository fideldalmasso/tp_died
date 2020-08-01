package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.PlantaController;
import tp.dominio.Planta;

public class PlantaTM extends AbstractTableModel{
	PlantaController controller;
	private List<Planta> data;
	private String[] columnNames = {"Id Planta","Nombre Planta"};
	
	public PlantaTM(){
		this.controller = new PlantaController();
		this.data = this.controller.getAll();
	}
	
	public void recargarTabla() {
		this.data = this.controller.getAll();
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		Planta actual = data.get(r);
		switch(c) {
		case 0:
			return actual.getId_planta();
		case 1:
			return actual.getNombre();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public Planta getPlanta(Integer row) {
		return data.get(row);
	}

}
