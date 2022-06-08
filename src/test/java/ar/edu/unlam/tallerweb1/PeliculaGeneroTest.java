package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGeneroImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGeneroImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcionImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;

public class PeliculaGeneroTest extends SpringTest {

	@Inject
	private SessionFactory sessionFactory;

	RepositorioUsuario repoUsuario = new RepositorioUsuarioImpl();
	RepositorioSuscripcion repoSuscripcion = new RepositorioSuscripcionImpl();
	RepositorioPeliculaGenero repoPeliculaGenero = new RepositorioPeliculaGeneroImpl();
	RepositorioPelicula repoPelicula = new RepositorioPeliculaImpl();
	RepositorioGenero repoGenero = new RepositorioGeneroImpl();
	
	private List<Long> cargarPeliculas() {
		List<Long> peliculasId = new ArrayList<Long>();
		Pelicula pelicula = new Pelicula();
		pelicula.setNombre("Toy Story");
		peliculasId.add((Long) sessionFactory.getCurrentSession().save(pelicula));
		
		pelicula = new Pelicula();
		pelicula.setNombre("Batman");
		peliculasId.add((Long) sessionFactory.getCurrentSession().save(pelicula));
		
		pelicula = new Pelicula();
		pelicula.setNombre("Spiderman");
		peliculasId.add((Long) sessionFactory.getCurrentSession().save(pelicula));
		
		return peliculasId;
	}
	
	private List<Long> cargarGeneros() {
		List<Long>generosID = new ArrayList<Long>();
		Genero genero = new Genero();
		genero.setNombre("Accion");
		generosID.add((Long) sessionFactory.getCurrentSession().save(genero));
		
		genero = new Genero();
		genero.setNombre("Comedia");
		generosID.add((Long) sessionFactory.getCurrentSession().save(genero));
		
		genero = new Genero();
		genero.setNombre("Terror");
		generosID.add((Long) sessionFactory.getCurrentSession().save(genero));
		
		return generosID;
	}
	
	private void asociarGenerosALasPeliculas(List<Long>peliculasId , List<Long>generosID) {
		PeliculaGenero peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(sessionFactory.getCurrentSession().get(Pelicula.class, peliculasId.get(0)));
		peliculaGenero.setGenero(sessionFactory.getCurrentSession().get(Genero.class, generosID.get(0)));
		sessionFactory.getCurrentSession().save(peliculaGenero);
		
		peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(sessionFactory.getCurrentSession().get(Pelicula.class, peliculasId.get(0)));
		peliculaGenero.setGenero(sessionFactory.getCurrentSession().get(Genero.class, generosID.get(1)));
		sessionFactory.getCurrentSession().save(peliculaGenero);
		
		peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(sessionFactory.getCurrentSession().get(Pelicula.class, peliculasId.get(1)));
		peliculaGenero.setGenero(sessionFactory.getCurrentSession().get(Genero.class, generosID.get(0)));	
		sessionFactory.getCurrentSession().save(peliculaGenero);
		
		peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(sessionFactory.getCurrentSession().get(Pelicula.class, peliculasId.get(1)));
		peliculaGenero.setGenero(sessionFactory.getCurrentSession().get(Genero.class, generosID.get(1)));	
		sessionFactory.getCurrentSession().save(peliculaGenero);
		
		peliculaGenero = new PeliculaGenero();
		peliculaGenero.setPelicula(sessionFactory.getCurrentSession().get(Pelicula.class, peliculasId.get(1)));
		peliculaGenero.setGenero(sessionFactory.getCurrentSession().get(Genero.class, generosID.get(2)));	
		sessionFactory.getCurrentSession().save(peliculaGenero);
		
	}
	
	private List<String>obtenerGenerosDeUnaPelicula(Pelicula pelicula){
		return sessionFactory.getCurrentSession().createCriteria(PeliculaGenero.class)
                                                 .add(Restrictions.eq("pelicula", pelicula))
                                                 .createAlias("genero", "tablaGenero")
                                                 .setProjection(Projections.property("tablaGenero.nombre"))
                                                 .list();
	}
	
	@Test
	@Transactional @Rollback
	public void agregarGenerosALasPeliculas() {
		
		List<Long>generos  = cargarGeneros();
		List<Long>peliculas = cargarPeliculas();
		asociarGenerosALasPeliculas(peliculas, generos);
		
		//El resultado me tiene que dar 3 registros
		Integer actual = sessionFactory.getCurrentSession().createCriteria(PeliculaGenero.class).list().size();
		Integer expected = 5;
		
		assertEquals(expected , actual);
	}
	
	@Test
	@Transactional @Rollback
	public void conseguirGenerosDeUnaPelicula() {
		List<Long>generos  = cargarGeneros();
		List<Long>peliculas = cargarPeliculas();
		asociarGenerosALasPeliculas(peliculas, generos);
		Pelicula pelicula1 = sessionFactory.getCurrentSession().get(Pelicula.class, peliculas.get(0));
		Pelicula pelicula2 = sessionFactory.getCurrentSession().get(Pelicula.class, peliculas.get(1));
		
		List<String>generosDePelicula2 = obtenerGenerosDeUnaPelicula(pelicula2);
		
		
		assertEquals("Accion" , generosDePelicula2.get(0));
		assertEquals(3 , generosDePelicula2.size());
		
	}

}
