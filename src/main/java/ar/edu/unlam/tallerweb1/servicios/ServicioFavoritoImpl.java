package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFavorito;

@Service("servicioFavorito")
@Transactional
public class ServicioFavoritoImpl implements ServicioFavorito {

	@Inject
	private RepositorioFavorito repositorioFavoritoDao;
	
	@Autowired
	public ServicioFavoritoImpl(RepositorioFavorito repositorioFavoritoDao){
		this.repositorioFavoritoDao = repositorioFavoritoDao;
	}
	
	@Override
	public List<Favorito> obtenerTodosLosFavoritos() {
		return repositorioFavoritoDao.obtenerTodosLosFavoritos();
	}

	@Override
	public Favorito buscarFavoritoPorId(Long id) {
		return repositorioFavoritoDao.buscarFavoritoPorId(id);
	}

	@Override
	public void insertarFavorito(Favorito favorito) {
		repositorioFavoritoDao.insertarFavorito(favorito);
	}

	@Override
	public void modificarFavorito(Favorito favorito) {
		repositorioFavoritoDao.modificarFavorito(favorito);
	}

	@Override
	public List<Favorito> obtenerFavoritosPorGenero(Long idGenero) {
		return repositorioFavoritoDao.obtenerFavoritosPorGenero(idGenero);
	}

	@Override
	public List<Favorito> obtenerFavoritoPorUsuario(Long idUsuario) {
		return repositorioFavoritoDao.obtenerFavoritoPorUsuario(idUsuario);
	}

	@Override
	public List<Favorito> obtenerFavoritoPorUsuarioYGenero(Long idUsuario, Long idGenero) {
		return repositorioFavoritoDao.obtenerFavoritoPorUsuarioYGenero(idUsuario, idGenero);
	}

	@Override
	public List<Favorito> obtenerFavoritosDeUsuario(Usuario usuario) {
		return repositorioFavoritoDao.obtenerFavoritosDeUsuario(usuario);
	}
}
