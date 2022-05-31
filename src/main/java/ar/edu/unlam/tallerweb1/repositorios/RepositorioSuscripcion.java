package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioSuscripcion {

	Long guardarSuscripcion (Suscripcion suscripcion);
	void modificarSuscripcion (Suscripcion suscripcion);
	void eliminarSuscripcion (Suscripcion suscripcion);
	Suscripcion obtenerSuscripcionPorId (Long id);
	void setSessionFactory(SessionFactory sessionFactory);
	SessionFactory getSessionFactory();
	List<Suscripcion> obtenerTodasLasSuscripciones();
}
