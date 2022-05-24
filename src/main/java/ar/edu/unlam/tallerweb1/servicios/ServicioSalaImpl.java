package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSala;

@Service("servicioSala")
@Transactional
public class ServicioSalaImpl implements ServicioSala{

	private RepositorioSala repositorioSalaDao;
	
	@Autowired
	public ServicioSalaImpl(RepositorioSala repositorioSalaDao) {
		this.repositorioSalaDao = repositorioSalaDao;
	}
	
	@Override
	public List<Sala> obtenerTodasLasSalas() {
		return repositorioSalaDao.obtenerTodasLasSalas();
	}

	@Override
	public Sala buscarSalaPorId(Long id) {
		return repositorioSalaDao.buscarSalaPorId(id);
	}

	@Override
	public void guardarSala(Sala sala) {
		repositorioSalaDao.guardarSala(sala);
	}
	
}
