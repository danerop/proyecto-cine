package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPeliculaGenero;

@Service("servicioPeliculaGenero")
@Transactional
public class ServicioPeliculaGeneroImpl implements ServicioPeliculaGenero{
	
	private RepositorioPeliculaGenero repositorioPeliculaGeneroDao;
	
	@Autowired
	public ServicioPeliculaGeneroImpl(RepositorioPeliculaGenero repositorioPeliculaGeneroDao) {
		this.repositorioPeliculaGeneroDao = repositorioPeliculaGeneroDao;
	}

	@Override
	public List<Pelicula> obtenerPeliculasRecomendadas(List<Genero> listaGenerosFav, List<Pelicula> listaPeliculasCompradas) {
		
		//List<Genero> listaGenerosComprados = repositorioPeliculaGeneroDao.obtenerGenerosDePeliculasCompradas(listaPeliculasCompradas);
		//listaGenerosFav.addAll(listaGenerosComprados);
		
		//List<Genero> listaGeneros = listaGenerosFav.stream().distinct().collect(Collectors.toList());
		
		return repositorioPeliculaGeneroDao.obtenerPeliculasRecomendadasSegunGeneroFavorito(listaGenerosFav);
	}
	
	
}
