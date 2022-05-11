package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Sala;

@Repository("repositorioSala")
public class RepositorioSalaImpl implements RepositorioSala{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioSalaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Sala> obtenerTodasLasSalas() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Sala.class).list();
	}

	@Override
	public Sala buscarSalaPorId(Long id) {
		return (Sala) sessionFactory.getCurrentSession().createCriteria(Sala.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}

	@Override
	public void guardarSala(Sala sala) {
		sessionFactory.getCurrentSession().save(sala);
	}

}
