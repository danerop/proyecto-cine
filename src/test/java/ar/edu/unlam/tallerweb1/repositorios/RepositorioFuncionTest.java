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
	private RepositorioFuncion repofuncion;
	
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
		
		repofuncion.guardarFuncion(funcion);
		
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
			repofuncion.guardarFuncion(iter.next());
		}
		
		assertEquals(listaFunciones, repofuncion.obtenerTodasLasFunciones());
	}
	
	@Test
	@Transactional @Rollback
	public void queSeObtenganFuncionesAsociadasAUnaPelicula() {			
		Pelicula peli = new Pelicula();
		session().save(peli);
		Funcion funcion=new Funcion();
		Funcion funcion2=new Funcion();
		Funcion funcion3=new Funcion();
		funcion.setPelicula(peli);
		funcion3.setPelicula(peli);
		
		session().save(funcion);
		session().save(funcion2);
		session().save(funcion3);
		
		List<Funcion> listaesperada=repofuncion.obtenerFuncionesPorPelicula(peli.getId());
		int cantidadEsperada= 2;
		assertNotNull(listaesperada);
		assertEquals(cantidadEsperada, listaesperada.size());
		assertTrue(listaesperada.contains(funcion));
		assertTrue(listaesperada.contains(funcion3));
		assertFalse(listaesperada.contains(funcion2));
	}
	@Test
	@Transactional @Rollback
	public void queSeObtenganCinesDisponiblesPorPelicula() {			
		Pelicula peli = new Pelicula();
		session().save(peli);
		Cine cine1= new Cine();
		Cine cine2= new Cine();
		Cine cine3= new Cine();
		session().save(cine1);
		session().save(cine2);
		session().save(cine3);
		Funcion funcion=new Funcion();
		Funcion funcion2=new Funcion();
		Funcion funcion3=new Funcion();
		funcion.setCine(cine2);
		funcion2.setCine(cine2);
		funcion3.setCine(cine1);
		funcion.setPelicula(peli);
		funcion2.setPelicula(peli);
		funcion3.setPelicula(peli);
		
		session().save(funcion);
		session().save(funcion2);
		session().save(funcion3);
		
		List<Cine> listaesperada= repofuncion.obtenerCinesDisponiblesParaFunciones(peli.getId());
		int cantidadEsperada= 2;
		assertNotNull(listaesperada);
		assertEquals(cantidadEsperada, listaesperada.size());
		assertTrue(listaesperada.contains(cine1));
		assertTrue(listaesperada.contains(cine2));
	}
	@Test
	@Transactional @Rollback
	public void queSeObtengaUnaFuncionUnicaPorCineFechaHoraSalaYPelicula() {			
		Cine cine=new Cine();
		Pelicula peli=new Pelicula();
		Sala sala=new Sala();
		session().save(peli);
		session().save(sala);
		session().save(cine);
		Funcion funcion=new Funcion();
		Funcion funcion2=new Funcion();
		funcion.setCine(cine);
		funcion.setFechaHora(Date.valueOf("2022-06-17"));
		funcion.setHora("18:00");
		funcion.setPelicula(peli);
		funcion.setSala(sala);
		
		session().save(funcion);
		session().save(funcion2);
		
		Funcion funcionEsperada=repofuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(cine.getId(), peli.getId(), funcion.getFechaHora(), funcion.getHora(), sala.getId());
		assertNotNull(funcionEsperada);
		assertEquals(funcionEsperada, funcion);

	}

}
