package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.StockInsumo;

public class StockInsumoDAO {
	
	public StockInsumoDAO() {
		super();
	}
	
	public Boolean add(StockInsumo s) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.StockInsumo VALUES (?,?,?,?);");
			pstm.setInt(1, Integer.parseInt(s.getPlanta()));
			pstm.setInt(2, Integer.parseInt(s.getInsumo()));
			pstm.setInt(3, s.getStock());
			pstm.setInt(4, s.getPuntoDePedido());
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
	
	public Boolean delete(StockInsumo s) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.StockInsumo WHERE id_planta=? AND id_insumo=?;");
			pstm.setInt(1, Integer.parseInt(s.getPlanta()));
			pstm.setInt(2, Integer.parseInt(s.getInsumo()));
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
	
	public Boolean update(StockInsumo original, StockInsumo nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.StockInsumo SET stock=?, punto_de_pedido=? WHERE id_planta=? AND id_insumo=?");
			pstm.setInt(1, nuevo.getStock());
			pstm.setInt(2, nuevo.getPuntoDePedido());
			pstm.setInt(3, Integer.parseInt(original.getPlanta()));
			pstm.setInt(4, Integer.parseInt(original.getInsumo()));
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
	
	public Optional<StockInsumo> get(StockInsumo s) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<StockInsumo> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.StockInsumo WHERE id_planta=? AND id_insumo=? ;");
			pstm.setInt(1, Integer.parseInt(s.getPlanta()));
			pstm.setInt(2, Integer.parseInt(s.getInsumo()));
			rs = pstm.executeQuery();
			if(rs.next()) {
				//Planta marcaTemp = new Planta(rs.getString(2));
				return  Optional.of(new StockInsumo(Integer.toString(rs.getInt(1)),
						Integer.toString(rs.getInt(2)),
						rs.getInt(3),
						rs.getInt(4)));
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
	
	public List<StockInsumo> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<StockInsumo> lista = new ArrayList<StockInsumo>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Planta;");
			 rs = pstm.executeQuery();
			 while(rs.next()) {
				lista.add(new StockInsumo(Integer.toString(rs.getInt(1)),
						Integer.toString(rs.getInt(2)),
						rs.getInt(3),
						rs.getInt(4)));
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
