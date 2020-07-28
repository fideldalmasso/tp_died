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

public class InsumoLiquidoDAO implements Registrable<InsumoLiquido>{
	
	public InsumoLiquidoDAO() {
		super();
	}
	
	@Override
	public Boolean add(InsumoLiquido en) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"INSERT INTO tp.InsumoLiquido VALUES (?,?);");
			pstm.setString(1, en.getId_insumo());
			pstm.setDouble(2, en.getDensidad());
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
	public Boolean delete(String ...id_InsumoLiquido) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"DELETE FROM tp.InsumoLiquido WHERE id_InsumoLiquido=?;");
			pstm.setString(1, id_InsumoLiquido[0]);
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
	public Boolean update(InsumoLiquido original, InsumoLiquido nuevo) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"UPDATE tp.InsumoLiquido SET id_InsumoLiquido=?, descripcion=?, unidad_de_medida=?, costo_por_unidad=? WHERE id_InsumoLiquido=?");
			pstm.setString(1, nuevo.getId_insumo());
			pstm.setDouble(2, nuevo.getDensidad());
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
	public Optional<InsumoLiquido> get(String ...id_InsumoLiquido) {
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Optional<InsumoLiquido> m = Optional.empty();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.InsumoLiquido WHERE id_InsumoLiquido=?;");
			pstm.setString(1, id_InsumoLiquido[0]);
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
	public List<InsumoLiquido> getAll(){
		Connection con = DataBase.getConexion();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InsumoLiquido> lista = new ArrayList<InsumoLiquido>();
		try {
			pstm = con.prepareStatement(
					"SELECT * FROM tp.InsumoLiquido;");
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
	
	InsumoLiquido parsearRS(ResultSet rs) throws SQLException {
		InsumoDAO insumodaoTemp = new InsumoDAO();
		Insumo insumoTemp = insumodaoTemp.get(rs.getString(1)).get();
		
		return new InsumoLiquido(insumoTemp.getId_insumo(),
						insumoTemp.getDescripcion(),
						insumoTemp.getUnidad_de_medida(),
						insumoTemp.getCosto_por_unidad(),
						rs.getDouble(2));
	}
	
}


