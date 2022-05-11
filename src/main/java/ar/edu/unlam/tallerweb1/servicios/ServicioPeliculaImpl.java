package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;

@Service("servicioPelicula")
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

	private RepositorioPelicula repositorioPeliculaDao;
	
	@Autowired
	public ServicioPeliculaImpl(RepositorioPelicula repositorioPeliculaDao) {
		this.repositorioPeliculaDao = repositorioPeliculaDao;
	}

	@Override
	public List<Pelicula> obtenerTodosLasPeliculas() {
		return repositorioPeliculaDao.obtenerTodasLasPeliculas();
	}

	@Override
	public Pelicula buscarPeliculaPorID(Long id) {
		return repositorioPeliculaDao.obtenerPeliculaPorID(id);
	}

	@Override
	public void guardarPelicula(Pelicula pelicula) {
		repositorioPeliculaDao.guardarPelicula(pelicula);
	}
	
	

}
