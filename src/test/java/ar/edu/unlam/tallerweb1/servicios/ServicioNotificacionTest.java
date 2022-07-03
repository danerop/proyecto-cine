package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacionUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

public class ServicioNotificacionTest {
	
	private RepositorioNotificacion repoNotificacion=mock(RepositorioNotificacion.class);
	private RepositorioNotificacionUsuario repoNotificacionUsuario=mock(RepositorioNotificacionUsuario.class);
	private RepositorioUsuario repoUsuario=mock(RepositorioUsuario.class);
	private ServicioNotificacion servicioNotificacion= new ServicioNotificacionImpl(repoNotificacion, repoNotificacionUsuario, repoUsuario);

	@Test
	@Transactional @Rollback
	public void queSePuedanobtenerTodasLasNotificaciones() {
		
		
		servicioNotificacion.obtenerTodasLasNotificaciones();
		
		verify(repoNotificacion, times(1)).obtenerTodasLasNotificaciones();
	}	
	@Test
	@Transactional @Rollback
	public void queSePuedanobtenerListaDeUsuariosActivos() {
		
		
		servicioNotificacion.obtenerListaUsuariosActivos();
		
		verify(repoUsuario, times(1)).obtenerUsuariosActivos();
	}	
	@Test
	@Transactional @Rollback
	public void queSePuedanobtenerNotificacionesDeUsuario() {
		Usuario user=new Usuario();
		
		servicioNotificacion.obtenerNotificacionesDeUsuario(user);
		
		verify(repoNotificacionUsuario, times(1)).obtenerNotificacionesDeUnUsuario(user);
	}	
	@Test
	@Transactional @Rollback
	public void queSePuedaRegistrarNotificacion() {
		Notificacion notificacion= new Notificacion();
		
		servicioNotificacion.registrarNotificacion(notificacion);
		
		verify(repoNotificacion, times(1)).registrarNotificacion(notificacion);;
	}	
	@Test
	@Transactional @Rollback
	public void queSeAsocieNotificacionAUsuarios() {
		Notificacion notificacion= new Notificacion();
		Usuario user=new Usuario();
		Usuario user2=new Usuario();
		Usuario user3=new Usuario();
		user.setId(1l);
		user2.setId(2l);
		user3.setId(3l);
		List<Long> idUsuarios=new ArrayList<Long>();
		idUsuarios.add(user.getId());
		idUsuarios.add(user2.getId());
		idUsuarios.add(user3.getId());
		idUsuarios.add(10l);
		
		when(repoUsuario.buscarUsuarioPorId(1l)).thenReturn(user);
		when(repoUsuario.buscarUsuarioPorId(2l)).thenReturn(user2);
		when(repoUsuario.buscarUsuarioPorId(3l)).thenReturn(user3);
		when(repoUsuario.buscarUsuarioPorId(10l)).thenReturn(null);
		
		servicioNotificacion.asociarNotificacionAUsuarios(notificacion, idUsuarios);;
		
		
		verify(repoUsuario, times(4)).buscarUsuarioPorId(Mockito.anyLong());
		verify(repoNotificacionUsuario, times(3)).crearRegistroNotificacionUsuario(Mockito.any(NotificacionUsuario.class));
	}	
	
}
