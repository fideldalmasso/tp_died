package base_de_datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entidades_dominio.*;

public class CamionDAO implements Registrable<Camion>{
	
	public CamionDAO() {
		super();
	}
	
	@Override
	public Boolean add(Camion c) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Camion VALUES (?,?,?,?,?,?,?);");
			pstm.setString(1, c.getId_camion());
			pstm.setString(2, c.getPlanta().getId_planta());
			pstm.setString(3, c.getModelo().getNombre());
			pstm.setDouble(4, c.getDistancia_recorrida_en_km());
			pstm.setDouble(5, c.getCosto_por_hora());
			pstm.setDouble(6, c.getCosto_por_km());
			pstm.setDate(7, Date.valueOf(c.getFecha_de_compra()));
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
	public Boolean delete(String ...id_camion) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Camion WHERE id_camion=?;");
			pstm.setString(1, id_camion[0]);
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
	public Boolean update(Camion original, Camion nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Camion SET id_camion=?, "
					+ "id_planta=?, "
					+ "modelo=?, "
					+ "distancia_recorrida_en_km=?, "
					+ "costo_por_km=?, "
					+ "costo_por_hora=?, "
					+ "fecha_de_compra=?, "
					+ "WHERE id_camion=?");
			pstm.setString(1, nuevo.getId_camion());
			pstm.setString(2, nuevo.getPlanta().getId_planta());
			pstm.setString(3, nuevo.getModelo().getNombre());
			pstm.setDouble(4, nuevo.getDistancia_recorrida_en_km());
			pstm.setDouble(5, nuevo.getCosto_por_hora());
			pstm.setDouble(6, nuevo.getCosto_por_km());
			pstm.setDate(7, Date.valueOf(nuevo.getFecha_de_compra()));
			pstm.setString(8, original.getId_camion());
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
	public Optional<Camion> get(String ...id_camion) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Camion> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Camion WHERE id_camion=?;"); 
			pstm.setString(1, id_camion[0]);
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
	public List<Camion> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Camion> lista = new ArrayList<Camion>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Camion;");
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
	
	
	private Camion parsearRS(ResultSet rs) throws SQLException {
		ModeloDAO modeloDAOTemp = new ModeloDAO();
		Modelo modeloTemp = modeloDAOTemp.get(rs.getString(4)).get();
				
		return new Camion(rs.getString(1),
							new Planta(rs.getString(2)),
							modeloTemp,
							rs.getDouble(4),
							rs.getDouble(5),
							rs.getDouble(6),
							rs.getDate(7).toLocalDate()); 
	}
	
}


