package tp.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.PlantaController;
import tp.controller.RutaController;
import tp.dominio.Empresa;
import tp.dominio.Ruta;

public class CaminoTM extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Integer modo;
	private List<List<Ruta>> data = new ArrayList<List<Ruta>>();
	private String[] columnNames1 = {"Camino", "Distancia total (Km)"};
	private String[] columnNames2 = {"Camino", "Duracion total (min)"};
	
	public CaminoTM(Integer modo) {
		this.modo=modo;
	}
	
	public void recargarTabla(String nombre_origen, String nombre_destino) {
		PlantaController pc = new PlantaController();
		RutaController rc = new RutaController();
		Empresa emp = new Empresa(rc.getAll(),pc.getAll());
		data = emp.caminosMinimos(nombre_origen, nombre_destino, modo);
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int col) {
		if(modo==0) return columnNames1[col];
		else return columnNames2[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Ruta> temp = data.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return temp.toString();
		case 1:
			if(modo==0) return temp.parallelStream().mapToDouble(r->r.getDistancia_en_km()).sum();
			else return temp.parallelStream().mapToDouble(r->r.getDuracion_en_minutos()).sum();
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
