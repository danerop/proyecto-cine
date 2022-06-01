package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDetalleSuscripcion;

@Service("servicioDetalleSuscripcion")
@Transactional
public class ServicioDetalleSuscripcionImpl implements ServicioDetalleSuscripcion {

	@Inject
	private RepositorioDetalleSuscripcion repositorioDetalleSuscripcionDao;
	
	@Override
	public Long guardarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		return repositorioDetalleSuscripcionDao.guardarDetalleSuscripcion(detalleSuscripcion);
	}

	@Override
	public void modificarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		repositorioDetalleSuscripcionDao.modificarDetalleSuscripcion(detalleSuscripcion);
		
	}

	@Override
	public void eliminarDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		repositorioDetalleSuscripcionDao.eliminarDetalleSuscripcion(detalleSuscripcion);
		
	}

	@Override
	public DetalleSuscripcion obtenerDetalleSuscripcionPorId(Long id) {
		return repositorioDetalleSuscripcionDao.obtenerDetalleSuscripcionPorId(id);
	}

	@Override
	public List<DetalleSuscripcion> obtenerTodasLasSuscripciones() {
		return repositorioDetalleSuscripcionDao.obtenerTodosLosDetallesSuscripcion();
	}

	
	
}
