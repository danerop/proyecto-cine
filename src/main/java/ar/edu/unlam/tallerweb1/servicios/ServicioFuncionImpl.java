package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

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
		Funcion funcion = repositorioFuncionDao.buscarFuncionPorId(id);
		if(funcion == null) {
			throw new ExceptionFuncionNoEncontrada("");
		}
		return funcion;
	}

	@Override
	public void guardarFuncion(Funcion funcion) {
		String msg = "";
		
		if(funcion.getCine() == null) {
			msg = msg + "Cine no elegido <br>";
		}
		if(funcion.getSala() == null) {
			msg = msg + "Sala no elegida <br>";
		}
		if(funcion.getPelicula() == null) {
			msg = msg + "Película no elegida <br>";
		}
		if(funcion.getPrecioMayor() == null) {
			msg = msg + "Rellenar precio de adulto <br>";
		}
		if(funcion.getFechaHora() == null) {
			msg = msg + "Elegir fecha <br>";
		}
		if(funcion.getHora() == null) {
			msg = msg + "Rellenar hora (hh:mm) <br>";
		}
		if(msg != "") {
			throw new ExceptionFuncionCamposVacios(msg);
		}
		if(!Pattern.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", funcion.getHora())) {
			throw new ExceptionFuncionHoraIncorrecta();
		}
		if(funcion.getPrecioMayor()<0 || funcion.getPrecioMenor()<0) {
			throw new ExceptionFuncionPrecioIncorrecto();
		}
		
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
	public List<Funcion> obtenerFuncionesFuturasDePelicula(Long idPelicula) {
		List<Funcion> funciones = repositorioFuncionDao.obtenerFuncionesFuturasDePelicula(idPelicula);		
		return funciones;
	}
	
	@Override
	public List<Cine> obtenerCinesDisponiblesParaFunciones(Long idPelicula) {
		return repositorioFuncionDao.obtenerCinesDisponiblesParaFunciones(idPelicula);
	}

	@Override
	public List<Cine> obtenerCinesDisponiblesParaFuncionesFuturas(Long idPelicula) {
		return repositorioFuncionDao.obtenerCinesDisponiblesParaFuncionesFuturas(idPelicula);
	}
	
	@Override
	public Funcion obtenerFuncionesPorCineFechaHoraSalaYPelicula(Long idCine, Long idPelicula, String fechaHora,
			String hora, Long idSala) {
		Date temp=null;	
		try {
			temp=Date.valueOf(fechaHora);
		}catch (IllegalArgumentException e){
			temp=Date.valueOf("0000-01-01");
		}finally {
			Funcion funcionzz=repositorioFuncionDao.obtenerFuncionesPorCineFechaHoraSalaYPelicula(idCine, idPelicula, temp, hora, idSala);
			return funcionzz;
		}
	}
}
