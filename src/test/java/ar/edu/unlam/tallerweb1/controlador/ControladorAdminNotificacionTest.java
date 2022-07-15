package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAdminNotificacion;
import ar.edu.unlam.tallerweb1.controladores.DatosNotificacion;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

public class ControladorAdminNotificacionTest extends SpringTest{

	private ServicioNotificacion servicioNoti = mock(ServicioNotificacion.class);
	private ControladorAdminNotificacion controladorAdminNoti = new ControladorAdminNotificacion(servicioNoti);

	@Test
	@Transactional @Rollback
	public void sePuedeAccederALaPaginaSiElUsuarioEsAdmin() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("admin");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
	
	
		DatosNotificacion datosNotificacion = new DatosNotificacion();
		List<Long> listaIdUsuarios = new ArrayList<Long>();
		listaIdUsuarios.add(user.getId());
		datosNotificacion.setIdUsuario(listaIdUsuarios);
		datosNotificacion.setTexto("texto titulo");
		datosNotificacion.setTitulo("titulo test");
		ModelAndView mav = controladorAdminNoti.irAAdminCargarNotificaciones(mockRequest);
		ModelAndView mav2 = controladorAdminNoti.agregarNuevaNotificacion(datosNotificacion, mockRequest);
		
		assertEquals("admin-notificaciones", mav.getViewName());
		assertEquals("admin-notificaciones", mav2.getViewName());
	}
	@Test
	@Transactional @Rollback
	public void seRedirijaAInicioSiElUsuarioNoEsAdmin() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("usuario");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
	
		
		ModelAndView mav = controladorAdminNoti.irAAdminCargarNotificaciones(mockRequest);
		
		assertEquals(mav.getViewName(), "redirect:/inicio");
	}
	
}
