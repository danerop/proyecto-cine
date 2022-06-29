package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

public interface RepositorioPeliculaGenero {

	void insertarPeliculaGenero(PeliculaGenero peliculaGenero);
	List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula);
	
	SessionFactory getSessionFactory();
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	List<PeliculaGenero> obtenerTodosLosRegistros();
	List<Genero> obtenerGenerosDePeliculasCompradas(List<Pelicula> listaPeliculas);
	
	List<Pelicula> obtenerPeliculasRecomendadasSegunGeneroFavorito(List<Genero> listaGeneros);
	
}
