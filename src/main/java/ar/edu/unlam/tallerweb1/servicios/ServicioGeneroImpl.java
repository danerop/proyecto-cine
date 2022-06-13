package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGenero;

@Service("servicioGenero")
@Transactional
public class ServicioGeneroImpl implements ServicioGenero {
	
	private RepositorioGenero servicioGeneroDao;
	private RepositorioPeliculaGenero servicioPeliculaGeneroDao;

	@Autowired
	public ServicioGeneroImpl(RepositorioGenero servicioGeneroDao, RepositorioPeliculaGenero servicioPeliculaGeneroDao){
		this.servicioGeneroDao = servicioGeneroDao;
		this.servicioPeliculaGeneroDao = servicioPeliculaGeneroDao;
	}

	@Override
	public void guardarGenero(Genero genero) {
		servicioGeneroDao.insertarGenero(genero);
	}

	@Override
	public List<Genero> obtenerTodosLosGeneros() {
		return servicioGeneroDao.obtenerTodosLosGeneros();
	}

	@Override
	public List<String> obtenerGenerosDeUnaPelicula(Pelicula pelicula) {
		return servicioPeliculaGeneroDao.obtenerGenerosDeUnaPelicula(pelicula);
	}
}