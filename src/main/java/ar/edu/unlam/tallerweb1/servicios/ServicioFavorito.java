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
	void inactivar(Long idRegistro);
	List<Favorito> obtenerFavoritosPorGenero(Long idGenero);
	List<Favorito> obtenerFavoritoPorUsuario(Long idUsuario);
	Favorito obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero);
}
