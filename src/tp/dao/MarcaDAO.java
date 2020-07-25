package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.Marca;

public class MarcaDAO implements Registrable<Marca>{
	
	public MarcaDAO() {
		super();
	}
	
	@Override
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
	
	@Override
	public Boolean delete(String ...nombre) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Marca WHERE nombre=?;");
			pstm.setString(1, nombre[0]);
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
	
	@Override
	public Optional<Marca> get(String ...nombre) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Marca> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Marca WHERE nombre=?;");
			pstm.setString(1, nombre[0]);
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
	
	@Override
	public List<Marca> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Marca> lista = new ArrayList<Marca>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Marca ORDER BY 1;");
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


