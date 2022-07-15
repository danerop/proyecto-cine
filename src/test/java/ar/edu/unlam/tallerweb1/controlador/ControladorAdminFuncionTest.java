package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorAdminFuncion;
import ar.edu.unlam.tallerweb1.controladores.DatosFuncion;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFuncionCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioButacaFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

public class ControladorAdminFuncionTest {

	private ServicioCine servicioCine = mock(ServicioCine.class);
	private ServicioSala servicioSala = mock(ServicioSala.class);
	private ServicioButaca servicioButaca = mock(ServicioButaca.class);
	private ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private ServicioButacaFuncion servicioButacaFuncion = mock(ServicioButacaFuncion.class);
	private ServicioNotificacion servicioNotificacion = mock(ServicioNotificacion.class);
	
	private ControladorAdminFuncion controladorAdminFuncion = new ControladorAdminFuncion(servicioCine, servicioSala, servicioButaca,
			servicioPelicula, servicioFuncion, servicioButacaFuncion, servicioNotificacion);
	
	@Test
	public void sePuedeAccederALaPaginaSiElUsuarioEsAdmin() {
		ModelAndView mav = controladorAdminFuncion.irAAdminCargarFuncion(usuarioDeRol("admin"));
		assertEquals(mav.getViewName(), "admin-funciones");
	}
	
	@Test
	public void noSePuedeAccederALaPaginaSiElUsuarioNoEsAdmin() {
		ModelAndView mav = controladorAdminFuncion.irAAdminCargarFuncion(usuarioDeRol("usuario"));
		assertEquals(mav.getViewName(), "redirect:/inicio");
	}
	
	@Test
	public void registroExitosoDeFuncion() {
		DatosFuncion datosFuncion = new DatosFuncion();
		
		datosFuncion.setIdCine(1l);
		datosFuncion.setIdPelicula(1l);
		datosFuncion.setIdSala(1l);
		datosFuncion.setFechaHora("2022-07-01");
		datosFuncion.setHora("23:00");
		datosFuncion.setPrecioMayor(1100.0f);
		datosFuncion.setPrecioMenor(500.0f);
		
		Sala salaSeleccionada = new Sala();
		when(servicioSala.buscarSalaPorId(datosFuncion.getIdSala())).thenReturn(new Sala());
		when(servicioButaca.cantidadDeButacasEnSala(salaSeleccionada.getId())).thenReturn(200);
		
		ModelAndView mav = controladorAdminFuncion.agregarNuevaFuncion(datosFuncion, usuarioDeRol("admin"));
		
		assertNotNull(mav.getModelMap().get("msgExito"));
	}
	
	@Test
	public void registroFallidoDeFuncionPorCamposObligatoriosVacios() {
		DatosFuncion datosFuncion = new DatosFuncion();
		
		datosFuncion.setIdCine(1l);
		datosFuncion.setFechaHora("2022-07-01");
		datosFuncion.setPrecioMenor(500.0f);
		
		Sala salaSeleccionada = new Sala();
		when(servicioSala.buscarSalaPorId(datosFuncion.getIdSala())).thenReturn(new Sala());
		when(servicioButaca.cantidadDeButacasEnSala(salaSeleccionada.getId())).thenReturn(200);
		
		doThrow(ExceptionFuncionCamposVacios.class)
			.when(servicioFuncion).guardarFuncion(any());
		
		ModelAndView mav = controladorAdminFuncion.agregarNuevaFuncion(datosFuncion, usuarioDeRol("admin"));
		
		assertNull(mav.getModelMap().get("msgExito"));
	}
	
	public HttpServletRequest usuarioDeRol(String rol) {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol(rol);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		sessionmock.setAttribute("usuario", user);
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		
		return mockRequest;
	}
}
