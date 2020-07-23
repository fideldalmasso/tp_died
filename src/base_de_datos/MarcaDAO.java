package base_de_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entidades_dominio.Marca;

public class MarcaDAO {
	
	public MarcaDAO() {
		super();
	}
	
	public Boolean add(Marca m) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Marca VALUES (?);");
			pstm.setString(1, m.getNombre());
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
	
	public Boolean delete(String nombre) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Marca WHERE nombre=?;");
			pstm.setString(1, nombre);
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
	
	public Boolean update(Marca moriginal, Marca mnueva) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Marca SET nombre=? WHERE nombre=?");
			pstm.setString(1, mnueva.getNombre());
			pstm.setString(2, moriginal.getNombre());
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
	
	public Optional<Marca> getMarca(String nombre) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Marca> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Marca WHERE nombre=?;");
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			if(rs.next())
				return  Optional.of(new Marca(rs.getString(1)));
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
	
	public List<Marca> getMarcas(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Marca> lista = new ArrayList<Marca>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Marca;");
			rs = pstm.executeQuery();
			while(rs.next()) {
				lista.add(new Marca(rs.getString(1)));
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
	
}


