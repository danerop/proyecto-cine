package ar.edu.unlam.tallerweb1.repositorios;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("RepositorioFuncion")
public class RepositorioFuncionImpl implements RepositorioFuncion {

	private SessionFactory sessionFactory;
	
    @Autowired
	public RepositorioFuncionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Funcion buscarFuncion(Long idcine, String fecha, Integer hora, Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		Date fechaz=Date.valueOf(fecha);
		return (Funcion) session.createCriteria(Funcion.class)
				.add(Restrictions.eq("idcine", idcine))
				.add(Restrictions.eq("fecha", fechaz))
				.uniqueResult();
		
	}

	@Override
	public void guardar(Funcion funcion) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(funcion);
	}

	@Override
	public Funcion buscar(Long idfuncion) {
		// TODO Auto-generated method stub
		Funcion funcionComprobar=sessionFactory.getCurrentSession().get(Funcion.class, idfuncion);
		return funcionComprobar;
	}

}
