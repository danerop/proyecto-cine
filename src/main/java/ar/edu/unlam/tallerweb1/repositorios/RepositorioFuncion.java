package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface RepositorioFuncion {
	
	List<Funcion> obtenerTodasLasFunciones();
	Funcion buscarFuncionPorId(Long id);
	void guardarFuncion(Funcion funcion);
	
}
