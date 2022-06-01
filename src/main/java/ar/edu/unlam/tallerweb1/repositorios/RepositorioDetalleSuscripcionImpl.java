package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioDetalleSuscripcion")
public class RepositorioDetalleSuscripcionImpl implements RepositorioDetalleSuscripcion {
	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Long guardarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(detalleSuscripcion);
	}

	@Override
	public void modificarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		sessionFactory.getCurrentSession().update(detalleSuscripcion);		
		
	}

	@Override
	public void eliminarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		sessionFactory.getCurrentSession().delete(detalleSuscripcion);		
		
	}

	@Override
	public DetalleSuscripcion obtenerDetalleSuscripcionPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (DetalleSuscripcion) session.get(DetalleSuscripcion.class, id);
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	@Override
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@Override
	public List<DetalleSuscripcion> obtenerTodosLosDetallesSuscripcion() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(DetalleSuscripcion.class)
				      .list();
	}

}
