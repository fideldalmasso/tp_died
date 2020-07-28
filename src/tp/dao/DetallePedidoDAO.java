package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

public class DetallePedidoDAO implements Registrable<DetallePedido>{

	public DetallePedidoDAO() {
		super();
	}

	@Override
	public Boolean add(DetallePedido dp) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.DetallePedido VALUES (?,?,?);");
			pstm.setString(1, dp.getInsumo().getId_insumo());
			pstm.setString(2, dp.getPedido().getId_pedido());
			pstm.setInt(3,dp.getCantidad_de_unidades());
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
	public Boolean delete(String ...ids) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.DetallePedido WHERE id_insumo=? AND id_pedido=?;");
			pstm.setString(1, ids[0]);
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

	@Override
	public Boolean update(DetallePedido original, DetallePedido nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.DetallePedido SET id_insumo=? AND id_pedido=? AND cantidad_de_unidades=? WHERE id_insumo=? AND id_pedido=?");
			pstm.setString(1, nuevo.getInsumo().getId_insumo());
			pstm.setString(2, nuevo.getPedido().getId_pedido());
			pstm.setInt(3,nuevo.getCantidad_de_unidades());
			pstm.setString(1, original.getInsumo().getId_insumo());
			pstm.setString(2, original.getPedido().getId_pedido());
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
	public Optional<DetallePedido> get(String ...ids) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<DetallePedido> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.DetallePedido WHERE id_insumo=? AND id_pedido=?;");
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

	@Override
	public List<DetallePedido> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<DetallePedido> lista = new ArrayList<DetallePedido>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.DetallePedido;");
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

	

	DetallePedido parsearRS(ResultSet rs) throws SQLException {

		Insumo insumoTemp = new InsumoDAO().get(rs.getString(1)).get();
		
		//Ruta rutaTemp = new RutaDAO().get(rs.getString(2)).get();

		Pedido pedidoTemp = new PedidoDAO().get(rs.getString(2)).get();
		
		return new DetallePedido(insumoTemp, pedidoTemp, rs.getInt(3));
	}
}

