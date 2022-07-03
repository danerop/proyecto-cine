package ar.edu.unlam.tallerweb1.controlador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorSuscripcion;
import ar.edu.unlam.tallerweb1.controladores.DatosSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

public class ControladorSuscripcionTest {
 
	private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
	private ServicioDetalleSuscripcion servicioDetalleSuscripcion = mock(ServicioDetalleSuscripcion.class);
	private ControladorSuscripcion controladorSuscripcion = new ControladorSuscripcion(servicioSuscripcion, servicioUsuario, servicioDetalleSuscripcion);
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioSuscripcion servicioSuscripcionMock;
	private ServicioDetalleSuscripcion servicioDetalleSuscripcionMock;
	
	@Before // Se ejecutan antes de cada prueba. Esto es �til cuando queremos ejecutar alg�n c�digo com�n antes de ejecutar una prueba.
	public void init() {
		usuarioMock = mock(Usuario.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioSuscripcionMock = mock(ServicioSuscripcion.class);
		controladorSuscripcion.setServicioSuscripcion(servicioSuscripcion);
		servicioDetalleSuscripcionMock = mock(ServicioDetalleSuscripcion.class);
		controladorSuscripcion.setServicioDetalleSuscripcion(servicioDetalleSuscripcion);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorSuscripcion.setServicioLogin(servicioUsuario);
	}

	@Test
	@Rollback(true)
	@Transactional
	public void SiNoExisteSesionRedirigeALogin() {
		
		// Preparacion
		when(requestMock.getSession()).thenReturn(sessionMock);
		DatosSuscripcion ds = new DatosSuscripcion();
		ds.setIdDetalleSuscripcion(2l);
		
		// Ejecucion
		ModelAndView mav = controladorSuscripcion.suscripcionElegida(requestMock, ds.getIdDetalleSuscripcion());
		
		// Validacion
		assertThat(mav.getViewName()).isEqualTo("redirect:/login");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void suscripcionExitosa() {
		// Preparacion
		Usuario user=new Usuario();
		Suscripcion sus=new Suscripcion();
		DetalleSuscripcion detalle=new DetalleSuscripcion();
		DatosSuscripcion datos = new DatosSuscripcion();
		sus.setId(1l);
		sus.setDetalleSuscripcion(detalle);
		user.setId(1l);
		user.setSuscripcion(sus);
		sessionMock.setAttribute("usuario", user);
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(Mockito.anyLong())).thenReturn(detalle);
		

		
		DetalleSuscripcion ds = new DetalleSuscripcion();
		ds.setId(2L);
		
		Suscripcion s = new Suscripcion();
		s.setDetalleSuscripcion(ds);
		s.setCantidadDeBoletosGratisRestantes(ds.getCantidadBoletosGratis());
		
		
		// Ejecuci�n 
		ModelAndView modelo = controladorSuscripcion.reciboSuscripcion(datos, requestMock);
		
		// Validaci�n
		assertThat(modelo.getViewName()).isEqualTo("suscripcion-validar");
	}
	
	/*@Test
	@Rollback(true)
	@Transactional
	public void SuscripcionGuardadaExitosamente() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioDetalleSuscripcionMock.guardarDetalleSuscripcion(detalleSuscripcion)
		
	}*/
	
	
}
