package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioGenero")
public class RepositorioGeneroImpl implements RepositorioGenero {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioGeneroImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertarGenero(Genero genero) {
		Session session = sessionFactory.getCurrentSession();
				session.save(genero);
	}

	@Override
	public List<Genero> obtenerTodosLosGeneros() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Genero.class)
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
	public Genero obtenerGeneroPorid(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Genero) session.get(Genero.class, id);
	}

	@Override
	public void agregarListaDeGeneros(List<Long> idGeneros) {
		Session session = sessionFactory.getCurrentSession();
		session.save(idGeneros);
	}

	@Override
	public List<Long> obtenerGenerosPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Long>) session.get(Genero.class, id);
	}

	@Override
	public List<Long> obtenerListaDeGenerosPorId(List<Long> idGeneros) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Long>) session.getEntityGraphs(Genero.class).get(idGeneros.size());
	}
}
