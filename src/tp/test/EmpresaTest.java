package tp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import tp.dominio.Empresa;
import tp.dominio.Ruta;
import tp.dominio.Planta;

public class EmpresaTest {
	
	@Test
	public void testFlujoMaximo() {
		Empresa meli = new Empresa("meli");
		meli.agregarPlanta("p1");
		meli.agregarPlanta("p2");
		meli.agregarPlanta("p3");
		meli.agregarPlanta("p4");
		meli.agregarPlanta("p5");
		meli.agregarRuta("r1", 1000D, 2D, 4D,"p1", "p2");
		meli.agregarRuta("r2", 1000D, 2D, 1D,"p1", "p3");
		meli.agregarRuta("r3", 1000D, 2D, 1D,"p1", "p4");
		meli.agregarRuta("r4", 1000D, 2D, 2D,"p2", "p5");
		meli.agregarRuta("r5", 1000D, 2D, 2D,"p3", "p5");
		meli.agregarRuta("r6", 1000D, 2D, 1D,"p4", "p5");
		meli.agregarRuta("r7", 2000D, 2D, 4D,"p5", "p1");
		Double d= meli.flujoMaximo("p1","p5");
		Double d1 = 4D;
		assertEquals(d1,d);
	}
	
	@Test
	public void testCaminosMinimos() {
		Empresa meli = new Empresa("meli");
		meli.agregarPlanta("p1");
		meli.agregarPlanta("p2");
		meli.agregarPlanta("p3");
		meli.agregarPlanta("p4");
		meli.agregarPlanta("p5");
		meli.agregarRuta("r1", 1001D, 2D, 4D,"p1", "p2");
		meli.agregarRuta("r2", 504D, 1D, 1D,"p1", "p3");
		meli.agregarRuta("r3", 1006D, 3D, 1D,"p1", "p4");
		meli.agregarRuta("r4", 507D, 2D, 2D,"p2", "p5");
		meli.agregarRuta("r7", 508D, 1D, 4D,"p3", "p4");
		meli.agregarRuta("r5", 509D, 3D, 2D,"p3", "p5");
		meli.agregarRuta("r6", 1002D, 1D, 1D,"p4", "p5");
		
		Ruta r2 = new Ruta("r2",meli.getPlanta("p1"),meli.getPlanta("p3"),504D, 1D, 1D);
		Ruta r5 = new Ruta("r5",meli.getPlanta("p3"),meli.getPlanta("p5"),509D, 3D, 2D);
		Ruta r6 = new Ruta("r6",meli.getPlanta("p4"),meli.getPlanta("p5"),1002D, 1D, 1D);
		Ruta r7 = new Ruta("r7",meli.getPlanta("p3"),meli.getPlanta("p4"),508D, 1D, 4D);
		
		List<List<Ruta>> ans1 = new ArrayList<List<Ruta>>();
		List<List<Ruta>> ans2 = new ArrayList<List<Ruta>>();
		List<Ruta> ans3 = new ArrayList<Ruta>();
		List<Ruta> ans4 = new ArrayList<Ruta>();
		ans3.add(r2);
		ans3.add(r5);
		ans4.add(r2);
		ans4.add(r7);
		ans4.add(r6);
		ans1.add(ans3);
		ans2.add(ans4);
		
		assertEquals(meli.caminosMinimos("p1","p5",0),ans1);
		assertEquals(meli.caminosMinimos("p1","p5",1),ans2);
	}
	
	@Test
	public void testFloyd(){
		Boolean sol =true;
		Empresa meli = new Empresa("meli");
		meli.agregarPlanta("p1");
		meli.agregarPlanta("p2");
		meli.agregarPlanta("p3");
		meli.agregarPlanta("p4");
		meli.agregarPlanta("p5");
		meli.agregarRuta("r1", 1001D, 2D, 4D,"p1", "p2");
		meli.agregarRuta("r2", 504D, 1D, 1D,"p1", "p3");
		meli.agregarRuta("r3", 1006D, 3D, 1D,"p1", "p4");
		meli.agregarRuta("r4", 507D, 2D, 2D,"p2", "p5");
		meli.agregarRuta("r7", 508D, 1D, 4D,"p3", "p4");
		meli.agregarRuta("r5", 509D, 3D, 2D,"p3", "p5");
		meli.agregarRuta("r6", 1002D, 1D, 1D,"p4", "p5");
		Map<Integer,Planta> m = new HashMap<Integer,Planta>();
		Double[][] ans = meli.matrizCaminoMinimo(0,m);
		Double[][] ansCorrecta= {{1.7976931348623157E308D,1001.0D,504.0D,1006.0D,1013.0D},
				{1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,507.0D},
				{1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,508.0D,509.0D},
				{1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,1002.0D},
				{1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D,1.7976931348623157E308D}};
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(ans[i][j]==ansCorrecta[i][j]) {
					sol=false;
				}
			}
		}
		
		assertTrue(sol);
	}
	

}
