package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;

public class LoginTest extends SpringTest {

	@Inject
	private SessionFactory sessionFactory;

	RepositorioUsuario repoUsuario = new RepositorioUsuarioImpl();
	
	    @Test
	    @Transactional @Rollback
	    public void testBuscarUsuarioPorRol(){
	        Usuario usuario = new Usuario();
	        usuario.setId(1L);
	        usuario.setActivo(true);
	        usuario.setEmail("elian@gmail.com");
	        usuario.setPassword("1234");
	        usuario.setRol("usuario");
	        
	        sessionFactory.getCurrentSession().save(usuario);
	        
	        assertEquals("usuario", repoUsuario.buscarUsuarioPorRol(usuario));
	    }
	}
