package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;

public interface RepositorioDetalleSuscripcion {

	Long guardarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	void modificarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	void eliminarDetalleSuscripcion (DetalleSuscripcion detalleSuscripcion);
	DetalleSuscripcion obtenerDetalleSuscripcionPorId (Long id);
	void setSessionFactory(SessionFactory sessionFactory);
	SessionFactory getSessionFactory();
	List<DetalleSuscripcion> obtenerTodosLosDetallesSuscripcion();}
