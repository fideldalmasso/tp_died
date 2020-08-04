package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

public class ASeguirEnDAO {

	public ASeguirEnDAO() {
		super();
	}

	public Boolean add(ASeguirEn a) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.ASeguirEn VALUES (?,?,?);");
			pstm.setInt(1, Integer.parseInt(a.getEnvio().getId_envio()));
			pstm.setString(2, a.getRuta().getId_ruta());
			pstm.setInt(3,a.getOrden());
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

	public Boolean delete(String ...ids) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.ASeguirEn WHERE id_envio=? AND id_ruta=?;");
			pstm.setInt(1, Integer.parseInt(ids[0]));
			pstm.setString(2, ids[1]);
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

	public Boolean update(ASeguirEn nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.ASeguirEn SET  orden=? WHERE id_envio=? AND id_ruta=?");
			pstm.setInt(3,nuevo.getOrden());
			pstm.setInt(4, Integer.parseInt(nuevo.getEnvio().getId_envio()));
			pstm.setString(5, nuevo.getRuta().getId_ruta());
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

	public Optional<ASeguirEn> get(String ...ids) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<ASeguirEn> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.ASeguirEn WHERE id_envio=?, id_ruta=?;");
			pstm.setString(1, ids[0]);
			pstm.setString(2, ids[1]);
			rs = pstm.executeQuery();
			if(rs.next()) {

				return   Optional.of(this.parsearRS(rs));
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

	public List<ASeguirEn> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ASeguirEn> lista = new ArrayList<ASeguirEn>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.ASeguirEn;");
			rs = pstm.executeQuery();
			while(rs.next()) {
				lista.add(this.parsearRS(rs));
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

	

	ASeguirEn parsearRS(ResultSet rs) throws SQLException {

		Envio envioTemp = new EnvioDAO().get(rs.getString(1)).get();

		Ruta rutaTemp = new RutaDAO().get(rs.getString(2)).get();

		return new ASeguirEn(envioTemp, rutaTemp, rs.getInt(3));
	}
}

