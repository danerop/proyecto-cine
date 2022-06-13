package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorAdminCine;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

public class ControladorAdminCineTest {
	
	private ServicioCine servicioCine = mock(ServicioCine.class);
	private ControladorAdminCine controladorAdminCine = new ControladorAdminCine(servicioCine);
	
//	/* test comentado por no poder solucionar problema con httpServletRequest, nullpointerexception...
	@Test
	public void sePuedeAccederALaPaginaSiElUsuarioEsAdmin() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("admin");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		sessionmock.setAttribute("usuario", user);
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
	
		
		ModelAndView mav = controladorAdminCine.irAAdminCargarCine(mockRequest);
		
		assertEquals(mav.getViewName(), "admin-cines");
	}
//	*/
	
	@Test
	public void registroExitosoDeCine() {
		Cine cineNuevo = new Cine();
		//estos datos están para dar a entender la funcionalidad del test (como los ejemplos de la clase)
		//pero no se guardan ni se usan en el test, ya que el servicio es un mock y no hace nada.
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setDireccion("direccion");
		cineNuevo.setTelefono("telefono");
		cineNuevo.setLatitud(-30.0);
		cineNuevo.setLongitud(-60.0);
		
		ModelAndView mav = controladorAdminCine.agregarNuevoCine(cineNuevo);
		
		//busca si el modelmap tiene el msgExito, si lo tiene entonces se guardó el cine
		assertNotNull(mav.getModelMap().get("msgExito"));
	}
	
	@Test
	public void registroFallidoDeCinePorFaltaDeDatos(){
		Cine cineNuevo = new Cine();
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setLongitud(-60.0);
		
		doThrow(ExceptionCine.class)
			.when(servicioCine)
			.guardarCine(cineNuevo);
		
		ModelAndView mav = controladorAdminCine.agregarNuevoCine(cineNuevo);
		
		assertNull(mav.getModelMap().get("msgExito"));
	}

}
