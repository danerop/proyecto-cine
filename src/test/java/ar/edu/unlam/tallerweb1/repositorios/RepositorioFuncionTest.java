package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;

public class RepositorioFuncionTest extends SpringTest{

	@Autowired
	private RepositorioFuncion repositorioFuncion;
	
	@Test
	@Transactional @Rollback
	public void queSePuedaGuardarUnaFuncion() {
		Funcion funcion = new Funcion();
		Pelicula peli=new Pelicula();
		Cine cine=new Cine();
		Sala sala=new Sala();
		funcion.setEntradasDisponibles(10);
		funcion.setFechaHora(Date.valueOf("2022-12-10"));
		funcion.setHora("18:00");
		funcion.setPrecioMayor((float) 100.0);
		funcion.setPrecioMenor((float) 50.0);
		funcion.setCine(cine);
		funcion.setPelicula(peli);
		funcion.setSala(sala);
		
		repositorioFuncion.guardarFuncion(funcion);
		
		assertNotNull(funcion.getId());
	}

	@Test
	@Transactional @Rollback
	public void queSeDevuelvanTodasLasFuncionesRegistradas(){
		List<Funcion> listaFunciones = new ArrayList<Funcion>();
		
		listaFunciones.add(new Funcion());
		listaFunciones.add(new Funcion());
		listaFunciones.add(new Funcion());
		listaFunciones.add(new Funcion());
		listaFunciones.add(new Funcion());
		listaFunciones.add(new Funcion());
		
		Iterator<Funcion> iter = listaFunciones.iterator();
		while (iter.hasNext()) {
			repositorioFuncion.guardarFuncion(iter.next());
		}
		
		assertEquals(listaFunciones, repositorioFuncion.obtenerTodasLasFunciones());
	}
	
	@Test
	@Transactional @Rollback
	public void queSeDevuelvanTodasLasFuncionesDeUnaPelicula() {
		Pelicula peli1 = nuevaPelicula(1l, "peliculaBuscada");
		Pelicula peli2 = nuevaPelicula(2l, "ejemplo2");
		Pelicula peli3 = nuevaPelicula(3l, "ejemplo3");
		
		List<Funcion> listaFuncionesNoEsperadas = new ArrayList<Funcion>();
		List<Funcion> listaFuncionesEsperadas = new ArrayList<Funcion>();
		archivarFuncion(listaFuncionesNoEsperadas, peli3);
		archivarFuncion(listaFuncionesNoEsperadas, peli2);
		archivarFuncion(listaFuncionesEsperadas, peli1);
		archivarFuncion(listaFuncionesEsperadas, peli1);
		
		Iterator<Funcion> iter = listaFuncionesEsperadas.iterator();
		while (iter.hasNext()) {
			repositorioFuncion.guardarFuncion(iter.next());
		}
		
		System.out.println(repositorioFuncion.buscarFuncionPorId(1l));
		
		assertEquals(listaFuncionesEsperadas, repositorioFuncion.obtenerFuncionesPorPelicula(peli1.getId()));
	}
	
	private void archivarFuncion(List<Funcion> listaFunciones, Pelicula peli) {
		Funcion fun = new Funcion();
		fun.setPelicula(peli);
		listaFunciones.add(fun);
	}

	public Pelicula nuevaPelicula(Long id, String nombre) {
		Pelicula peli = new Pelicula();
		peli.setId(id);
		peli.setNombre(nombre);
		return peli;
	}
}
