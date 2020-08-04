package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

public class EnvioDAO implements Registrable<Envio>{
	
	public EnvioDAO() {
		super();
	}
	
	@Override
	public Boolean add(Envio en) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Envio VALUES (default,?,?);");
			pstm.setString(1, en.getCamion().getId_camion());
			pstm.setDouble(2, en.getCosto_envio());
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
	public Boolean delete(String ...id_envio) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Envio WHERE id_envio=?;");
			pstm.setInt(1, Integer.parseInt(id_envio[0]));
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
	public Boolean update(Envio original, Envio nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Envio SET id_camion=?, costo_envio=? WHERE id_envio=?");
			pstm.setString(1, nuevo.getCamion().getId_camion());
			pstm.setDouble(2, nuevo.getCosto_envio());
			pstm.setInt(3, Integer.parseInt(original.getId_envio()));
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
	public Optional<Envio> get(String ...id_envio) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Envio> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Envio WHERE id_envio=?;");
			pstm.setInt(1, Integer.parseInt(id_envio[0]));
			rs = pstm.executeQuery();
			if(rs.next()) {
				return  Optional.of(parsearRS(rs));
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
	public List<Envio> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Envio> lista = new ArrayList<Envio>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Envio;");
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
	
	Envio parsearRS(ResultSet rs) throws SQLException {
		CamionDAO camionDAO = new CamionDAO();
		Camion camionTemp = camionDAO.get(rs.getString(2)).get();
	
		return new Envio(Integer.toString(rs.getInt(1)),
						camionTemp,
						rs.getDouble(3));
	}
	
	public String getId() {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer max=1;
		try {
			pstm = con.prepareStatement(
					"SELECT id_envio FROM tp.envio ORDER BY 1 LIMIT 1;");
			rs = pstm.executeQuery();
			if (rs.next()) max=rs.getInt(1);
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarRs(rs);
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return Integer.toString(max);
	}
	
}


