package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Cine;

public interface ServicioCine {
	Cine buscarCinePorID(Long id);
	void guardarCine(Cine cine);
}
