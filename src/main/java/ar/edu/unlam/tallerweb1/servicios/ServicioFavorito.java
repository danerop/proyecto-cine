package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioFavorito {

	List<Favorito> obtenerTodosLosFavoritos();
	Favorito buscarFavoritoPorId(Long id);
	void insertarFavorito(Favorito favorito);
	void modificarFavorito(Favorito favorito);
	void eliminarGenerosFavoritos(List<Long> idGeneros);
	List<Favorito> obtenerFavoritosPorGenero(Long idGenero);
	List<Favorito> obtenerListaDeFavoritosPorUsuario(Long idUsuario);
	List<Favorito> obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero);
	List<Favorito> obtenerFavoritosDeUsuario(Usuario usuario);
	Favorito obtenerFavoritoPorUsuario(Long idUsuario);
	void guardarGenerosFavoritos(List<Long> idGeneros, Usuario usuario);
	void modificarGenerosFavoritos(List<Long> idGeneros, Usuario usuario);
}
