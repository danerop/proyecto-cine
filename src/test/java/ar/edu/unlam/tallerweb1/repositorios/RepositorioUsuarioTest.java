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

public class RepositorioUsuarioTest extends SpringTest{
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Test
	@Transactional @Rollback
	public void queSePuedaBuscarUsuarioPorId() {
		Usuario user=new Usuario();
		Usuario user2=new Usuario();
		session().save(user);
		session().save(user2);

		Usuario usuarioEsperado=repositorioUsuario.buscarUsuarioPorId(user.getId());
		
		assertNotNull(usuarioEsperado);
		assertEquals(user, usuarioEsperado);
	}
	@Test
	@Transactional @Rollback
	public void queSePuedaObtenerTodosLosUsuariosActivos() {
		Usuario user=new Usuario();
		Usuario user2=new Usuario();
		Usuario user3=new Usuario();
		user.setActivo(true);
		user2.setActivo(true);
		session().save(user);
		session().save(user2);

		List<Usuario> listaEsperada=repositorioUsuario.obtenerUsuariosActivos();
		
		assertNotNull(listaEsperada);
		assertTrue(listaEsperada.contains(user));
		assertTrue(listaEsperada.contains(user2));
		assertFalse(listaEsperada.contains(user3));
	}

}
