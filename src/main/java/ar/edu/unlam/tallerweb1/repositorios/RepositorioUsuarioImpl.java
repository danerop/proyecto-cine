package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate
	// el mismo esta difinido en el archivo hibernateContext.xml
	@Inject
	private SessionFactory sessionFactory;

   /* @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}*/

	@Override
	public Usuario buscarUsuario(String email, String password) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran mas de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.uniqueResult();
	}

	@Override
	public void guardar(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}

	@Override
	public Usuario buscar(String email) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
	}

	@Override
	public void modificar(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.get(Usuario.class, id);
	}
	
	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public Long insertarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioPorRol(Usuario usuario) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.add(Restrictions.eq("id", usuario.getId()))
				.add(Restrictions.eq("rol", usuario.getRol()))
				.uniqueResult();
	}

	@Override
	public Usuario buscarPorSuscripcion(String tipoSuscripcion) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.createAlias("suscripcion", "suscripcionBuscada")
				.add(Restrictions.eq("suscripcionBuscada.tipo", tipoSuscripcion))
				.uniqueResult();
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
	public Usuario buscarUsuarioPorSuscripcionID(Long idSuscripcion) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
				.createAlias("suscripcion", "suscripcionBuscada")
				.add(Restrictions.eq("suscripcionBuscada.id", idSuscripcion))
				.uniqueResult();
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuariosActivos() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Usuario.class)
				.add(Restrictions.eq("activo", true))
				.list();
	}

	@Override
	public List<Usuario> obtenerUsuariosPorRol(String rol) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Usuario.class)
				.add(Restrictions.eq("rol", rol))
				.list();
	}
	
}
