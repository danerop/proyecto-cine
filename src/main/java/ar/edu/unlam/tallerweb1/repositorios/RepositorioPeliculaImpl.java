package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

@Repository("repositorioPelicula")
public class RepositorioPeliculaImpl implements RepositorioPelicula {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPeliculaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardarPelicula(Pelicula pelicula) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pelicula);
	}

	@Override
	public Pelicula obtenerPeliculaPorID(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Pelicula) session.get(Pelicula.class, id);
	}

	@Override
	public List<Pelicula> obtenerTodasLasPeliculas() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				      .list();
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
	public List<Pelicula> buscarPeliculasPorNombre(String nombre) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.like("nombre", nombre))
				.list();
	}

	@Override
	public void actualizarPelicula(Pelicula pelicula) {
		sessionFactory.getCurrentSession().update(pelicula);
	}
}
