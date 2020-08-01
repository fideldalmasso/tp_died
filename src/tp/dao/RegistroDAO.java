package tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.Registro;

public class RegistroDAO {

	public RegistroDAO() {
		super();
	}
	
	public Boolean add(Registro r) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Registro VALUES (?,?,?,?,?,?);");
			pstm.setInt(1, Integer.parseInt(r.getPlanta()));
			pstm.setInt(2, Integer.parseInt(r.getInsumo()));
			pstm.setDate(3, Date.valueOf(r.getFechaRegistro()));
			pstm.setInt(4, r.getStock());
			pstm.setInt(5, r.getVariacion());
			pstm.setInt(6, r.getPuntoDePedido());
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
	
	public Boolean delete(Registro r) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Registro WHERE id_planta=? AND id_insumo=? AND fecha_registro=?;");
			pstm.setInt(1, Integer.parseInt(r.getPlanta()));
			pstm.setInt(2, Integer.parseInt(r.getInsumo()));
			pstm.setDate(2, Date.valueOf(r.getFechaRegistro()));
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
	
	public Boolean update(Registro original, Registro nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Rregistro SET stock=?, variacion=?, punto_de_pedido=? WHERE id_planta=? AND id_insumo=? AND fecha_registro=? ;");
			pstm.setInt(1, nuevo.getStock());
			pstm.setInt(2, nuevo.getVariacion());
			pstm.setInt(3, nuevo.getPuntoDePedido());
			pstm.setInt(4, Integer.parseInt(original.getPlanta()));
			pstm.setInt(5, Integer.parseInt(original.getInsumo()));
			pstm.setDate(6, Date.valueOf(original.getFechaRegistro()));
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
	
	public Optional<Registro> get(Registro r) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Registro> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.StockInsumo WHERE id_planta=? AND id_insumo=? AND fecha_registro=?;");
			pstm.setInt(1, Integer.parseInt(r.getPlanta()));
			pstm.setInt(2, Integer.parseInt(r.getInsumo()));
			pstm.setDate(2, Date.valueOf(r.getFechaRegistro()));
			rs = pstm.executeQuery();
			if(rs.next()) {
				//Planta marcaTemp = new Planta(rs.getString(2));
				return  Optional.of(new Registro(Integer.toString(rs.getInt(1)),
						Integer.toString(rs.getInt(2)),
						rs.getDate(3).toLocalDate(),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6)));
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
	
	public List<Registro> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Registro> lista = new ArrayList<Registro>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Registro;");
			 rs = pstm.executeQuery();
			 while(rs.next()) {
				lista.add(new Registro(Integer.toString(rs.getInt(1)),
						Integer.toString(rs.getInt(2)),
						rs.getDate(3).toLocalDate(),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6)));
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
