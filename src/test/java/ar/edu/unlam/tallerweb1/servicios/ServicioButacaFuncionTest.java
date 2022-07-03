package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioButacaFuncion;

public class ServicioButacaFuncionTest {
	private RepositorioButacaFuncion repoButacaFuncion=mock(RepositorioButacaFuncion.class);
	private ServicioButacaFuncion servicioButacaFuncion= new ServicioButacaFuncionImpl(repoButacaFuncion);

	@Test
	@Transactional @Rollback
	public void buscarRegistrosPorFuncion() {
		Funcion funcion=new Funcion();
		
		servicioButacaFuncion.obtenerButacasPorFuncion(funcion);
		
		verify(repoButacaFuncion, times(1)).obtenerButacasPorFuncion(funcion);
	}
	@Test
	@Transactional @Rollback
	public void buscarRegistroPorButacaYFuncion() {
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		
		servicioButacaFuncion.obtenerPorButacaYFuncion(funcion, butaca.getId());
		
		verify(repoButacaFuncion, times(1)).obtenerPorButacaYFuncion(funcion, butaca.getId());
	}
	@Test
	@Transactional @Rollback
	public void ButacaNoEstaOcupada() {
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		butacaFuncion.setOcupada(false);
		
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(funcion, butaca.getId())).thenReturn(butacaFuncion);
		Boolean temp=servicioButacaFuncion.isButacaOcupada(funcion, butaca.getId());
		
		assertEquals(false, temp);
		
	}
	@Test
	@Transactional @Rollback
	public void ButacaEstaOcupada() {
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		butacaFuncion.setOcupada(true);
		
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(funcion, butaca.getId())).thenReturn(butacaFuncion);
		Boolean temp=servicioButacaFuncion.isButacaOcupada(funcion, butaca.getId());
		
		assertEquals(true, temp);
		
	}
}
