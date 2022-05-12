package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface RepositorioPelicula {

	void guardarPelicula(Pelicula pelicula);
	
    Pelicula obtenerPeliculaPorID(Long id);
    
	List<Pelicula> obtenerTodasLasPeliculas();
	
	void setSessionFactory(SessionFactory sessionFactory);

	SessionFactory getSessionFactory();
}
