package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

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
		try {
				temp=Date.valueOf(fechaHora);
			}catch (IllegalArgumentException e){
				temp=Date.valueOf("0000-01-01");
			}
			 finally {
					Funcion funcionzz=repositorioFuncionDao.obtenerFuncionesPorCineFechaHoraSalaYPelicula(idCine, idPelicula, temp, hora, idSala);
					return funcionzz;
			}
			
	
		


	}




	

	



	


}
