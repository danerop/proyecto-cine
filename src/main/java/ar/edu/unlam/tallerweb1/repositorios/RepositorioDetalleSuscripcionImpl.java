package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;

@Repository("repositorioDetalleSuscripcion")
public class RepositorioDetalleSuscripcionImpl implements RepositorioDetalleSuscripcion {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioDetalleSuscripcionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		Session session = sessionFactory.getCurrentSession();
		session.save(detalleSuscripcion);
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
