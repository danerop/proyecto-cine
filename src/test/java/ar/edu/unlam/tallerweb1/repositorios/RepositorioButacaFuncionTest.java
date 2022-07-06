package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.*; 

public class RepositorioButacaFuncionTest extends SpringTest{

	@Autowired
	RepositorioButacaFuncion repoButacaFuncion;
	
	@Test
	@Transactional @Rollback
	public void queSeObtenganButacasAsociadasAFuncion() {			
		Funcion funcion=new Funcion();
		Funcion funcion2=new Funcion();
		session().save(funcion);
		session().save(funcion2);
		Butaca butaca=new Butaca();
		Butaca butaca2=new Butaca();
		Butaca butaca3=new Butaca();
		session().save(butaca);
		session().save(butaca2);
		session().save(butaca3);
		
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		butacaFuncion.setFuncion(funcion);
		butacaFuncion.setButaca(butaca);
		ButacaFuncion butacaFuncion2=new ButacaFuncion();
		butacaFuncion2.setFuncion(funcion);
		butacaFuncion2.setButaca(butaca2);
		ButacaFuncion butacaFuncion3=new ButacaFuncion();
		butacaFuncion3.setFuncion(funcion2);
		butacaFuncion3.setButaca(butaca3);
		session().save(butacaFuncion);
		session().save(butacaFuncion2);
		session().save(butacaFuncion3);
		
		List<ButacaFuncion> butacasEsperadas=repoButacaFuncion.obtenerButacasPorFuncion(funcion);
		
		assertNotNull(butacasEsperadas);
		assertTrue(butacasEsperadas.contains(butacaFuncion));
		assertTrue(butacasEsperadas.contains(butacaFuncion2));
		assertFalse(butacasEsperadas.contains(butacaFuncion3));
	}
	@Test
	@Transactional @Rollback
	public void queSeObtengaRegistroPorButacaYFuncion() {			
		Funcion funcion=new Funcion();
		session().save(funcion);
		Butaca butaca=new Butaca();
		Butaca butaca2=new Butaca();
		session().save(butaca);
		session().save(butaca2);
		
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		butacaFuncion.setFuncion(funcion);
		butacaFuncion.setButaca(butaca);
		ButacaFuncion butacaFuncion2=new ButacaFuncion();
		butacaFuncion2.setFuncion(funcion);
		butacaFuncion2.setButaca(butaca2);
		session().save(butacaFuncion);
		session().save(butacaFuncion2);
		
		ButacaFuncion registroEsperado= repoButacaFuncion.obtenerPorButacaYFuncion(funcion, butaca2.getId());
		
		assertNotNull(registroEsperado);
		assertEquals(registroEsperado, butacaFuncion2);
	}
	@Test
	@Transactional @Rollback
	public void queSePuedaActualizarUnRegistro() {			
		Funcion funcion=new Funcion();
		session().save(funcion);
		Butaca butaca=new Butaca();
		session().save(butaca);

		
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		butacaFuncion.setFuncion(funcion);
		butacaFuncion.setButaca(butaca);
		session().save(butacaFuncion);
		
		butacaFuncion.setOcupada(true);
		repoButacaFuncion.actualizarButacaFuncion(butacaFuncion);
		ButacaFuncion registroActualizado = repoButacaFuncion.obtenerPorButacaYFuncion(funcion, butaca.getId());
		assertTrue(registroActualizado.getOcupada());
	
	}
	
}
