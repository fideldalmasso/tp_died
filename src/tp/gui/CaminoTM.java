package tp.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import tp.controller.PlantaController;
import tp.controller.RutaController;
import tp.dominio.Camion;
import tp.dominio.Empresa;
import tp.dominio.Modelo;
import tp.dominio.Planta;
import tp.dominio.Ruta;

public class CaminoTM extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private Camion camion= new Camion(null, null, null,null,0D,0D, null);
	private List<List<Ruta>> data = new ArrayList<List<Ruta>>();
	private String[] columnNames = {"Camino", "Distancia (Km)", "Duracion (min)", "Costo total"};
	
	public void recargarTabla(String nombre_origen, String nombre_destino) {
		PlantaController pc = new PlantaController();
		RutaController rc = new RutaController();
		Empresa emp = new Empresa(rc.getAll(),pc.getAll());
		data = emp.caminosMinimos(nombre_origen, nombre_destino, 0);
		data.addAll(emp.caminosMinimos(nombre_origen, nombre_destino, 1));
	}
	
	public void recargarTabla(Empresa emp, String nombre_origen, String nombre_destino) {
		data = emp.caminosMinimos(nombre_origen, nombre_destino, 0);
		data.addAll(emp.caminosMinimos(nombre_origen, nombre_destino, 1));
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Ruta> temp = data.get(rowIndex);
		Double distancia = temp.parallelStream().mapToDouble(r->r.getDistancia_en_km()).sum();
		Double duracion = temp.parallelStream().mapToDouble(r->r.getDuracion_en_minutos()).sum();
		Double costo = distancia*camion.getCosto_por_km()+(duracion*camion.getCosto_por_hora()/60D);
		switch(columnIndex) {
		case 0:
			return temp.toString();
		case 1:
			return distancia;
		case 2:
			return duracion;
		case 3:
			return costo;
		}
		return 0;
	}
	
	@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public List<Ruta> getCamino(Integer row) {
		return data.get(row);
	}
	
	public void setCamion(Camion camion) {
		this.camion=camion;
	}
	
	public Camion getCamion() {
		return camion;
	}
	
	public void setData (List<Ruta> camino) {
		List<List<Ruta>> caminos = new ArrayList<List<Ruta>>();
		caminos.add(camino);
		this.data = caminos;
	}
	
	public List<List<Ruta>> getData() {
		return data;
	}
	
}
