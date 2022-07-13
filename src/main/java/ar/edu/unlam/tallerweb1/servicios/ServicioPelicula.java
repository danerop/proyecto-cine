package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface ServicioPelicula {

	List<Pelicula> obtenerTodosLasPeliculas();
	Pelicula buscarPeliculaPorID(Long id);
	void validarPelicula(Pelicula pelicula);
	void guardarPelicula(Pelicula pelicula);
	void actualizarPelicula(Pelicula pelicula);
	List<Pelicula> buscarPeliculasPorNombre(String busqueda);
	
}
