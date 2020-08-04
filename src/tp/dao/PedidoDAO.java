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
					"INSERT INTO tp.Pedido VALUES (default,?,?,?,?,?,?,CAST (? AS tp.EstadoPedido),?);");
			pstm.setNull(1,0);
			pstm.setInt(2,Integer.parseInt(en.getPlanta_destino().getId_planta()));
			pstm.setNull(3,0);
			pstm.setDate(4,Date.valueOf(en.getFecha_solicitud()));
			pstm.setNull(5,0);
			pstm.setDate(6, Date.valueOf(en.getFecha_maxima()));
			pstm.setString(7,en.getEstado_pedido().toString());
			pstm.setNull(8,0);
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
					"UPDATE tp.Pedido SET id_envio=?, fecha_entrega=?, estado_pedido= CAST(? AS tp.estadopedido), costo_pedido=? WHERE id_Pedido=?");
			pstm.setInt(1,Integer.parseInt(nuevo.getEnvio().getId_envio()));
			pstm.setDate(2,Date.valueOf(nuevo.getFecha_entrega()));
			pstm.setString(3,nuevo.getEstado_pedido().toString());
			pstm.setDouble(4,nuevo.getCosto_pedido());
			pstm.setInt(5,Integer.parseInt(original.getId_pedido()));
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
	
	public Boolean updateEstado(Pedido original, Pedido nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Pedido SET estado_pedido = CAST(? AS tp.estadopedido) WHERE id_Pedido=?");
			pstm.setString(1,nuevo.getEstado_pedido().toString());
			pstm.setInt(2,Integer.parseInt(original.getId_pedido()));
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
	
	public List<Pedido> getEstado(Estado est){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Pedido WHERE estado_pedido=CAST(? AS tp.estadopedido);");
			pstm.setString(1, est.toString());
			rs = pstm.executeQuery();
			PlantaDAO dao1 = new PlantaDAO();
			EnvioDAO dao2 = new EnvioDAO();
			while(rs.next()) {
				Integer id_pedido = (Integer) rs.getObject(1);
				Integer id_planta_origen = (Integer) rs.getObject(2);
				Integer id_planta_destino = (Integer) rs.getObject(3);
				Integer id_envio = (Integer) rs.getObject(4);
				Date fecha_solicitud = (Date) rs.getObject(5);
				Date fecha_entrega = (Date) rs.getObject(6);
				Date fecha_maxima = (Date) rs.getObject(7);
				String estado = rs.getString(8);
				Double costo = (Double) rs.getObject(9);
				
				Planta planta_origen=null, planta_destino=null;
				Envio envio=null;
				LocalDate ld_fecha_solicitud=null, ld_fecha_entrega=null, ld_fecha_maxima=null;
				
				if(id_planta_origen != null) planta_origen = dao1.get(Integer.toString(id_planta_origen)).get();
				if(id_planta_destino != null) planta_destino = dao1.get(Integer.toString(id_planta_destino)).get();
				if(id_envio != null)  envio = dao2.get(Integer.toString(id_envio)).get();
				if(fecha_solicitud != null)  ld_fecha_solicitud = fecha_solicitud.toLocalDate();
				if(fecha_entrega != null)  ld_fecha_entrega = fecha_entrega.toLocalDate();
				if(fecha_maxima != null)  ld_fecha_maxima = fecha_maxima.toLocalDate();
				Estado e_estado = Estado.valueOf(estado);
				
				lista.add(new Pedido(Integer.toString(id_pedido),
						planta_origen,
						planta_destino,
						envio,
						ld_fecha_solicitud,
						ld_fecha_entrega,
						ld_fecha_maxima,
						e_estado,
						costo));
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
	
	public String getId() {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Integer max=0;
		try {
			pstm = con.prepareStatement(
					"SELECT id_pedido FROM tp.pedido;");
			rs = pstm.executeQuery();
			while (rs.next()) {
				if(rs.getInt(1)>max) max=rs.getInt(1);
			};
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarRs(rs);
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return Integer.toString(max);
	}
	
}


