package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Butaca;

public interface ServicioButaca {
	List<Butaca> obtenerButacasPorSala(Long idSala);
}
