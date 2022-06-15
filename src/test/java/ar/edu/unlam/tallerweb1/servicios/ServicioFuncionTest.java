package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

public class ServicioFuncionTest {
	private RepositorioFuncion repositorioFuncion = mock(RepositorioFuncion.class);
	private ServicioFuncion servicioFuncion = new ServicioFuncionImpl(repositorioFuncion);

	@Test
	@Transactional @Rollback
	public void guardarFuncion() {
		Funcion funcion = new Funcion();
		Pelicula peli=new Pelicula();
		Cine cine=new Cine();
		Sala sala=new Sala();
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
		funcion.setFechaHora(Date.valueOf("2022-12-10"));
		funcion.setHora("18:00");
		funcion.setPrecioMayor((float) 100.0);
		funcion.setPrecioMenor((float) 50.0);
		funcion.setCine(cine);
		funcion.setPelicula(peli);
		funcion.setSala(sala);
		
		servicioFuncion.guardarFuncion(funcion);
		
		verify(repositorioFuncion, times(1)).guardarFuncion(funcion);
	}
	@Test
	@Transactional @Rollback
	public void obtenerFuncionesPorPelicula() {
		Pelicula peli=new Pelicula();
		peli.setId(1l);
		
		servicioFuncion.obtenerFuncionesPorPelicula(peli.getId());
		
		verify(repositorioFuncion, times(1)).obtenerFuncionesPorPelicula(peli.getId());
	}
	@Test
	@Transactional @Rollback
	public void obtenerCinesDisponiblesParaFunciones() {
		Pelicula peli=new Pelicula();
		peli.setId(1l);
		
		servicioFuncion.obtenerCinesDisponiblesParaFunciones(peli.getId());
		
		verify(repositorioFuncion, times(1)).obtenerCinesDisponiblesParaFunciones(peli.getId());
	}
	@Test
	@Transactional @Rollback
	public void obtenerFuncionPorCineFechaHoraSalaYPelicula() {
		Funcion funcion = new Funcion();
		Pelicula peli=new Pelicula();
		Cine cine=new Cine();
		Sala sala=new Sala();
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
		funcion.setFechaHora(Date.valueOf("2022-12-10"));
		funcion.setHora("18:00");
		funcion.setPrecioMayor((float) 100.0);
		funcion.setPrecioMenor((float) 50.0);
		funcion.setCine(cine);
		funcion.setPelicula(peli);
		funcion.setSala(sala);
		cine.setId(1l);
		peli.setId(1l);
		sala.setId(1l);
		
		
		servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(cine.getId(), peli.getId(), funcion.getFechaHora().toString(), funcion.getHora(), sala.getId());
		
		verify(repositorioFuncion, times(1)).obtenerFuncionesPorCineFechaHoraSalaYPelicula(cine.getId(), peli.getId(), funcion.getFechaHora(), funcion.getHora(), sala.getId());
	}
	@Test
	@Transactional @Rollback
	public void queSeSustituyaLaFechaSiSeIntentaobtenerFuncionPorCineFechaHoraSalaYPeliculaConUnaFechaConFormatoInvalido() {
		Funcion funcion = new Funcion();
		Pelicula peli=new Pelicula();
		Cine cine=new Cine();
		Sala sala=new Sala();
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
			//fecha con fomrato invalido
		String fechaInvalida="9999-99-99";
		funcion.setHora("18:00");
		funcion.setPrecioMayor((float) 100.0);
		funcion.setPrecioMenor((float) 50.0);
		funcion.setCine(cine);
		funcion.setPelicula(peli);
		funcion.setSala(sala);
		cine.setId(1l);
		peli.setId(1l);
		sala.setId(1l);
			//Para que no traiga registros
		Date fechaSustituta=Date.valueOf("0000-01-01");
		servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(cine.getId(), peli.getId(), fechaInvalida, funcion.getHora(), sala.getId());
		
		verify(repositorioFuncion, times(1)).obtenerFuncionesPorCineFechaHoraSalaYPelicula(cine.getId(), peli.getId(), fechaSustituta, funcion.getHora(), sala.getId());
	}
}
