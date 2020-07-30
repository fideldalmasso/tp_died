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

public class InsumoDAO implements Registrable<Insumo>{
	
	public InsumoDAO() {
		super();
	}
	
	@Override
	public Boolean add(Insumo en) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.Insumo VALUES (default,?,CAST(? AS tp.unidaddemedida),?);");
			pstm.setString(1, en.getDescripcion());
			pstm.setString(2, en.getUnidad_de_medida().toString());
			pstm.setDouble(3, en.getCosto_por_unidad());

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
	public Boolean delete(String ...id_Insumo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.Insumo WHERE id_Insumo=?;");
			pstm.setInt(1, Integer.parseInt(id_Insumo[0]));
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
	public Boolean update(Insumo original, Insumo nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(

					"UPDATE tp.insumo SET descripcion=?, unidad_de_medida= (CAST(? AS tp.unidaddemedida)), costo_por_unidad=? WHERE id_insumo=?");
			pstm.setString(1, nuevo.getDescripcion());
			pstm.setString(2, nuevo.getUnidad_de_medida().toString());
			pstm.setDouble(3, nuevo.getCosto_por_unidad());
			pstm.setInt(4, Integer.parseInt(original.getId_insumo()));

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
	public Optional<Insumo> get(String ...id_Insumo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<Insumo> m = Optional.empty();
		try {
			pstm = con.prepareStatement(

					"SELECT * FROM tp.Insumo WHERE id_Insumo=?;");
			pstm.setInt(1, Integer.parseInt(id_Insumo[0]));

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
	public List<Insumo> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Insumo> lista = new ArrayList<Insumo>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.Insumo;");
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
	
	Insumo parsearRS(ResultSet rs) throws SQLException {
		
		return new Insumo(Integer.toString(rs.getInt(1)),
						rs.getString(2),
						Unidad.valueOf(rs.getString(3)),
						rs.getDouble(4));
	}
	
}


