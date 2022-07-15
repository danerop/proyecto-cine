package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorRecepcionista;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

public class ControladorRecepcionistaTest {
	private ServicioBoleto servicioBoleto = mock(ServicioBoleto.class);
	private ServicioRecepcionista servicioRecepcionista = mock(ServicioRecepcionista.class);
	
	private ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private ServicioNotificacion servicioNotificacion = mock(ServicioNotificacion.class);
	private ControladorRecepcionista controladorRecepcionista = new ControladorRecepcionista(servicioBoleto, servicioRecepcionista, servicioFuncion, servicioUsuario, servicioNotificacion);
	
	@Test
	@Transactional @Rollback
	public void sePuedeAccederALaPaginaSiElUsuarioEsRecepcionista() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioUsuario.buscarUsuarioPorId(user.getId())).thenReturn(user);
	
		
		ModelAndView mav = controladorRecepcionista.irAIniciouRecepcionista(mockRequest);
		ModelAndView mav2 = controladorRecepcionista.validarBoleto(1l ,mockRequest);
		ModelAndView mav3 = controladorRecepcionista.registrarAsistenciaBoleto(1l ,mockRequest);
		ModelAndView mav4 = controladorRecepcionista.irABoletoValidado(1l ,mockRequest);
		
		assertEquals("recepcionista", mav.getViewName());
		assertEquals("recepcionista-validarboleto", mav2.getViewName());
		assertEquals("redirect:/iniciorecepcionista", mav3.getViewName());
		assertEquals("recepcionista-boletovalidado", mav4.getViewName());
		
	}
	@Test
	@Transactional @Rollback
	public void queSeInformeSiElBoletoAValidarNoExiste() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioUsuario.buscarUsuarioPorId(user.getId())).thenReturn(user);
		doThrow(ExceptionBoletoInvalido.class).when(servicioRecepcionista).ConsultarBoletoValido(Mockito.any(Boleto.class));
		
		ModelAndView mav2 = controladorRecepcionista.validarBoleto(1l ,mockRequest);
		
		assertEquals("recepcionista-validarboleto", mav2.getViewName());
		assertEquals("La entrada no existe", mav2.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeInformeSiElBoletoAValidarPerteneceAOtraFecha() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioUsuario.buscarUsuarioPorId(user.getId())).thenReturn(user);
		doThrow(ExceptionFechaDistinta.class).when(servicioRecepcionista).ConsultarBoletoValido(Mockito.any(Boleto.class));
		
		ModelAndView mav2 = controladorRecepcionista.validarBoleto(1l ,mockRequest);
		
		assertEquals("recepcionista-validarboleto", mav2.getViewName());
		assertEquals("La entrada no es para la fecha actual", mav2.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeInformeSiElBoletoAValidarYaFueUsado() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioUsuario.buscarUsuarioPorId(user.getId())).thenReturn(user);
		doThrow(ExceptionBoletoYaUsado.class).when(servicioRecepcionista).ConsultarBoletoValido(Mockito.any(Boleto.class));
		
		ModelAndView mav2 = controladorRecepcionista.validarBoleto(1l ,mockRequest);
		
		assertEquals("recepcionista-validarboleto", mav2.getViewName());
		assertEquals("La entrada ya fue usada", mav2.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneABoletoValidadoSiElBoletoEsValido() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		Boleto boleto = new Boleto();
		boleto.setUsado(false);
		
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioUsuario.buscarUsuarioPorId(user.getId())).thenReturn(user);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(boleto);
		
		ModelAndView mav2 = controladorRecepcionista.registrarAsistenciaBoleto(1l ,mockRequest);
		
		assertEquals("redirect:/boleto-validado", mav2.getViewName());
	}
	public void queSeRedireccioneAInicioRecepcionistaSiElBoletoYaFueUsado() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("recepcionista");
		Boleto boleto = new Boleto();
		boleto.setUsado(true);
		
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(boleto);
		
		ModelAndView mav2 = controladorRecepcionista.registrarAsistenciaBoleto(1l ,mockRequest);
		
		assertEquals("redirect:/iniciorecepcionista", mav2.getViewName());
	}
}
