package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPeliculaGenero {
	
	List<Pelicula> obtenerPeliculasRecomendadasPorGenerosFavoritos(Usuario user);
	List<Pelicula> obtenerPeliculasRecomendadasPorBoletosComprados(Usuario user);
	
}
