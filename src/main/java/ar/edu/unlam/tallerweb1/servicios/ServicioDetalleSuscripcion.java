package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;

public interface ServicioDetalleSuscripcion {

	void guardarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	void modificarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	void eliminarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	DetalleSuscripcion obtenerDetalleSuscripcionPorId(Long id);
	List<DetalleSuscripcion> obtenerTodasLasSuscripciones();
	
	void validarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
}
