package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

public interface ServicioGenero {

	void guardarGenero(Genero genero);
	void guardarListaDeGeneros(List<Long> idGeneros);
	List<Genero> obtenerTodosLosGeneros();
	List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula);
	Genero obtenerGeneroPorid(Long id);
	void guardarGeneroPelicula(PeliculaGenero registro);
	PeliculaGenero obtenerRegistroPorPeliculaYGenero(Long idPelicula, Long idGenero);
	List<PeliculaGenero> obtenerTodosLosRegistrosGeneroPelicula();
	void inactivar(Long idRegistro);
	
}
