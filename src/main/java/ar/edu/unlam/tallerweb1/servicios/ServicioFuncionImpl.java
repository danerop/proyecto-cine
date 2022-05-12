package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCine;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFuncion;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion{

	private RepositorioFuncion repositorioFuncionDao;
	
	@Autowired
	public ServicioFuncionImpl(RepositorioFuncion repositorioFuncionDao){
		this.repositorioFuncionDao = repositorioFuncionDao;
	}
	
	@Override
	public Funcion buscarFuncion(Long id) {
		return repositorioFuncionDao.buscarFuncionPorId(id);
	}

	@Override
	public void guardarFuncion(Funcion funcion) {
		repositorioFuncionDao.guardarFuncion(funcion);
	}

	@Override
	public List<Funcion> obtenerTodasLasFunciones() {
		return repositorioFuncionDao.obtenerTodasLasFunciones();
	}


	@Override
	public List<Funcion> obtenerFuncionesPorPeliculaYCine(Long idCine, Long idPelicula) {
		
		return repositorioFuncionDao.obtenerFuncionesPorCineYPelicula(idCine, idPelicula);
	}

	@Override
	public List<Funcion> obtenerFuncionesPorCine(Long idCine) {
		return repositorioFuncionDao.obtenerFuncionesPorCine(idCine);
	}

	@Override
	public List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula) {
		return repositorioFuncionDao.obtenerFuncionesPorPelicula(idPelicula);
	}

	@Override
	public List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula) {
		return repositorioFuncionDao.obtenerCinesDisponiblesParaFunciones(idPelicula);
	}

	@Override
	public List<Funcion> obtenerFuncionesPorCineFechaHoraYPelicula(Long idCine, Long idPelicula, Date fechaHora,
			String hora) {
		return repositorioFuncionDao.obtenerFuncionesPorCineFechaHoraYPelicula(idCine, idPelicula, fechaHora, hora);
	}

	@Override
	public Funcion obtenerFuncionesPorCineFechaHoraPeliculaYSala(Long idCine, Long idPelicula, Date fechaHora,
			String hora, Long salaid) {
		
		return repositorioFuncionDao.obtenerFuncionesPorCineFechaHoraPeliculaYSala(idCine, idPelicula, fechaHora, hora, salaid);
	}

	

	



	


}