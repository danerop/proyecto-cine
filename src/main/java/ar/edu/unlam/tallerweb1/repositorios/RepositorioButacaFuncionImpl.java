package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

@Repository("repositorioButacaFuncion")
public class RepositorioButacaFuncionImpl implements RepositorioButacaFuncion {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioButacaFuncionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<ButacaFuncion> obtenerButacasPorFuncion(Funcion funcion) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(ButacaFuncion.class)
				.add(Restrictions.eq("funcion.id", funcion.getId()))
				.list();
	}
	@Override
	public void guardarButacaFuncion(ButacaFuncion butacaFuncion) {
		sessionFactory.getCurrentSession().save(butacaFuncion);
		
	}
	@Override
	public ButacaFuncion obtenerPorButacaYFuncion(Funcion funcion, Long idButaca) {
		Session session = sessionFactory.getCurrentSession();
		return (ButacaFuncion) session.createCriteria(ButacaFuncion.class)
				.add(Restrictions.eq("funcion.id", funcion.getId()))
				.add(Restrictions.eq("butaca.id", idButaca))
				.uniqueResult();
	}
	@Override
	public void actualizarButacaFuncion(ButacaFuncion butacaFuncion) {
		sessionFactory.getCurrentSession().update(butacaFuncion);
	}
}
