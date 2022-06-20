package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioFavorito {
	
	List<Favorito> obtenerTodosLosFavoritos();
	Favorito buscarFavoritoPorId(Long id);
	void insertarFavorito(Favorito favorito);
	void modificarFavorito(Favorito favorito);
	void eliminarFavorito(Favorito favorito);
	List<Favorito> obtenerFavoritosPorGenero(Long idGenero);
	List<Favorito> obtenerListaDeFavoritosPorUsuario(Long idUsuario);
	List<Favorito> obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero);
	Favorito obtenerFavoritoPorUsuario(Long idUsuario);
	SessionFactory getSessionFactory();
	void setSessionFactory(SessionFactory sessionFactory);
	List<Favorito> obtenerFavoritosDeUsuario(Usuario usuario);
	
}
