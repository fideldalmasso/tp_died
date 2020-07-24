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
			pstm.setString(1, en.getId());
			pstm.setString(2, en.getPlantaOrigen().getId_planta());
			pstm.setString(3, en.getPlantaDestino().getId_planta());
			pstm.setString(4, en.getEnvio().getId_envio());
			pstm.setDate(5,Date.valueOf(en.getFechaSolicitud()));
			pstm.setDate(6, Date.valueOf(en.getFechaEntrega()));
			pstm.setDate(7, Date.valueOf(en.getFechaMaxima()));
			pstm.setString(8, en.getEstado().toString());
			pstm.setDouble(9, en.getCosto());
			
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
			pstm.setString(1, nuevo.getId());
			pstm.setString(2, nuevo.getPlantaOrigen().getId_planta());
			pstm.setString(3, nuevo.getPlantaDestino().getId_planta());
			pstm.setString(4, nuevo.getEnvio().getId_envio());
			pstm.setDate(5,Date.valueOf(nuevo.getFechaSolicitud()));
			pstm.setDate(6, Date.valueOf(nuevo.getFechaEntrega()));
			pstm.setDate(7, Date.valueOf(nuevo.getFechaMaxima()));
			pstm.setString(8, nuevo.getEstado().toString());
			pstm.setDouble(9, nuevo.getCosto());
			pstm.setString(1, original.getId());
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
		
		
		return new Pedido(rs.getString(1), rs.getDate(5),rs.getDate(6),rs.getDate(7)
				 rs.getDouble(9), /*invocar a envioDao y crear el envio*/, /* invocar a plantaDAO y crear las plantas*/,
					Planta plantaDestino);
	}
	
}


