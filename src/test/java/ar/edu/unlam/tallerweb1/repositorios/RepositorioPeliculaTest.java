package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public class RepositorioPeliculaTest extends SpringTest{

	@Autowired
	RepositorioPelicula repopeli;
	
	@Test
	@Transactional @Rollback
	public void sePuedaBuscarPeliculaPorId() {		
		Pelicula peli1 = new Pelicula();
		Pelicula peli2 = new Pelicula();
		Pelicula peli3 = new Pelicula();
		peli2.setNombre("Batman");
		session().save(peli1);
		session().save(peli2);
		session().save(peli3);
		
		Pelicula peliesperada = repopeli.obtenerPeliculaPorID(peli2.getId());
		
		assertNotNull(peliesperada);
		assertEquals(peli2, peliesperada);
		assertEquals(peli2.getId(), peliesperada.getId());
	}
}
