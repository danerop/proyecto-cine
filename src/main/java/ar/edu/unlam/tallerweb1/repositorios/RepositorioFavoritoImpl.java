package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Genero;
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
	public List<Favorito> obtenerFavoritosPorGenero(Long idGenero) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class)
		.add(Restrictions.eq("genero.id", idGenero))
		.list();
	}

	@Override
	public List<Favorito> obtenerFavoritosPorUsuario(Long idUsuario) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class)
		.add(Restrictions.eq("usuario.id", idUsuario))
		.add(Restrictions.eq("activo", true))
		.list();
	}

	@Override
	public Favorito buscarFavoritoPorId(Long id) {
		return (Favorito) sessionFactory.getCurrentSession().get(Favorito.class, id);
	}

	@Override
	public void modificarFavorito(Favorito favorito) {
		sessionFactory.getCurrentSession().update(favorito);		
	}

	@Override
	public Favorito obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero) {
		Session session = sessionFactory.getCurrentSession();
		return (Favorito) session.createCriteria(Favorito.class)
				.add(Restrictions.eq("usuario.id", idUsuario))
				.add(Restrictions.eq("genero.id", idGenero))
				.uniqueResult();
	}

	@Override
	public List<Genero> obtenerGenerosFavoritosDeUsuario(Usuario usuario) {
		if(obtenerFavoritosPorUsuario(usuario.getId()).size() == 0) {
			return new ArrayList<Genero>();
		}
		
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Favorito.class)
				.add(Restrictions.eq("usuario", usuario))
				.add(Restrictions.eq("activo", true))
				.setProjection(Projections.distinct(Projections.property("genero")))
				.list();
	}
	
}
