package tp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.RutaController;
import tp.dominio.Ruta;

public class RutaTM extends AbstractTableModel{
	RutaController controller;
	private List<Ruta> data;
	private String[] columnNames = {"Nombre","Origen","Destino","Distancia","Duracion","Peso Maximo"};
	
	public RutaTM(){
		this.controller = new RutaController();
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
		Ruta actual = data.get(r);
		switch(c) {
		case 0:
			return actual.getId_ruta();
		case 1:
			return actual.getOrigen().getNombre();
		case 2:
			return actual.getDestino().getNombre();
		case 3:
			return actual.getDistancia();
		case 4:
			return actual.getDuracion_en_minutos();
		case 5:
			return actual.getPeso_maximo_por_dia_en_kg();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public Ruta getRuta(Integer row) {
		return data.get(row);
	}
}
