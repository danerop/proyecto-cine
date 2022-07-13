package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

public interface RepositorioPeliculaGenero {

	void insertarPeliculaGenero(PeliculaGenero peliculaGenero);
	List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula);
	List<PeliculaGenero> obtenerTodosLosRegistros();
	List<Genero> obtenerGenerosDePeliculasCompradas(List<Pelicula> listaPeliculas);
	List<Pelicula> obtenerPeliculasSegunGeneros(List<Genero> listaGeneros);
	PeliculaGenero obtenerRegistroPorPeliculaYGenero(Long idPelicula, Long idGenero);
	PeliculaGenero buscarRegistroPorId(Long id);
	List<Pelicula> obtenerPeliculasSegunGenerosDistintasDeLista(List<Genero> generos, List<Pelicula> peliculas);

}
