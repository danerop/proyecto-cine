package ar.edu.unlam.tallerweb1.repositorios;


import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface RepositorioFuncion {
	
	List<Funcion> obtenerTodasLasFunciones();
	Funcion buscarFuncionPorId(Long id);
	void guardarFuncion(Funcion funcion);
	List<Funcion> obtenerFuncionesPorCineYPelicula(Long idCine, Long idPelicula);
	List<Funcion> obtenerFuncionesPorCine(Long idCine);
	List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula);
	List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula);
	List<Funcion> obtenerFuncionesPorCineFechaHoraYPelicula(Long idCine, Long idPelicula, Date fechaHora, String hora);
	
	

}
