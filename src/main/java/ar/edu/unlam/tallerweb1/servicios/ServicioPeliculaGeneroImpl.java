package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	public List<Pelicula> obtenerPeliculasRecomendadas(List<Genero> listaGeneros) {
		Iterator<PeliculaGenero> iterPeliculaGenero = repositorioPeliculaGeneroDao.obtenerPeliculasGeneroSegunListaDeGeneros(listaGeneros).iterator();
		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
		
		while(iterPeliculaGenero.hasNext()) {
			Pelicula pelicula = iterPeliculaGenero.next().getPelicula();
			
			if(!listaPeliculas.contains(pelicula)){
				listaPeliculas.add(pelicula);
			}
		}
		
		return listaPeliculas;
	}
	
	
}
