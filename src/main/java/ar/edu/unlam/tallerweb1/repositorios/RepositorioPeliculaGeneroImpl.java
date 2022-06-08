package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

@Repository("repositorioPeliculaGenero")
public class RepositorioPeliculaGeneroImpl implements RepositorioPeliculaGenero {
	
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public void insertarPeliculaGenero(PeliculaGenero peliculaGenero) {
		Session session = sessionFactory.getCurrentSession();
		session.save(peliculaGenero);
		
	}

	@Override
	public List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula) {
		Session session = sessionFactory.getCurrentSession();
		return (List<String>) session.createCriteria(PeliculaGenero.class)
							.add(Restrictions.eq("pelicula", pelicula))
							.createAlias("genero", "tablaGenero")
							.setProjection(Projections.property("tablaGenero.nombre"))
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
}
