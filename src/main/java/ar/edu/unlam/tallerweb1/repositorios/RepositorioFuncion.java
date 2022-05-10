package ar.edu.unlam.tallerweb1.repositorios;

import java.sql.Date;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface RepositorioFuncion {

	Funcion buscarFuncion(Long idcine, String fecha, Integer hora, Pelicula pelicula);
	void guardar(Funcion funcion);
	Funcion buscar(Long idfuncion);
	

}
