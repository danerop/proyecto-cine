package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Favorito;

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
