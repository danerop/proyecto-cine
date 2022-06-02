package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

public interface ServicioButacaFuncion {
	void asociarButacasAFuncion(Funcion funcion, List<Butaca> butacas);
	List<ButacaFuncion> obtenerButacasPorFuncion(Funcion funcion);
	ButacaFuncion obtenerPorButacaYFuncion(Funcion funcion, Long idButaca);
	void actualizarButacaFuncion(ButacaFuncion butacaFuncion);
}
