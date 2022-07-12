package ar.edu.unlam.tallerweb1.repositorios;


import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

@Repository("RepositorioFuncion")
public class RepositorioFuncionImpl implements RepositorioFuncion {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioFuncionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override

	public List<Funcion> obtenerTodasLasFunciones() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class).list();
	}

	@Override
	public Funcion buscarFuncionPorId(Long id) {
		return (Funcion) sessionFactory.getCurrentSession().createCriteria(Funcion.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public void guardarFuncion(Funcion funcion) {
		sessionFactory.getCurrentSession().save(funcion);
	}

	@Override
	public List<Funcion> obtenerFuncionesPorCineYPelicula(Long idCine, Long idPelicula) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("cine.id",idCine))
		.add(Restrictions.eq("pelicula.id", idPelicula))
		.list();
	}

	@Override
	public List<Funcion> obtenerFuncionesPorCine(Long idCine) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("cine.id",idCine))
		.list();
	}

	@Override
	public List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("pelicula.id", idPelicula))
//		.add(Restrictions.ne("entradasDisponibles", 0))
		.list();
	}

	@Override
	public List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("pelicula.id", idPelicula))
//		.add(Restrictions.ne("entradasDisponibles", 0))
		.setProjection(Projections.distinct(Projections.property("cine")))
		.list();
	}

	@Override
	public Funcion obtenerFuncionesPorCineFechaHoraSalaYPelicula(Long idCine, Long idPelicula, Date fechaHora,
			String hora, Long idSala) {
		Session session = sessionFactory.getCurrentSession();
		return (Funcion) session.createCriteria(Funcion.class)
		.add(Restrictions.eq("cine.id",idCine))
		.add(Restrictions.eq("pelicula.id", idPelicula))
		.add(Restrictions.eq("sala.id", idSala))
		.add(Restrictions.eq("fechaHora", fechaHora))
		.add(Restrictions.eq("hora", hora))
//		.add(Restrictions.ne("entradasDisponibles", 0))
		.uniqueResult();
	}

	@Override
	public List<Funcion> obtenerFuncionesUnicasPorFecha(Long idPelicula) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("pelicula.id", idPelicula))
		.add(Restrictions.ne("entradasDisponibles", 0l))
		.setProjection(Projections.distinct(Projections.property("fechaHora")))
		.list();
	}
	
	@Override
	public List<Funcion> obtenerFuncionesFuturasDePelicula(Long idPelicula){
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
				.add(Restrictions.eq("pelicula.id", idPelicula))
				.add(Restrictions.ge("fechaHora", new Date(System.currentTimeMillis())))
				.list();
	}
	@Override
	public List<Cine> obtenerCinesDisponiblesParaFuncionesFuturas(Long idPelicula) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class)
		.add(Restrictions.eq("pelicula.id", idPelicula))
		.add(Restrictions.ge("fechaHora", new Date(System.currentTimeMillis())))
		.add(Restrictions.gt("entradasDisponibles", 0))
		.setProjection(Projections.distinct(Projections.property("cine")))
		.list();
	}

	@Override
	public void actualizarFuncion(Funcion funcion) {
		sessionFactory.getCurrentSession().update(funcion);
	}

}
