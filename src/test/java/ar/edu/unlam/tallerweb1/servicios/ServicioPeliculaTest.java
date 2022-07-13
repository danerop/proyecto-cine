package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

public class ServicioPeliculaTest {
	private RepositorioPelicula repositorioPelicula = mock(RepositorioPelicula.class);
	private ServicioPelicula servicioPelicula = new ServicioPeliculaImpl(repositorioPelicula);

	@Test
	@Transactional @Rollback
	public void buscarPeliculaPorId() {
		Pelicula peli=new Pelicula();
		
		when(repositorioPelicula.obtenerPeliculaPorID(peli.getId())).thenReturn(peli);
		servicioPelicula.buscarPeliculaPorID(peli.getId());
		
		verify(repositorioPelicula, times(1)).obtenerPeliculaPorID(peli.getId());
	}

}
