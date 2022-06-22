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

public class RepositorioNotificacionTest extends SpringTest{
	@Autowired
	RepositorioNotificacion repoNoti;

	@Test
	@Transactional @Rollback
	public void queSePuedaBuscarBoletoPorId() {			
		Notificacion noti=new Notificacion();
		Notificacion noti2=new Notificacion();
		Notificacion noti3=new Notificacion();
		session().save(noti);
		session().save(noti2);

		
		List<Notificacion> listaEsperada = repoNoti.obtenerTodasLasNotificaciones();
		
		assertNotNull(listaEsperada);
		assertEquals(2, listaEsperada.size());
		assertTrue(listaEsperada.contains(noti));
		assertTrue(listaEsperada.contains(noti));
		assertFalse(listaEsperada.contains(noti3));
		}
	@Test
	@Transactional @Rollback
	public void queSeRegistreNotificacion() {			
		Notificacion noti=new Notificacion();
		repoNoti.registrarNotificacion(noti);
		
		assertNotNull(noti.getId());
		}
}
