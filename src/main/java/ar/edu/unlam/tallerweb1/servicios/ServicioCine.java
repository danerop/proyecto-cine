package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;

public interface ServicioCine {
	List<Cine> obtenerTodosLosCines();
	Cine buscarCinePorID(Long id);
	void guardarCine(Cine cine) throws ExceptionCineCamposVacios;
}
