package base_de_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entidades_dominio.*;

public class ModeloDAO {
	
	public ModeloDAO() {
		super();
	}
	
	public Boolean add(Modelo m) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Modelo VALUES (?,?);");
			pstm.setString(1, m.getNombre());
			pstm.setString(2, m.getMarca().getNombre());
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
					"DELETE FROM tp.Modelo WHERE nombre=?;");
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
	
	public Boolean update(Modelo original, Modelo nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Modelo SET nombre=?, marca=? WHERE nombre=?");
			pstm.setString(1, nuevo.getNombre());
			pstm.setString(2, nuevo.getMarca().getNombre());
			pstm.setString(3, original.getNombre());
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
	
	public Optional<Modelo> getModelo(String nombre) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Modelo> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Modelo WHERE nombre=?;");
			pstm.setString(1, nombre);
			rs = pstm.executeQuery();
			if(rs.next()) {
				Marca marcaTemp = new Marca(rs.getString(2));
				return  Optional.of(new Modelo(rs.getString(1),marcaTemp));
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
	
	public List<Modelo> getModelos(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Modelo> lista = new ArrayList<Modelo>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Modelo;");
			 rs = pstm.executeQuery();
			while(rs.next()) {
				Marca marcaTemp = new Marca(rs.getString(2));
				lista.add(new Modelo(rs.getString(1),marcaTemp));
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


