package test;

import static org.junit.Assert.*;

import org.junit.Test;

import entidades_dominio.Empresa;

public class EmpresaTest {
	
	@Test
	public void testFlujoMaximo() {
		Empresa meli = new Empresa("meli");
		meli.agregarPlanta("p1");
		meli.agregarPlanta("p2");
		meli.agregarPlanta("p3");
		meli.agregarPlanta("p4");
		meli.agregarPlanta("p5");
		meli.agregarRuta("r1", 1000D, 2D, 4D, 0D, "p1", "p2");
		meli.agregarRuta("r2", 1000D, 2D, 1D, 0D, "p1", "p3");
		meli.agregarRuta("r3", 1000D, 2D, 1D, 0D, "p1", "p4");
		meli.agregarRuta("r4", 1000D, 2D, 2D, 0D, "p2", "p5");
		meli.agregarRuta("r5", 1000D, 2D, 2D, 0D, "p3", "p5");
		meli.agregarRuta("r6", 1000D, 2D, 1D, 0D, "p4", "p5");
		meli.agregarRuta("r7", 2000D, 2D, 4D, 0D, "p5", "p1");
		assertTrue(meli.flujoMaximo("p1","p5")==4D);
	}

}
