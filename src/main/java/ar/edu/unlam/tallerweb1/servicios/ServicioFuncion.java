package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

public interface ServicioFuncion {
	
	List<Funcion> obtenerTodasLasFunciones();
	Funcion buscarFuncion(Long id);
	void guardarFuncion(Funcion funcion);
	List<Funcion> obtenerFuncionesPorPeliculaYCine(Long idCine, Long idCinePelicula);
	List<Funcion> obtenerFuncionesPorCine(Long idCine);
	List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula);
	List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula);
	List<Funcion> obtenerFuncionesPorCineFechaHoraYPelicula(Long idCine, Long idPelicula, Date fechaHora, String hora);
	Funcion obtenerFuncionesPorCineFechaHoraPeliculaYSala(Long idCine, Long idPelicula, Date fechaHora, String hora, Long salaid);
	
}
