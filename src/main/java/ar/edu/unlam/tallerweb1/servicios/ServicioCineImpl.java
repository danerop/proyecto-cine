package ar.edu.unlam.tallerweb1.servicios;

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
		return repositorioCineDao.buscarCinePorId(id);
	}

	@Override
	public void guardarCine(Cine cine) {
		repositorioCineDao.guardarCine(cine);
	}

}
