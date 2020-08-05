package tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tp.controller.Utilidades;
import tp.dominio.*;

public class CamionDAO implements Registrable<Camion>{

	public CamionDAO() {
		super();
	}

	@Override
	public Boolean add(Camion c) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Camion VALUES (?,?,?,?,?,?,?);");
			pstm.setString(1, c.getId_camion());
			pstm.setInt(2, Integer.parseInt(c.getPlanta().getId_planta()));
			pstm.setString(3, c.getModelo().getNombre());
			pstm.setDouble(4, c.getDistancia_recorrida_en_km());
			pstm.setDouble(5, c.getCosto_por_km());
			pstm.setDouble(6, c.getCosto_por_hora());
			pstm.setDate(7, Date.valueOf(c.getFecha_de_compra()));
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
	public Boolean delete(String ...id_camion) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Camion WHERE id_camion=?;");
			pstm.setString(1, id_camion[0]);
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
	public Boolean update(Camion original, Camion nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.Camion SET id_planta=?, "
							+ "distancia_recorrida_en_km=?, "
							+ "costo_por_km=?, "
							+ "costo_por_hora=? "
							+ "WHERE id_camion=?");
			pstm.setInt(1, Integer.parseInt(nuevo.getPlanta().getId_planta()));
			pstm.setDouble(2, nuevo.getDistancia_recorrida_en_km());
			pstm.setDouble(3, nuevo.getCosto_por_km());
			pstm.setDouble(4, nuevo.getCosto_por_hora());
			pstm.setString(5, original.getId_camion());
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
	public Optional<Camion> get(String ...id_camion) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Camion> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Camion WHERE id_camion=?;"); 
			pstm.setString(1, id_camion[0]);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return Optional.of(parsearRS(rs));
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
	public List<Camion> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Camion> lista = new ArrayList<Camion>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Camion ORDER BY 1;");
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

	private Camion parsearRS(ResultSet rs) throws SQLException {
		//ModeloDAO modeloDAOTemp = new ModeloDAO();
		Modelo modeloTemp = new ModeloDAO().get(rs.getString(3)).get();

		return new Camion(rs.getString(1),
				new Planta(Integer.toString(rs.getInt(2))),
				modeloTemp,
				rs.getDouble(4),
				rs.getDouble(5),
				rs.getDouble(6),
				rs.getDate(7).toLocalDate()); 
	}
	
	//METODOS ADICIONALES-----------------------------------------------------------------------------------
	public List<Camion> query(String [] parametros){
		String query = "SELECT * FROM tp.Camion WHERE ";
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Camion> lista = new ArrayList<Camion>();
		List<String> consultas = new ArrayList<String>();
		
		if(parametros[0]!=null) consultas.add("id_camion LIKE '" + parametros[0] + "%'");
		if(parametros[1]!=null) consultas.add("id_planta = " + parametros[1]);
		if(parametros[2]!=null) consultas.add("modelo = '" + parametros[2] +"'");
		if(parametros[3]!=null) consultas.add("distancia_recorrida_en_km = " + parametros[3]);
		if(parametros[4]!=null) consultas.add("costo_por_km = " + parametros[4]);
		if(parametros[5]!=null) consultas.add("costo_por_hora = " + parametros[5]);
		if(parametros[6]!=null) {
			consultas.add(" fecha_de_compra = ?");
		}
		
		if(consultas.size()==0)
			return this.getAll();
		
		query+= consultas
				.stream()
				.collect(Collectors.joining(" AND "));
		
		try {
			pstm = con.prepareStatement(query);
			if(parametros[6]!=null) {
				pstm.setDate(1, Date.valueOf(Utilidades.parsearFecha(parametros[6])));
			}
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


	public Camion getDisponible(String nombre_planta){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Camion disponible = null;
		try {
			pstm = con.prepareStatement(
					"select ca.id_camion, ca.id_planta, ca.modelo, ca.distancia_recorrida_en_km, ca.costo_por_km, ca.Costo_por_hora, ca.fecha_de_compra " + 
							"from tp.camion ca, tp.planta pl " + 
							"where pl.id_planta=ca.id_planta and pl.nombre=? and not exists( " + 
							"	(select ped.id_pedido " + 
							"	from tp.pedido ped " + 
							"	where ped.estado_pedido='PROCESADA') " + 
							"	intersect " + 
							"	(select ped.id_pedido " + 
							"	from tp.pedido ped, tp.envio env " + 
							"	where ped.id_envio=env.id_envio and env.id_camion=ca.id_camion) " + 
							")" +
							"order by ca.distancia_recorrida_en_km asc "+
					"limit 1");
			pstm.setString(1, nombre_planta);
			rs = pstm.executeQuery();
			if(rs.next()) disponible = parsearRS(rs);
		}catch(Exception e) {
			System.out.println(e.getMessage());	
		}
		finally {
			DataBase.cerrarRs(rs);
			DataBase.cerrarPstm(pstm);
			DataBase.cerrarConexion(con);
		}
		return disponible;
	}



}


