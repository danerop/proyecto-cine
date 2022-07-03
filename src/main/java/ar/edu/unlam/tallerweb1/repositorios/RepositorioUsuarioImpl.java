package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

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
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Usuario.class, id);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", email))
				.uniqueResult();
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
	public void actualizarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		session.update(usuario);		
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		session.save(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuariosPorRol(String rol) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Usuario.class)
				.add(Restrictions.eq("rol", rol))
				.list();
	}

	@Override
	public List<Usuario> obtenerUsuariosActivos() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Usuario.class)
				.add(Restrictions.eq("activo", true))
				.list();
	}
	
}
