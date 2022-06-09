package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGenero;


public class PeliculaGeneroTest extends SpringTest {


	
	@Autowired
	RepositorioPeliculaGenero repoPeliculaGenero;
	@Autowired
	RepositorioPelicula repoPelicula;
	@Autowired
	RepositorioGenero repoGenero;
	
	
//	private List<Pelicula> cargarPeliculas() {
//		List<Pelicula> peliculas = new ArrayList<Pelicula>();
//		Pelicula pelicula = new Pelicula();
//		pelicula.setNombre("Toy Story");
//		repoPelicula.guardarPelicula(pelicula);
//		peliculas.add(pelicula);
//		
//		pelicula = new Pelicula();
//		pelicula.setNombre("Batman");
//		repoPelicula.guardarPelicula(pelicula);
//		peliculas.add(pelicula);
//		
//		
//		pelicula = new Pelicula();
//		pelicula.setNombre("Spiderman");
//		repoPelicula.guardarPelicula(pelicula);
//		peliculas.add(pelicula);
//		
//		
//		
//		return peliculas;
//		
//
//	}
//	
//
//	private List<Genero> cargarGeneros() {
//		List<Genero>generosID = new ArrayList<Genero>();
//		Genero genero = new Genero();
//		genero.setNombre("Accion");
//		session().save(genero);
//		generosID.add(genero);
//		
//		genero = new Genero();
//		genero.setNombre("Comedia");
//		session().save(genero);
//		generosID.add(genero);
//		
//		genero = new Genero();
//		genero.setNombre("Terror");
//		session().save(genero);
//		generosID.add(genero);
//		
//		return generosID;
//	}
//	
//
//	private List<PeliculaGenero> asociarGenerosALasPeliculas(List<Pelicula>peliculas , List<Genero>generos) {
//		List<PeliculaGenero>peliculageneros = new ArrayList<PeliculaGenero>();
//		PeliculaGenero peliculaGenero = new PeliculaGenero();
//		peliculaGenero.setPelicula(peliculas.get(0));
//		peliculaGenero.setGenero(generos.get(0));
//		session().save(peliculaGenero);
//		peliculageneros.add(peliculaGenero);
//		
//		peliculaGenero = new PeliculaGenero();
//		peliculaGenero.setPelicula(peliculas.get(0));
//		peliculaGenero.setGenero(generos.get(1));
//		session().save(peliculaGenero);
//		peliculageneros.add(peliculaGenero);
//		
//		peliculaGenero = new PeliculaGenero();
//		peliculaGenero.setPelicula(peliculas.get(1));
//		peliculaGenero.setGenero(generos.get(0));	
//		session().save(peliculaGenero);
//		peliculageneros.add(peliculaGenero);
//		
//		peliculaGenero = new PeliculaGenero();
//		peliculaGenero.setPelicula(peliculas.get(1));
//		peliculaGenero.setGenero(generos.get(1));	
//		session().save(peliculaGenero);
//		peliculageneros.add(peliculaGenero);
//		
//		peliculaGenero = new PeliculaGenero();
//		peliculaGenero.setPelicula(peliculas.get(1));
//		peliculaGenero.setGenero(generos.get(2));	
//		session().save(peliculaGenero);
//		peliculageneros.add(peliculaGenero);
//		
//		return peliculageneros;
//		
//	}
	

	@Test
	@Transactional @Rollback
	public void agregarGeneroAPelicula() {
		
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Toy Story");
		session().save(pelicula);
//		repoPelicula.guardarPelicula(pelicula);
		
		Genero genero = new Genero();
		genero.setNombre("Accion");
		session().save(genero);
//		repoGenero.insertarGenero(genero);
		Genero genero2 = new Genero();
		genero2.setNombre("Comedia");
		session().save(genero2);
//		repoGenero.insertarGenero(genero2);
		
		PeliculaGenero peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(pelicula);
		peliculaGenero.setGenero(genero);
		session().save(peliculaGenero);
//		repoPeliculaGenero.insertarPeliculaGenero(peliculaGenero);
		PeliculaGenero peliculaGenero2 = new PeliculaGenero();
		peliculaGenero2.setPelicula(pelicula);
		peliculaGenero2.setGenero(genero2);
		session().save(peliculaGenero2);
//		repoPeliculaGenero.insertarPeliculaGenero(peliculaGenero2);
	
		assertNotNull(peliculaGenero.getId());
		assertNotNull(peliculaGenero2.getId());
		
		Integer actual = repoPeliculaGenero.obtenerTodosLosRegistros().size();
		Integer expected = 2;
		assertEquals(expected , actual);
	}
	

	@Test
	@Transactional @Rollback
	public void conseguirGenerosDeUnaPelicula() {
		//Registros de película
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Toy Story");
		session().save(pelicula);
		Pelicula pelicula2 = new Pelicula();
		pelicula2.setNombre("Batman");
		session().save(pelicula2);

		//Registros de género
		Genero genero = new Genero();
		genero.setNombre("Accion");
		session().save(genero);
		Genero genero2 = new Genero();
		genero2.setNombre("Comedia");
		session().save(genero2);
		
		//Registros de PelículaGénero
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

		//Validación determinada película pertenece determinados géneros
		String generoEsperado="Comedia";
		Boolean esDeEseGenero=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).contains(generoEsperado);
		assertTrue(esDeEseGenero); 
		String generoEsperado2="Accion";
		Boolean esDeEseGenero2=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).contains(generoEsperado2);
		assertTrue(esDeEseGenero2);
		
		//Validación cantidad de registros de géneros asociados a una película
		Integer cantidadDeGenerosAsociadosEsperada=2;
		Integer cantidadDeGenerosAsociadosReal=repoPeliculaGenero.obtenerGenerosDeUnaPelicula(pelicula).size();
		assertEquals(cantidadDeGenerosAsociadosEsperada , cantidadDeGenerosAsociadosReal);
		
	}
	

}
