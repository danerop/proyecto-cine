package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCine;

@Service("servicioCine")
@Transactional
public class ServicioCineImpl implements ServicioCine{

	private RepositorioCine repositorioCineDao;
	
	@Autowired
	public ServicioCineImpl(RepositorioCine repositorioCineDao){
		this.repositorioCineDao = repositorioCineDao;
	}
	
	@Override
	public Cine buscarCinePorID(Long id) {
		Cine cine = repositorioCineDao.buscarCinePorId(id);
		if(cine == null) {
			throw new ExceptionCineNoEncontrado("");
		}
		return cine;
	}

	@Override
	public void guardarCine(Cine cine){
		String msg = "";
		
		if(cine.getNombreLocal()=="") {
			msg= msg+"Rellenar nombre de local <br>";
		}
		if(cine.getTelefono()=="") {
			msg= msg+"Rellenar teléfono <br>";
		}
		if(cine.getDireccion()=="") {
			msg= msg+"Rellenar dirección <br>";
		}
		if(cine.getLatitud()==null || cine.getLongitud()==null) {
			msg= msg+"Rellenar ubicación <br>";
		}
		
		if(msg != "") {
			throw new ExceptionCineCamposVacios(msg);
		}
		
		repositorioCineDao.guardarCine(cine);
	}

	@Override
	public List<Cine> obtenerTodosLosCines() {
		return repositorioCineDao.obtenerTodosLosCines();
	}

}
