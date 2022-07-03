package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioSuscripcion")
@Transactional
public class ServicioSuscripcionImpl implements ServicioSuscripcion {
	
	private RepositorioSuscripcion repositorioSuscripcionDao;
	private RepositorioUsuario repositorioUsuarioDao;
	
	@Autowired
	public ServicioSuscripcionImpl(RepositorioSuscripcion repositorioSuscripcionDao, RepositorioUsuario repositorioUsuarioDao) {
		this.repositorioSuscripcionDao = repositorioSuscripcionDao;
		this.repositorioUsuarioDao = repositorioUsuarioDao;
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
	public Suscripcion obtenerSuscripcionPorId(Long id) {
		return repositorioSuscripcionDao.obtenerSuscripcionPorId(id);
	}

	@Override
	public List<Suscripcion> obtenerTodasLasSuscripciones() {
		return repositorioSuscripcionDao.obtenerTodasLasSuscripciones();
	}

	@Override
	public void usarEntradaGratis(Usuario usuario) {
		Suscripcion sustemp=obtenerSuscripcionPorId(usuario.getSuscripcion().getId());
		sustemp.setCantidadDeBoletosGratisRestantes(sustemp.getCantidadDeBoletosGratisRestante()-1);
		modificarSuscripcion(sustemp);
	}

}
