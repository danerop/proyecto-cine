package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioSuscripcion")
public class RepositorioSuscripcionImpl implements RepositorioSuscripcion {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Long guardarSuscripcion(Suscripcion suscripcion) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(suscripcion);
	}

	@Override
	public void modificarSuscripcion(Suscripcion suscripcion) {
		sessionFactory.getCurrentSession().update(suscripcion);		
	}

	@Override
	public void eliminarSuscripcion(Suscripcion suscripcion) {
		sessionFactory.getCurrentSession().delete(suscripcion);
	}

	@Override
	public Suscripcion obtenerSuscripcionPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Suscripcion) session.get(Suscripcion.class, id);
	}
	
	@Override
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		 this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Suscripcion> obtenerTodasLasSuscripciones() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Suscripcion.class)
				      .list();
	}


}
