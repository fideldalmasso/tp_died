package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.CamionController;
import tp.controller.Utilidades;
import tp.dominio.Camion;

public class CamionTM extends AbstractTableModel {

	CamionController controller;

	
	private List<Camion> data;
	private String[] columnNames = {"Id camion","Id Planta","Modelo","Distancia (km)","Costo por km","Costo por hora","Fecha de compra"};
	
	public CamionTM() {
		controller = new CamionController();
		recargarTabla();
		
	}
	
	public void recargarTabla() {
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
	
	
	public Camion getObject(int row) {
		return data.get(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Camion temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.getId_camion();
		case 1:
			return temp.getPlanta().getId_planta();
		case 2:
			return temp.getModelo().getNombre();
		case 3:
			return temp.getDistancia_recorrida_en_km().toString();
		case 4:
			return temp.getCosto_por_hora().toString();
		case 5:
			return temp.getCosto_por_km().toString();
		case 6:
			return Utilidades.formatearFecha(temp.getFecha_de_compra());
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



