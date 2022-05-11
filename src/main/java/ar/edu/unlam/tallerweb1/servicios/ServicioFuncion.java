package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface ServicioFuncion {
	
	List<Funcion> obtenerTodasLasFunciones();
	Funcion buscarFuncion(Long id);
	void guardarFuncion(Funcion funcion);
	
}
