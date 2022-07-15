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
		Pelicula pelicula = repositorioPeliculaDao.obtenerPeliculaPorID(id);
		if(pelicula == null) {
			throw new ExceptionPeliculaNoEncontrada("");
		}
		return pelicula;
	}

	@Override
	public void validarPelicula(Pelicula pelicula) {
		String campoNulo = "";
		
		if(pelicula.getNombre() == "") {
			campoNulo = campoNulo + "Rellenar nombre de película <br>";
		}
		if(pelicula.getDuracion() == null) {
			campoNulo = campoNulo + "Rellenar duración <br>";
		}
		if(pelicula.getAnio() == null) {
			campoNulo = campoNulo + "Rellenar año <br>";
		}
		if(pelicula.getUrlImagenPelicula() == "") {
			campoNulo = campoNulo + "Rellenar URL de imagen <br>";
		}
		if(campoNulo != "") {
			throw new ExceptionPeliculaCamposVacios(campoNulo);
		}
		if(pelicula.getAnio() <= 1800 || pelicula.getAnio() >= 2100) {
			throw new ExceptionPeliculaAnioNoValido();
		}
		if(pelicula.getDuracion() <= 0) {
			throw new ExceptionPeliculaDuracionNoValida();
		}
	}
	
	@Override
	public void actualizarPelicula(Pelicula pelicula) {
		validarPelicula(pelicula);
		
		repositorioPeliculaDao.actualizarPelicula(pelicula);
	}
	
	@Override
	public void guardarPelicula(Pelicula pelicula) {
		validarPelicula(pelicula);
		
		repositorioPeliculaDao.guardarPelicula(pelicula);
	}

	@Override
	public List<Pelicula> buscarPeliculasPorNombre(String busqueda) {
		
		busqueda = "%" + busqueda + "%";
		
		return repositorioPeliculaDao.buscarPeliculasPorNombre(busqueda);
	}

}
