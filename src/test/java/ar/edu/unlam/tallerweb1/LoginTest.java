package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class LoginTest extends SpringTest {

	@Autowired
	RepositorioUsuario repoUsuario;
	
	@Test
	@Transactional @Rollback
	public void testBuscarUsuario(){
	    Usuario usuario = new Usuario();
	    usuario.setId(1L);
	    usuario.setActivo(true);
	    usuario.setEmail("elian@gmail.com");
	    usuario.setPassword("1234");
	    usuario.setRol("usuario");
	        
	    session().save(usuario);
	        
	    assertEquals(usuario, repoUsuario.consultarUsuario(usuario));
    }
}
