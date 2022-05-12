package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Cine;

@Repository("repositorioCine")
public class RepositorioCineImpl implements RepositorioCine {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCineImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Cine> obtenerTodosLosCines() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Cine.class).list();
	}
	
	@Override
	public Cine buscarCinePorId(Long id) {
		return (Cine) sessionFactory.getCurrentSession().createCriteria(Cine.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public void guardarCine(Cine cine) {
		sessionFactory.getCurrentSession().save(cine);
	}
	
	
}
