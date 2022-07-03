package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface RepositorioButacaFuncion {
	List<ButacaFuncion> obtenerButacasPorFuncion(Funcion funcion);
	void guardarButacaFuncion(ButacaFuncion butacaFuncion);
	ButacaFuncion obtenerPorButacaYFuncion(Funcion funcion, Long idButaca);
	void actualizarButacaFuncion(ButacaFuncion butacaFuncion);
	
}
