package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSala;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion {
	
	@Inject
	private RepositorioSuscripcion repositorioSuscripcionDao;
	
	@Inject 
	private RepositorioUsuario repositorioUsuarioDao;
	
	@Autowired
	public ServicioSuscripcionImpl(RepositorioSuscripcion repositorioSuscripcionDao) {
		this.repositorioSuscripcionDao = repositorioSuscripcionDao;
	}
	
	@Override
	public Long guardarSuscripcion(Suscripcion suscripcion) {
		return repositorioSuscripcionDao.guardarSuscripcion(suscripcion);
		
	}

	@Override
	public void modificarSuscripcion(Suscripcion suscripcion) {
		repositorioSuscripcionDao.modificarSuscripcion(suscripcion);
		
	}

	@Override
	public void eliminarSuscripcion(Suscripcion suscripcion) {
		repositorioSuscripcionDao.eliminarSuscripcion(suscripcion);
		
	}

	@Override
	public Long guardarUsuario(Usuario usuario) {
		return repositorioUsuarioDao.insertarUsuario(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		return repositorioUsuarioDao.buscarUsuarioPorId(id);
	}

	@Override
	public Suscripcion obtenerSuscripcionPorId(Long id) {
		return repositorioSuscripcionDao.obtenerSuscripcionPorId(id);
	}

	@Override
	public Usuario buscarPorSuscripcion(String tipoSuscripcion) {
		return repositorioUsuarioDao.buscarPorSuscripcion(tipoSuscripcion);
	}

	@Override
	public List<Suscripcion> obtenerTodasLasSuscripciones() {
		return repositorioSuscripcionDao.obtenerTodasLasSuscripciones();
	}

}
