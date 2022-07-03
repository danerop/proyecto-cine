package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Butaca;

public interface RepositorioButaca {
	List<Butaca> obtenerButacasPorSala(Long idSala);
	void guardarButaca(Butaca butaca);
	Butaca obtenerButaca(Long idButaca);
}
