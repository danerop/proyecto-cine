package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioFavorito")
public class RepositorioFavoritoImpl implements RepositorioFavorito {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioFavoritoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertarFavorito(Favorito favorito) {
		sessionFactory.getCurrentSession().save(favorito);		
	}

	@Override
	public List<Favorito> obtenerTodosLosFavoritos() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class).list();
	}

	@Override
	public List<Favorito> obtenerFavoritosDeUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Favorito>) session.createCriteria(Favorito.class)
				                       .add(Restrictions.eq("usuario", usuario))
				                       .list();
//		Session session = sessionFactory.getCurrentSession();
//		return (List<Long>) session.createCriteria(Favorito.class)
//		.add(Restrictions.eq("usuario", usuario))
//		.setProjection(Projections.property("usuario.id"))
//		.list();
//		Session session = sessionFactory.getCurrentSession();
//		return (List<String>) session.createCriteria(PeliculaGenero.class)
//							.add(Restrictions.eq("pelicula", pelicula))
//							.createAlias("genero", "tablaGenero")
//							.setProjection(Projections.property("tablaGenero.nombre"))
//							.list();
	}
	
	@Override
	public List<Favorito> obtenerFavoritosPorGenero(Long idGenero) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class)
		.add(Restrictions.eq("genero.id", idGenero))
		.list();
	}

	@Override
	public List<Favorito> obtenerListaDeFavoritosPorUsuario(Long idUsuario) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Favorito>) session.createCriteria(Favorito.class)
		.add(Restrictions.eq("usuario.id", idUsuario))
		.list();
	}

	@Override
	public Favorito buscarFavoritoPorId(Long id) {
		return (Favorito) sessionFactory.getCurrentSession().createCriteria(Favorito.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public void modificarFavorito(Favorito favorito) {
		sessionFactory.getCurrentSession().update(favorito);
	}

	@Override
	public List<Favorito> obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class)
		.add(Restrictions.eq("usuario.id", idUsuario))
		.add(Restrictions.eq("genero.id", idGenero))
		.list();
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
	public Favorito obtenerFavoritoPorUsuario(Long idUsuario) {
		Session session = sessionFactory.getCurrentSession();
		return (Favorito) session.createCriteria(Favorito.class)
		.add(Restrictions.eq("usuario.id", idUsuario))
		.uniqueResult();
	}

	@Override
	public void eliminarFavorito(Favorito favorito) {
		sessionFactory.getCurrentSession().remove(favorito.getUsuario().getId());
	}
}
