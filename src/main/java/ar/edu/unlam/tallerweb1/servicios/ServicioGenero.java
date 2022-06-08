package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface ServicioGenero {

	void guardarGenero(Genero genero);

	List<Genero> obtenerTodosLosGeneros();
	
	List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula);
	
}
