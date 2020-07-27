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
					"INSERT INTO tp.Envio VALUES (?,?,?);");
			pstm.setString(1, en.getId_envio());
			pstm.setString(2, en.getCamion().getId_camion());
			pstm.setDouble(3, en.getCosto_envio());
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
			pstm.setString(1, id_envio[0]);
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
					"UPDATE tp.Envio SET id_envio=?, id_camion=?, costo_envio=? WHERE id_envio=?");
			pstm.setString(1, nuevo.getId_envio());
			pstm.setString(2, nuevo.getCamion().getId_camion());
			pstm.setDouble(3, nuevo.getCosto_envio());
			pstm.setString(4, original.getId_envio());
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
			pstm.setString(1, id_envio[0]);
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
		
		return new Envio(rs.getString(1),
						camionTemp,
						rs.getDouble(3));
	}
	
}


