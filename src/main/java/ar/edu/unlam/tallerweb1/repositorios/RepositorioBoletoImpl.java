package ar.edu.unlam.tallerweb1.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

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

	@Override
	public Boleto buscarBoletUnicoPorDatos(Long idcliente, Long idfuncion, Long idbutaca) {
		return (Boleto) sessionFactory.getCurrentSession().createCriteria(Boleto.class)
				.add(Restrictions.eq("cliente.id",idcliente))
				.add(Restrictions.eq("funcion.id",idfuncion))
				.add(Restrictions.eq("butaca.id",idbutaca))
				.uniqueResult();
	}
	@Override
	public void actualizarBoleto(Boleto boleto) {
		sessionFactory.getCurrentSession().update(boleto);
		
	}
	@Override
	public void eliminarBoleto(Boleto boleto) {
		sessionFactory.getCurrentSession().delete(boleto);;
		
	}

	@Override
	public List<Boleto> buscarBoletosDeUnUsuario(Long idUsuario) {
		return sessionFactory.getCurrentSession().createCriteria(Boleto.class)
				.add(Restrictions.eq("cliente.id",idUsuario))
				.add(Restrictions.eq("temporal", false))
				.list();
	}

	@Override
	public List<Funcion> obtenerFuncionesCompradasPorUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Boleto.class)
				.add(Restrictions.eq("cliente", user))
				.add(Restrictions.eq("temporal", false))
				.setProjection(Projections.distinct(Projections.property("funcion")))
				.list();
	}

	@Override
	public List<Pelicula> obtenerPeliculasDeFuncionesCompradasPorUsuario(Usuario user) {
		if(obtenerFuncionesCompradasPorUsuario(user).size() == 0) {
			return new ArrayList<Pelicula>();
		}
		
		Session session = sessionFactory.getCurrentSession();
		List<Long> funciones = session.createCriteria(Boleto.class)
				.add(Restrictions.eq("cliente", user))
				.setProjection(Projections.distinct(Projections.property("funcion.id")))
				.list();
		
		return session.createCriteria(Funcion.class)
				.add(Restrictions.in("id", funciones))
				.setProjection(Projections.distinct(Projections.property("pelicula")))
				.list();
	}

	@Override
	public Long obtenerCantidadUsuariosQueVieronLaPelicula(Pelicula pelicula) {
		Session session = sessionFactory.getCurrentSession();
		List<Funcion> funciones = session.createCriteria(Funcion.class)
				.add(Restrictions.eq("pelicula", pelicula))
				.list();
		
		List<Long> usuarios =  session.createCriteria(Boleto.class)
				.add(Restrictions.in("funcion", funciones))
				.setProjection(Projections.distinct(Projections.property("cliente.id")))
				.list();
		
		return (Long) session.createCriteria(Usuario.class)
				.add(Restrictions.in("id", usuarios))
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}

}
