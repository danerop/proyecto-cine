package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDetalleSuscripcion;

@Service("servicioDetalleSuscripcion")
@Transactional
public class ServicioDetalleSuscripcionImpl implements ServicioDetalleSuscripcion {

	private RepositorioDetalleSuscripcion repositorioDetalleSuscripcionDao;
	
	@Autowired
	public ServicioDetalleSuscripcionImpl(RepositorioDetalleSuscripcion repositorioDetalleSuscripcionDao) {
		this.repositorioDetalleSuscripcionDao = repositorioDetalleSuscripcionDao;
	}
	
	@Override
	public void guardarDetalleSuscripcion(DetalleSuscripcion ds) {
		String msg = "";
		
		if(ds.getCantidadBoletosGratis() == null){ds.setCantidadBoletosGratis(0l);}
		if(ds.getDescuentoEnBoletos() == null){ds.setDescuentoEnBoletos((float)0.0);}
		
		if(ds.getTipo() == ""){
			msg = msg + "Rellenar nombre <br>";
		}
		if(ds.getCuota() == null){
			msg = msg + "Rellenar cuota <br>";
		}
		if(msg != "") {
			throw new ExceptionDetalleSuscripcionCamposVacios(msg);
		}
		if(ds.getCantidadBoletosGratis() < 0l) {
			throw new ExceptionDetalleSuscripcionBoletosGratisNoValido();
		}
		if(ds.getDescuentoEnBoletos() < 0.0) {
			throw new ExceptionDetalleSuscripcionDescuentoNoValido();
		}
		
		repositorioDetalleSuscripcionDao.guardarDetalleSuscripcion(ds);
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
		DetalleSuscripcion ds = repositorioDetalleSuscripcionDao.obtenerDetalleSuscripcionPorId(id);
		if(ds == null) {
			throw new ExceptionDetalleSuscripcionNoEncontrada();
		}
		return ds;
	}

	@Override
	public List<DetalleSuscripcion> obtenerTodasLasSuscripciones() {
		return repositorioDetalleSuscripcionDao.obtenerTodosLosDetallesSuscripcion();
	}

	
	
}
