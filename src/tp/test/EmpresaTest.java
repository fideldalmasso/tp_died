package tp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tp.dominio.Empresa;
import tp.dominio.Ruta;

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
		assertTrue(meli.flujoMaximo("p1","p5")==4D);
	}
	
	@Test
	public void testDijkstra() {
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
		
		List<Ruta> ans1 = new ArrayList<Ruta>();
		List<Ruta> ans2 = new ArrayList<Ruta>();
		
		ans1.add(r2);
		ans1.add(r5);
		ans2.add(r2);
		ans2.add(r7);
		ans2.add(r6);
		
		assertEquals(meli.caminoMinimo("p1","p5").get(0),ans1);
		assertEquals(meli.caminoMinimo("p1","p5").get(1),ans2);
	}
	

}
