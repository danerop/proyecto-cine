package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface ServicioPelicula {

	List<Pelicula> obtenerTodosLasPeliculas();
	Pelicula buscarPeliculaPorID(Long id);
	void guardarPelicula(Pelicula pelicula);
	
}
