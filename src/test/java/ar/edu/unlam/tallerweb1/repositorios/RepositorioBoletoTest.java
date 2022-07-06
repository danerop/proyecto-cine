package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class RepositorioBoletoTest  extends SpringTest{
	
	@Autowired
	RepositorioBoleto repoBoleto;

	@Test
	@Transactional @Rollback
	public void queSePuedaBuscarBoletoPorId() {			
		Boleto boleto=new Boleto();
		Boleto boleto2=new Boleto();
		session().save(boleto);
		session().save(boleto2);
		
		Boleto boletoEsperado= repoBoleto.buscarBoleto(boleto.getId());
		
		assertNotNull(boletoEsperado);
		assertEquals(boletoEsperado, boleto);
	}
	@Test
	@Transactional @Rollback
	public void queSePuedaGuardarUnBoleto() {			
		Boleto boleto=new Boleto();
		
		repoBoleto.guardarBoleto(boleto);
		
		assertNotNull(boleto.getId());
	}

	@Test
	@Transactional @Rollback
	public void queSePuedanObtenerFuncionesDeLosBoletosDeUnUsuario() {
		
		Usuario user = new Usuario();
		Long idUsuario = (Long) session().save(user);
		
		Pelicula pelicula = new Pelicula();
		Long idPelicula = (Long) session().save(pelicula);
		
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		Long idFuncion = (Long) session().save(funcion);
		
		Boleto boleto = new Boleto();
		boleto.setCliente(user);
		boleto.setFuncion(funcion);
		Long idBoleto = (Long) session().save(boleto);
		
		List<Funcion> funciones = repoBoleto.obtenerFuncionesCompradasPorUsuario(user);
		
		System.out.println(idFuncion + " - " + funciones.size());
		
		assertEquals(funciones.size(), 1);
	}
	
	@Test
	@Transactional @Rollback
	public void queSePuedanObtenerPeliculasDeLasFuncionesDeLosBoletosDeUnUsuario() {
		
		Usuario user = new Usuario();
		Long idUsuario = (Long) session().save(user);
		
		Pelicula pelicula1 = new Pelicula();
		Long idPelicula1 = (Long) session().save(pelicula1);
		Pelicula pelicula2 = new Pelicula();
		Long idPelicula2 = (Long) session().save(pelicula2);
		
		Funcion funcion1 = new Funcion();
		funcion1.setPelicula(pelicula1);
		Long idFuncion1 = (Long) session().save(funcion1);
		Funcion funcion2 = new Funcion();
		funcion2.setPelicula(pelicula2);
		Long idFuncion2 = (Long) session().save(funcion2);
		
		Boleto boleto1 = new Boleto();
		boleto1.setCliente(user);
		boleto1.setFuncion(funcion1);
		Long idBoleto1 = (Long) session().save(boleto1);
		Boleto boleto2 = new Boleto();
		boleto2.setCliente(user);
		boleto2.setFuncion(funcion2);
		Long idBoleto2 = (Long) session().save(boleto2);
		
		List<Pelicula> peliculas = repoBoleto.obtenerPeliculasDeFuncionesCompradasPorUsuario(user);
		
		System.out.println(idPelicula1 + " - " + peliculas.size());
		
		assertEquals(peliculas.get(0).getId(), idPelicula1);
		assertTrue(peliculas.contains(pelicula1));
	}

}
