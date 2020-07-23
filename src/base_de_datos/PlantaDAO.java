package base_de_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entidades_dominio.*;

public class PlantaDAO implements Registrable<Planta> {
	
	public PlantaDAO() {
		super();
	}
	
	@Override
	public Boolean add(Planta p) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Planta VALUES (?,?);");
			pstm.setString(1, p.getId_planta());
			pstm.setString(2, p.getNombre());
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
	public Boolean delete(String ...id_planta) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Planta WHERE id_planta=?;");
			pstm.setString(1, id_planta[0]);
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
	public Boolean update(Planta original, Planta nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Planta SET id_planta=?, nombre=?,WHERE id_planta=?");
			pstm.setString(1, nuevo.getId_planta());
			pstm.setString(2, nuevo.getNombre());
			pstm.setString(3, original.getId_planta());
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
	public Optional<Planta> get(String ...id_planta) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Planta> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Planta WHERE id_planta=?;"); 
			pstm.setString(1, id_planta[0]);
			rs = pstm.executeQuery();
			if(rs.next()) {
				//Planta marcaTemp = new Planta(rs.getString(2));
				return  Optional.of(new Planta(rs.getString(1),rs.getString(2)));
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
	public List<Planta> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Planta> lista = new ArrayList<Planta>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Planta;");
			 rs = pstm.executeQuery();
			while(rs.next()) {
				lista.add(new Planta(rs.getString(1),rs.getString(2)));
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


