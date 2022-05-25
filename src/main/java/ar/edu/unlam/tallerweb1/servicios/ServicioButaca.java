package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.Sala;

public interface ServicioButaca {
	List<Butaca> obtenerButacasPorSala(Long idSala);
	void guardarButaca(Butaca butaca);
	void guardarButacas(List<Integer> ubicaciones, Sala sala);
	Butaca obtenerButaca(Long idSala);
	Integer cantidadDeButacasEnSala(Long idSala);
}