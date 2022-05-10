package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Cine;

public interface RepositorioCine {

	List<Cine> obtenerTodosLosCines();
	Cine buscarCinePorId(Long id);
	void guardarCine(Cine cine);
	
}
