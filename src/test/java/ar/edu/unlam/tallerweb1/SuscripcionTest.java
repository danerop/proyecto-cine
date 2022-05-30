package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioSuscripcionImpl;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuarioImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;


public class SuscripcionTest extends SpringTest {

	@Inject
	private SessionFactory sessionFactory;

	RepositorioUsuario repoUsuario = new RepositorioUsuarioImpl();
	RepositorioSuscripcion repoSuscripcion = new RepositorioSuscripcionImpl();
	
	@Test
	@Transactional @Rollback
	public void testObtenerSuscripcionPorId() {
		
		Suscripcion sub = new Suscripcion();
    	sub.setTipo("gold");
    	sub.setId(4L);
        
    	repoSuscripcion.setSessionFactory(sessionFactory);
    	repoSuscripcion.guardarSuscripcion(sub);
        
		
        assertEquals(sub, repoSuscripcion.obtenerSuscripcionPorId(sub.getId().longValue()));
	}
	
	@Test
	@Transactional @Rollback
	public void testObtenerSuscripcionPorUsuario() {
		
		Suscripcion sub = new Suscripcion();
    	sub.setTipo("gold");
    	
    	Usuario user = new Usuario();
    	user.setEmail("asd@gmail.com");
    	user.setPassword("1234");
    	user.setActivo(true);
    	user.setId(5L);
    	
    	repoSuscripcion.setSessionFactory(sessionFactory);
    	repoSuscripcion.guardarSuscripcion(sub);
    	
    	assertNotNull(repoSuscripcion.obtenerSuscripcionPorId(sub.getId()));
    	
    	user.setSuscripcion(sub);
    	repoUsuario.setSessionFactory(sessionFactory);
    	repoUsuario.guardar(user);
    	
    	assertEquals(user, repoUsuario.buscarUsuarioPorId(user.getId()));
    	
    	assertEquals(user, repoUsuario.buscarPorSuscripcion(user.getSuscripcion().getTipo()));
    	
	}

}
