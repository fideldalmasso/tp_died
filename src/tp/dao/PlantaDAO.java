package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;

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
					"INSERT INTO tp.Planta VALUES (default,?);");
			pstm.setString(1, p.getNombre());
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
			pstm.setInt(1, Integer.parseInt(id_planta[0]));
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
					"UPDATE tp.Planta SET nombre=? WHERE id_planta=?");
			pstm.setString(1, nuevo.getNombre());
			pstm.setInt(2, Integer.parseInt(original.getId_planta()));
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
			pstm.setInt(1, Integer.parseInt(id_planta[0]));
			rs = pstm.executeQuery();
			if(rs.next()) {
				//Planta marcaTemp = new Planta(rs.getString(2));
				return  Optional.of(new Planta(Integer.toString(rs.getInt(1)),rs.getString(2)));
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
				lista.add(new Planta(Integer.toString(rs.getInt(1)),rs.getString(2)));
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
	
	public List<Planta> getAll(String id_pedido){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Planta> lista = new ArrayList<Planta>();
		try {
			pstm = con.prepareStatement(
					"select * " + 
					"from tp.Planta pl " + 
					"where not exists ( " + 
					"select dp.id_insumo " + 
					"from tp.Detallepedido dp " + 
					"where dp.id_pedido=? " + 
					"except " + 
					"select dp.id_insumo " + 
					"from tp.stockinsumo si, tp.detallepedido dp " + 
					"where si.id_planta=pl.id_planta and dp.id_insumo=si.id_insumo and dp.cantidad_de_unidades<=si.stock " + 
					") and exists ( " + 
					"	(select ca.id_camion " + 
					"	from tp.camion ca " + 
					"	where ca.id_planta=pl.id_planta) " + 
					"	except " + 
					"	(select ca.id_camion " + 
					"	from tp.camion ca, tp.pedido ped, tp.envio env" + 
					"	where ca.id_camion=env.id_camion and env.id_envio=ped.id_envio and ped.estado_pedido='PROCESADA') " + 
					") ");
			pstm.setInt(1, Integer.parseInt(id_pedido));
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


