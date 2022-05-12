package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

@Repository("repositorioBoleto")
public class RepositorioBoletoImpl implements RepositorioBoleto{

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioBoletoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardarBoleto(Boleto boleto) {
		sessionFactory.getCurrentSession().save(boleto);
		
	}

	@Override
	public Boleto buscarBoleto(Long id) {
		return (Boleto) sessionFactory.getCurrentSession().createCriteria(Boleto.class)
				.add(Restrictions.eq("id",id))
				.uniqueResult();
	}
}
