package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGenero;

@Service("servicioPeliculaGenero")
@Transactional
public class ServicioPeliculaGeneroImpl implements ServicioPeliculaGenero{
	
	private RepositorioPeliculaGenero repositorioPeliculaGeneroDao;
	private ServicioFavorito servicioFavorito;
	private ServicioBoleto servicioBoleto;
	
	@Autowired
	public ServicioPeliculaGeneroImpl(RepositorioPeliculaGenero repositorioPeliculaGeneroDao, ServicioFavorito servicioFavorito, ServicioBoleto servicioBoleto) {
		this.repositorioPeliculaGeneroDao = repositorioPeliculaGeneroDao;
		this.servicioFavorito = servicioFavorito;
		this.servicioBoleto = servicioBoleto;
	}

	@Override
	public List<Pelicula> obtenerPeliculasRecomendadasPorGenerosFavoritos(Usuario user) {
		
		List<Genero> generos = servicioFavorito.obtenerGenerosFavoritosDeUsuario(user);
		
		return repositorioPeliculaGeneroDao.obtenerPeliculasSegunGeneros(generos);
	}

	@Override
	public List<Pelicula> obtenerPeliculasRecomendadasPorBoletosComprados(Usuario user) {
		
		List<Pelicula> peliculas = servicioBoleto.obtenerPeliculasDeFuncionesCompradasPorUsuario(user);
		
		List<Genero> generos = repositorioPeliculaGeneroDao.obtenerGenerosDePeliculasCompradas(peliculas);
				
		return repositorioPeliculaGeneroDao.obtenerPeliculasSegunGenerosDistintasDeLista(generos, peliculas);
	}

}
