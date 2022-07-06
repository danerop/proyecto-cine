package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;

@Repository("repositorioPeliculaGenero")
public class RepositorioPeliculaGeneroImpl implements RepositorioPeliculaGenero {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioPeliculaGeneroImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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
	public List<PeliculaGenero> obtenerTodosLosRegistros() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PeliculaGenero.class).list();
	}

	@Override
	public List<Genero> obtenerGenerosDePeliculasCompradas(List<Pelicula> listaPeliculas) {
		if(listaPeliculas.size()==0) {
			return new ArrayList<Genero>();
		}
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PeliculaGenero.class)
				.add(Restrictions.in("pelicula", listaPeliculas))
				.setProjection(Projections.property("genero"))
				.list();
	}
	@Override
	public PeliculaGenero obtenerRegistroPorPeliculaYGenero(Long idPelicula, Long idGenero) {
		Session session = sessionFactory.getCurrentSession();
		return (PeliculaGenero) session.createCriteria(PeliculaGenero.class)
				.add(Restrictions.eq("pelicula.id", idPelicula))
				.add(Restrictions.eq("genero.id", idGenero))
				.uniqueResult();
	}
	@Override
	public PeliculaGenero buscarRegistroPorId(Long id) {
		return (PeliculaGenero) sessionFactory.getCurrentSession().createCriteria(PeliculaGenero.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}
	
	@Override
	public List<Pelicula> obtenerPeliculasSegunGeneros(List<Genero> listaGeneros) {
		if(listaGeneros.size()==0) {
			return new ArrayList<Pelicula>();
		}
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PeliculaGenero.class)
				.add(Restrictions.in("genero", listaGeneros))
				.setProjection(Projections.distinct(Projections.property("pelicula")))
				.setMaxResults(5)
				.list();
	}

	@Override
	public List<Pelicula> obtenerPeliculasSegunGenerosDistintasDeLista(List<Genero> generos, List<Pelicula> peliculas) {
		if(generos.size()==0) {
			return new ArrayList<Pelicula>();
		}
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(PeliculaGenero.class)
				.add(Restrictions.in("genero", generos))
				.add(Restrictions.not(Restrictions.in("pelicula", peliculas)))
				.setProjection(Projections.distinct(Projections.property("pelicula")))
				.setMaxResults(5)
				.list();
	}

}
