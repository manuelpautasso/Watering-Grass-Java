package mp;

import static org.junit.jupiter.api.Assertions.*;
import static mp.Main.Aspersor;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testAreaEfectivaIgualA0DeAspersorDeRadio0() {
		int l = 20;
		int w = 5;
		
		int radioAspersor = 0;
		int posicionAspersor = 5;
		
		Aspersor aspersor = new Aspersor(posicionAspersor, radioAspersor, l, w);
		
		assertEquals(0, aspersor.getAreaEfectivaIzquierda());
		assertEquals(0, aspersor.getAreaEfectivaDerecha());
	}
	
	@Test
	void testAreaEfectivaDerechaDeAspersorIgualALargoDelCampoCuandoElAreaEfectivaExcedeElCampoPorDerecha() {
		int l = 20;
		int w = 2;
		
		int radioAspersor = 10;
		int posicionAspersor = 17;
		
		Aspersor aspersor = new Aspersor(posicionAspersor, radioAspersor, l, w);		
		
		assertEquals(l, aspersor.getAreaEfectivaDerecha());
	}

	
	@Test
	void testAreaEfectivaIzquierdaDeAspersorIgualA0CuandoElAreaEfectivaExcedeElCampoPorizquierda() {
		int l = 20;
		int w = 2;
		
		int radioAspersor = 10;
		int posicionAspersor = 2;
		
		Aspersor aspersor = new Aspersor(posicionAspersor, radioAspersor, l, w);		
		
		assertEquals(0, aspersor.getAreaEfectivaIzquierda());
	}
	
	@Test
	void testAreaEfectivaBienCalculadaParaAspersorValidoLejosDeLosBordesDelCampo() {
		int l = 20;
		int w = 3;
		
		int radioAspersor = 5;
		int posicionAspersor = 10;
		
		Aspersor aspersor = new Aspersor(posicionAspersor, radioAspersor, l, w);
		
		double areaEfectivaIzquierdaEsperada = 6;
		double areaEfectivaDerechaEsperada = 14;
		
		double areaEfectivaIzquierdaCalculada = aspersor.getAreaEfectivaIzquierda();
		double areaEfectivaDerechaCalculada = aspersor.getAreaEfectivaDerecha();
		
		assertEquals(areaEfectivaIzquierdaEsperada, areaEfectivaIzquierdaCalculada);
		assertEquals(areaEfectivaDerechaEsperada, areaEfectivaDerechaCalculada);
	}
	
	
}
