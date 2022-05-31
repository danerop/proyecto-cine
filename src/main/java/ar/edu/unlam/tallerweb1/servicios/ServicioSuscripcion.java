package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioSuscripcion {

	Long guardarSuscripcion (Suscripcion suscripcion);
	void modificarSuscripcion (Suscripcion suscripcion);
	void eliminarSuscripcion (Suscripcion suscripcion);
	Suscripcion obtenerSuscripcionPorId(Long id);
	Long guardarUsuario (Usuario usuario);
	Usuario obtenerUsuarioPorId (Long id);
	Usuario buscarPorSuscripcion(String tipoSuscripcion);
	List<Suscripcion> obtenerTodasLasSuscripciones();
}
