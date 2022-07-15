package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Repository("repositorioNotificacion")
public class RepositorioNotificacionImpl implements RepositorioNotificacion{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Notificacion> obtenerTodasLasNotificaciones() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Notificacion.class).list();
	}

	@Override
	public void registrarNotificacion(Notificacion notificacion) {
		Session session=sessionFactory.getCurrentSession();
				session.save(notificacion);
	}
	
}
