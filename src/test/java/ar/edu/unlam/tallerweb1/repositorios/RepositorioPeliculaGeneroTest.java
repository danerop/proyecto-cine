package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

public class RepositorioPeliculaGeneroTest extends SpringTest {

	@Autowired
	RepositorioPeliculaGenero repoPeliculaGenero;
	
	@Test
	@Transactional @Rollback
	public void agregarGeneroAPelicula() {
		
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Toy Story");
		session().save(pelicula);
		
		Genero genero = new Genero();
		genero.setNombre("Accion");
		session().save(genero);

		Genero genero2 = new Genero();
		genero2.setNombre("Comedia");
		session().save(genero2);
		
		PeliculaGenero peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(pelicula);
		peliculaGenero.setGenero(genero);
		session().save(peliculaGenero);

		PeliculaGenero peliculaGenero2 = new PeliculaGenero();
		peliculaGenero2.setPelicula(pelicula);
		peliculaGenero2.setGenero(genero2);
		session().save(peliculaGenero2);
	
		assertNotNull(peliculaGenero.getId());
		assertNotNull(peliculaGenero2.getId());
		
		Integer actual = repoPeliculaGenero.obtenerTodosLosRegistros().size();
		Integer expected = 2;
		assertEquals(expected , actual);
	}
	

	@Test
	@Transactional @Rollback
	public void conseguirGenerosDeUnaPelicula() {
		//Registros de pel�cula
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Toy Story");
		session().save(pelicula);
		Pelicula pelicula2 = new Pelicula();
		pelicula2.setNombre("Batman");
		session().save(pelicula2);

		//Registros de g�nero
		Genero genero = new Genero();
		genero.setNombre("Accion");
		session().save(genero);
		Genero genero2 = new Genero();
		genero2.setNombre("Comedia");
		session().save(genero2);
		
		//Registros de Pel�culaG�nero
		PeliculaGenero peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(pelicula);
		peliculaGenero.setGenero(genero);
		session().save(peliculaGenero);
		PeliculaGenero peliculaGenero2 = new PeliculaGenero();
		peliculaGenero2.setPelicula(pelicula);
		peliculaGenero2.setGenero(genero2);
		session().save(peliculaGenero2);
		PeliculaGenero peliculaGenero3 = new PeliculaGenero();
		peliculaGenero3.setPelicula(pelicula2);
		peliculaGenero3.setGenero(genero);
		session().save(peliculaGenero3);

		//Validaci�n determinada pel�cula pertenece determinados g�neros
		String generoEsperado="Comedia";
		Boolean esDeEseGenero=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).contains(generoEsperado);
		assertTrue(esDeEseGenero); 
		String generoEsperado2="Accion";
		Boolean esDeEseGenero2=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).contains(generoEsperado2);
		assertTrue(esDeEseGenero2);
		
		//Validaci�n cantidad de registros de g�neros asociados a una pel�cula
		Integer cantidadDeGenerosAsociadosEsperada=2;
		Integer cantidadDeGenerosAsociadosReal=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).size();
		assertEquals(cantidadDeGenerosAsociadosEsperada , cantidadDeGenerosAsociadosReal);
		
	}
	

}
