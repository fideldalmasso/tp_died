package tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tp.dominio.*;
import tp.enumerados.Unidad;

public class InsumoGeneralDAO implements Registrable<InsumoGeneral>{
	
	public InsumoGeneralDAO() {
		super();
	}
	
	@Override
	public Boolean add(InsumoGeneral en) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.InsumoGeneral VALUES (?,?);");
			pstm.setString(1, en.getId_insumo());
			pstm.setDouble(2, en.getPesoPorUnidad());
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
	public Boolean delete(String ...id_InsumoGeneral) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.InsumoGeneral WHERE id_InsumoGeneral=?;");
			pstm.setString(1, id_InsumoGeneral[0]);
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
	public Boolean update(InsumoGeneral original, InsumoGeneral nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.InsumoGeneral SET id_InsumoGeneral=?, descripcion=?, unidad_de_medida=?, costo_por_unidad=? WHERE id_InsumoGeneral=?");
			pstm.setString(1, nuevo.getId_insumo());
			pstm.setDouble(2, nuevo.getPesoPorUnidad());
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
	public Optional<InsumoGeneral> get(String ...id_InsumoGeneral) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<InsumoGeneral> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.InsumoGeneral WHERE id_InsumoGeneral=?;");
			pstm.setString(1, id_InsumoGeneral[0]);
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
	public List<InsumoGeneral> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InsumoGeneral> lista = new ArrayList<InsumoGeneral>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.InsumoGeneral;");
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
	
	InsumoGeneral parsearRS(ResultSet rs) throws SQLException {
		InsumoDAO insumodaoTemp = new InsumoDAO();
		Insumo insumoTemp = insumodaoTemp.get(rs.getString(1)).get();
		
		return new InsumoGeneral(insumoTemp.getId_insumo(),
						insumoTemp.getDescripcion(),
						insumoTemp.getUnidad_de_medida(),
						insumoTemp.getCosto_por_unidad(),
						rs.getDouble(2));
	}
	
}


