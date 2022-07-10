package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Sala;

public interface ServicioSala {
	
	List<Sala> obtenerTodasLasSalas();
	Sala buscarSalaPorId(Long id);
	void guardarSala(Sala sala);
	void actualizarSala(Sala sala);
}
