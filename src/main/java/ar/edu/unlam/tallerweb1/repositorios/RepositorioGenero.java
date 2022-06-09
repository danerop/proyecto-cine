package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Genero;

public interface RepositorioGenero {

	void insertarGenero(Genero genero);

	List<Genero> obtenerTodosLosGeneros();

	SessionFactory getSessionFactory();
	
	void setSessionFactory(SessionFactory sessionFactory);
	
	Genero obtenerGeneroPorid(Long id);
	
}
