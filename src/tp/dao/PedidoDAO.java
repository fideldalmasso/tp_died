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
import tp.enumerados.Estado;

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
					"INSERT INTO tp.Pedido VALUES (default,?,?,?,?,?,?,?,?);");
			pstm.setInt(1, Integer.parseInt(en.getPlanta_origen().getId_planta()));
			pstm.setInt(2, Integer.parseInt(en.getPlanta_destino().getId_planta()));
			pstm.setInt(3, Integer.parseInt(en.getEnvio().getId_envio()));
			pstm.setDate(4,Date.valueOf(en.getFecha_solicitud()));
			pstm.setDate(5, Date.valueOf(en.getFecha_entrega()));
			pstm.setDate(6, Date.valueOf(en.getFecha_maxima()));
			pstm.setString(7, en.getEstado_pedido().toString());
			pstm.setDouble(8, en.getCosto_pedido());
			
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
			pstm.setInt(1, Integer.parseInt(id_Pedido[0]));
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
					"UPDATE tp.Pedido SET fecha_entrega=?, estado_pedido=? WHERE id_Pedido=?");
			pstm.setDate(1, Date.valueOf(nuevo.getFecha_entrega()));
			pstm.setString(2, nuevo.getEstado_pedido().toString());
			pstm.setInt(3, Integer.parseInt(original.getId_pedido()));
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
			pstm.setInt(1, Integer.parseInt(id_Pedido[0]));
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
		
		PlantaDAO dao1 = new PlantaDAO();
		EnvioDAO dao2 = new EnvioDAO();
		Planta plantaTemp1 = dao1.get(Integer.toString(rs.getInt(2))).get();
		Planta plantaTemp2 = dao1.get(Integer.toString(rs.getInt(3))).get();
		Envio envioTemp = dao2.get(Integer.toString(rs.getInt(4))).get();
		
		return new Pedido(Integer.toString(rs.getInt(1)),
							plantaTemp1,
							plantaTemp2,
							envioTemp,
							rs.getDate(5).toLocalDate(),
							rs.getDate(6).toLocalDate(),
							rs.getDate(7).toLocalDate(),
							Estado.valueOf(rs.getString(8)),
							rs.getDouble(9));
		
	}
	
}


