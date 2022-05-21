package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Butaca;

@Repository("repositorioButaca")
public class RepositorioButacaImpl implements RepositorioButaca{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioButacaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Butaca> obtenerButacasPorSala(Long idSala) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Butaca.class)
				.add(Restrictions.eq("sala.id", idSala))
				.list();
	}
	
}
