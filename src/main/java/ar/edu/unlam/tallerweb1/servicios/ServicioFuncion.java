package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface ServicioFuncion {
	
	List<Funcion> obtenerTodasLasFunciones();
	Funcion buscarFuncion(Long id);
	void validarFuncion(Funcion funcion);
	void guardarFuncion(Funcion funcion);
	List<Funcion> obtenerFuncionesPorCine(Long idCine);
	List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula);
	List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula);
	Funcion obtenerFuncionesPorCineFechaHoraSalaYPelicula(Long idCine, Long idPelicula, String fechaHora, String hora,Long idSala);
	
	List<Funcion> obtenerFuncionesFuturasDePelicula(Long idPelicula);
	List<Cine> obtenerCinesDisponiblesParaFuncionesFuturas(Long idPelicula);
	void actualizarFuncion(Funcion nuevaFuncion);
	
}
