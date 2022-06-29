package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface ServicioPeliculaGenero {

	List<Pelicula> obtenerPeliculasRecomendadas(List<Genero> listaGeneros, List<Pelicula> listaPeliculasCompradas);
	
}
