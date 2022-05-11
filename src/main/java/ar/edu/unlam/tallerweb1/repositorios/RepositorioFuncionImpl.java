package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

@Repository("repositorioFuncion")
public class RepositorioFuncionImpl implements RepositorioFuncion {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioFuncionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Funcion> obtenerTodasLasFunciones() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Funcion.class).list();
	}

	@Override
	public Funcion buscarFuncionPorId(Long id) {
		return (Funcion) sessionFactory.getCurrentSession().createCriteria(Funcion.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public void guardarFuncion(Funcion funcion) {
		sessionFactory.getCurrentSession().save(funcion);
	}

}
