package tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

public class PedidoDAO implements Registrable<Pedido>{
	
	public PedidoDAO() {
		super();
	}
	
	@Override
	public Boolean add(Pedido en) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Pedido VALUES (?,?,?,?,?,?,?,?,?);");
			pstm.setString(1, en.getId_pedido());
			pstm.setString(2, en.getPlanta_origen().getId_planta());
			pstm.setString(3, en.getPlanta_destino().getId_planta());
			pstm.setString(4, en.getEnvio().getId_envio());
			pstm.setDate(5,Date.valueOf(en.getFecha_solicitud()));
			pstm.setDate(6, Date.valueOf(en.getFecha_entrega()));
			pstm.setDate(7, Date.valueOf(en.getFecha_maxima()));
			pstm.setString(8, en.getEstado_pedido().toString());
			pstm.setDouble(9, en.getCosto_pedido());
			
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
	public Boolean delete(String ...id_Pedido) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Pedido WHERE id_Pedido=?;");
			pstm.setString(1, id_Pedido[0]);
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
	public Boolean update(Pedido original, Pedido nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Pedido SET id_Pedido=?, id_planta_origen=?, id_planta_destino=?, id_envio=?, "
					+ "fecha_solicitud =?, fecha_entrega=?, fecha_maxima=?, estado_pedido=?, costo_pedido=? WHERE id_Pedido=?");
			pstm.setString(1, nuevo.getId_pedido());
			pstm.setString(2, nuevo.getPlanta_origen().getId_planta());
			pstm.setString(3, nuevo.getPlanta_destino().getId_planta());
			pstm.setString(4, nuevo.getEnvio().getId_envio());
			pstm.setDate(5,Date.valueOf(nuevo.getFecha_solicitud()));
			pstm.setDate(6, Date.valueOf(nuevo.getFecha_entrega()));
			pstm.setDate(7, Date.valueOf(nuevo.getFecha_maxima()));
			pstm.setString(8, nuevo.getEstado_pedido().toString());
			pstm.setDouble(9, nuevo.getCosto_pedido());
			pstm.setString(1, original.getId_pedido());
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
	public Optional<Pedido> get(String ...id_Pedido) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Pedido> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Pedido WHERE id_Pedido=?;");
			pstm.setString(1, id_Pedido[0]);
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
	public List<Pedido> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Pedido;");
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
	
	Pedido parsearRS(ResultSet rs) throws SQLException {
		
		return new Pedido();
		
//		return new Pedido(rs.getString(1), 
//							rs.getDate(5),
//							rs.getDate(6),
//							rs.getDate(7)
//							rs.getDouble(9), //invocar a envioDao y crear el envio invocar a plantaDAO y crear las plantas
//							Planta plantaDestino);
	}
	
}


