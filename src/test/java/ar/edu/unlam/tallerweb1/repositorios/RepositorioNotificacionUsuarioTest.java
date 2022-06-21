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

public class RepositorioNotificacionUsuarioTest extends SpringTest{
	@Autowired
	RepositorioNotificacionUsuario repoNotiUsuario;

	@Test
	@Transactional @Rollback
	public void queSePuedaCrearRegistroUsuarioNotificacion() {			
		NotificacionUsuario notiusuario=new NotificacionUsuario();
		repoNotiUsuario.crearRegistroNotificacionUsuario(notiusuario);

		assertNotNull(notiusuario.getId());
		}
	@Test
	@Transactional @Rollback
	public void queSePuedaObtenerTodosLosRegistros() {			
		NotificacionUsuario notiusuario=new NotificacionUsuario();
		NotificacionUsuario notiusuario2=new NotificacionUsuario();
		NotificacionUsuario notiusuario3=new NotificacionUsuario();
		session().save(notiusuario);
		session().save(notiusuario2);

		List<NotificacionUsuario> listaEsperada = repoNotiUsuario.obtenerTodosLosRegistros();
		
		assertNotNull(listaEsperada);
		assertEquals(2, listaEsperada.size());
		assertTrue(listaEsperada.contains(notiusuario));
		assertTrue(listaEsperada.contains(notiusuario2));
		assertFalse(listaEsperada.contains(notiusuario3));
		}
	@Test
	@Transactional @Rollback
	public void queSePuedaObtenerNotificacionesDeUnUsuario() {	
		Usuario user=new Usuario();
		Usuario user2=new Usuario();
		session().save(user);
		session().save(user2);
		Notificacion noti=new Notificacion();
		Notificacion noti2=new Notificacion();
		Notificacion noti3=new Notificacion();
		session().save(noti);
		session().save(noti2);
		session().save(noti3);
		NotificacionUsuario notiusuario=new NotificacionUsuario();
		NotificacionUsuario notiusuario2=new NotificacionUsuario();
		NotificacionUsuario notiusuario3=new NotificacionUsuario();
		notiusuario.setNotificacion(noti);
		notiusuario.setUsuario(user);
		notiusuario2.setNotificacion(noti2);
		notiusuario2.setUsuario(user);
		notiusuario3.setNotificacion(noti3);
		notiusuario3.setUsuario(user2);
		session().save(notiusuario);
		session().save(notiusuario2);
		session().save(notiusuario3);

		List<NotificacionUsuario> listaEsperada = repoNotiUsuario.obtenerNotificacionesDeUnUsuario(user);
		
		assertNotNull(listaEsperada);
		assertEquals(2, listaEsperada.size());
		assertTrue(listaEsperada.contains(notiusuario));
		assertTrue(listaEsperada.contains(notiusuario2));
		assertFalse(listaEsperada.contains(notiusuario3));
		}
	@Test
	@Transactional @Rollback
	public void queSePuedaObtenerRegistrosPorNotificacion() {	
		Usuario user=new Usuario();
		Usuario user2=new Usuario();
		Usuario user3=new Usuario();
		session().save(user);
		session().save(user2);
		session().save(user3);
		Notificacion noti=new Notificacion();
		Notificacion noti2=new Notificacion();
		session().save(noti);
		session().save(noti2);
		NotificacionUsuario notiusuario=new NotificacionUsuario();
		NotificacionUsuario notiusuario2=new NotificacionUsuario();
		NotificacionUsuario notiusuario3=new NotificacionUsuario();
		notiusuario.setNotificacion(noti);
		notiusuario.setUsuario(user);
		notiusuario2.setNotificacion(noti);
		notiusuario2.setUsuario(user2);
		notiusuario3.setNotificacion(noti2);
		notiusuario3.setUsuario(user3);
		session().save(notiusuario);
		session().save(notiusuario2);
		session().save(notiusuario3);

		List<NotificacionUsuario> listaEsperada = repoNotiUsuario.obtenerRegistrosPorNotificacion(noti);
		
		assertNotNull(listaEsperada);
		assertEquals(2, listaEsperada.size());
		assertTrue(listaEsperada.contains(notiusuario));
		assertTrue(listaEsperada.contains(notiusuario2));
		assertFalse(listaEsperada.contains(notiusuario3));
		}
}
