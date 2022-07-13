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
		session().save(user);
		
		Pelicula pelicula = new Pelicula();
		session().save(pelicula);
		
		Funcion funcion = new Funcion();
		funcion.setPelicula(pelicula);
		session().save(funcion);
		
		Boleto boleto = new Boleto();
		boleto.setCliente(user);
		boleto.setFuncion(funcion);
		session().save(boleto);
		
		List<Funcion> funciones = repoBoleto.obtenerFuncionesCompradasPorUsuario(user);
		
		assertEquals(funciones.size(), 1);
	}
	
	@Test
	@Transactional @Rollback
	public void queSePuedanObtenerPeliculasDeLasFuncionesDeLosBoletosDeUnUsuario() {
		
		Usuario user = new Usuario();
		session().save(user);
		
		Pelicula pelicula1 = new Pelicula();
		Long idPelicula1 = (Long) session().save(pelicula1);
		Pelicula pelicula2 = new Pelicula();
		session().save(pelicula2);
		
		Funcion funcion1 = new Funcion();
		funcion1.setPelicula(pelicula1);
		session().save(funcion1);
		Funcion funcion2 = new Funcion();
		funcion2.setPelicula(pelicula2);
		session().save(funcion2);
		
		Boleto boleto1 = new Boleto();
		boleto1.setCliente(user);
		boleto1.setFuncion(funcion1);
		session().save(boleto1);
		Boleto boleto2 = new Boleto();
		boleto2.setCliente(user);
		boleto2.setFuncion(funcion2);
		session().save(boleto2);
		
		List<Pelicula> peliculas = repoBoleto.obtenerPeliculasDeFuncionesCompradasPorUsuario(user);
		
		assertEquals(peliculas.get(0).getId(), idPelicula1);
		assertTrue(peliculas.contains(pelicula1));
	}
	
	@Test
	@Transactional @Rollback
	public void queSeVeaLaCantidadDeUsuariosQueVieronUnaPelicula() {
		
		Usuario user = new Usuario();
		session().save(user);
		
		Pelicula pelicula1 = new Pelicula();
		session().save(pelicula1);
		Pelicula pelicula2 = new Pelicula();
		session().save(pelicula2);
		
		Funcion funcion1 = new Funcion();
		funcion1.setPelicula(pelicula1);
		session().save(funcion1);
		Funcion funcion2 = new Funcion();
		funcion2.setPelicula(pelicula2);
		session().save(funcion2);
		Funcion funcion3 = new Funcion();
		funcion3.setPelicula(pelicula2);
		session().save(funcion3);
		
		Boleto boleto1 = new Boleto();
		boleto1.setCliente(user);
		boleto1.setFuncion(funcion1);
		session().save(boleto1);
		Boleto boleto2 = new Boleto();
		boleto2.setCliente(user);
		boleto2.setFuncion(funcion2);
		session().save(boleto2);
		Boleto boleto3 = new Boleto();
		boleto3.setCliente(user);
		boleto3.setFuncion(funcion3);
		session().save(boleto3);
		
		Long cantUsuarios = (Long) repoBoleto.obtenerCantidadUsuariosQueVieronLaPelicula(pelicula2);
		
		assertEquals(cantUsuarios.longValue(), 1L);
	}
	
}
