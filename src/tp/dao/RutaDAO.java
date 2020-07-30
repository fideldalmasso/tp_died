package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

public class RutaDAO implements Registrable<Ruta>{
	
	public RutaDAO() {
		super();
	}
	
	@Override
	public Boolean add(Ruta r) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Ruta VALUES (?,?,?,?,?,?);");
			pstm.setString(1, r.getId_ruta());
			pstm.setInt(2, Integer.parseInt(r.getPlanta_origen().getId_planta()));
			pstm.setInt(3, Integer.parseInt(r.getPlanta_destino().getId_planta()));
			pstm.setDouble(4, r.getDistancia_en_km());
			pstm.setDouble(5, r.getDuracion_en_minutos());
			pstm.setDouble(6, r.getPeso_maximo_por_dia_en_kg());
			return pstm.executeUpdate() == 1;
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return false;
	}
	
	@Override
	public Boolean delete(String ...id_ruta) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Ruta WHERE id_ruta=?;");
			pstm.setString(1, id_ruta[0]);
			return pstm.executeUpdate() == 1;
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return false;
	}
	
	@Override
	public Boolean update(Ruta original, Ruta nueva) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Ruta SET "
					+ "distancia_en_km=?, "
					+ "duracion_en_minutos=?, "
					+ "peso_maximo_por_dia_en_kg=? "
					+ "WHERE id_ruta=?");
			pstm.setDouble(1, nueva.getDistancia_en_km());
			pstm.setDouble(2, nueva.getDuracion_en_minutos());
			pstm.setDouble(3, nueva.getPeso_maximo_por_dia_en_kg());
			pstm.setString(4, original.getId_ruta());
			return pstm.executeUpdate() == 1;
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return false;
	}
	
	@Override
	public Optional<Ruta> get(String ...id_ruta) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Ruta> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Ruta WHERE id_ruta=?;"); 
			pstm.setString(1, id_ruta[0]);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return Optional.of(parsearRS(rs));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarRs(rs);
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return m;
	}
	
	@Override
	public List<Ruta> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Ruta> lista = new ArrayList<Ruta>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Ruta;");
			 rs = pstm.executeQuery();
			while(rs.next()) {
				lista.add(parsearRS(rs));
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarRs(rs);
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return lista;
	}
	
	
	private Ruta parsearRS(ResultSet rs) throws SQLException {
		return new Ruta(rs.getString(1),
							new Planta(Integer.toString(rs.getInt(2))),
							new Planta(Integer.toString(rs.getInt(3))),
							rs.getDouble(4),
							rs.getDouble(5),
							rs.getDouble(6)); 
	}
	
}


