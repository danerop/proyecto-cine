package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.*;

@Repository("repositorioNotificacionUsuario")
public class RepositorioNotificacionUsuarioImpl implements RepositorioNotificacionUsuario{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioNotificacionUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void crearRegistroNotificacionUsuario(NotificacionUsuario notificacionUsuario) {
		Session session = sessionFactory.getCurrentSession();
		session.save(notificacionUsuario);
	}

	@Override
	public List<NotificacionUsuario> obtenerTodosLosRegistros() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(NotificacionUsuario.class).list();
	}

	@Override
	public List<NotificacionUsuario> obtenerNotificacionesDeUnUsuario(Usuario usuario) {
		return sessionFactory.getCurrentSession().createCriteria(NotificacionUsuario.class)
				.add(Restrictions.eq("usuario", usuario))
				.list();
	}

	@Override
	public List<NotificacionUsuario> obtenerRegistrosPorNotificacion(Notificacion notificacion) {
		return sessionFactory.getCurrentSession().createCriteria(NotificacionUsuario.class)
				.add(Restrictions.eq("notificacion", notificacion))
				.list();
	}
	
	
}
