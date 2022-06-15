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
		List<Funcion> funciones = repositorioFuncionDao.obtenerTodasLasFunciones();		
		return funciones;
	}
	@Override
	public List<Funcion> obtenerFuncionesPorCine(Long idCine) {
		return repositorioFuncionDao.obtenerFuncionesPorCine(idCine);
	}

	@Override
	public List<Funcion> obtenerFuncionesPorPelicula(Long idPelicula) {
		List<Funcion> funciones = repositorioFuncionDao.obtenerFuncionesPorPelicula(idPelicula);		
		return funciones;
	}

	@Override
	public List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula) {
		return repositorioFuncionDao.obtenerCinesDisponiblesParaFunciones(idPelicula);
	}

	@Override
	public Funcion obtenerFuncionesPorCineFechaHoraSalaYPelicula(Long idCine, Long idPelicula, String fechaHora,
			String hora, Long idSala) {
		Date temp=null;
		if (fechaHora!=null) {
			temp=Date.valueOf(fechaHora);
	
		}

		Funcion funcionzz=repositorioFuncionDao.obtenerFuncionesPorCineFechaHoraSalaYPelicula(idCine, idPelicula, temp, hora, idSala);
//		if (funcionzz==null) {
//			throw new NullPointerException("Funcion no encontrada");
//		}
		return funcionzz;
	}




	

	



	


}
