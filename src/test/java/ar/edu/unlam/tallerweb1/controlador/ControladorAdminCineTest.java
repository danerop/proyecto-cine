package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorAdminCine;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCineCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

public class ControladorAdminCineTest {
	
	private ServicioCine servicioCine = mock(ServicioCine.class);
	private ControladorAdminCine controladorAdminCine = new ControladorAdminCine(servicioCine);
	
	@Test
	@Transactional @Rollback
	public void sePuedeAccederALaPaginaSiElUsuarioEsAdmin() {
		
		ModelAndView mav = controladorAdminCine.irAAdminCargarCine(usuarioDeRol("admin"));
		
		assertEquals(mav.getViewName(), "admin-cines");
	}
	
	@Test
	public void noSePuedeAccederALaPaginaSiElUsuarioNoEsAdmin() {
		
		ModelAndView mav = controladorAdminCine.irAAdminCargarCine(usuarioDeRol("usuario"));
		
		assertEquals(mav.getViewName(), "redirect:/inicio");
	}
	
	@Test
	@Transactional @Rollback
	public void registroExitosoDeCine() {
		Cine cineNuevo = new Cine();
		//estos datos están para dar a entender la funcionalidad del test (como los ejemplos de la clase)
		//pero no se guardan ni se usan en el test, ya que el servicio es un mock y no hace nada.
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setDireccion("direccion");
		cineNuevo.setTelefono("telefono");
		cineNuevo.setLatitud(-30.0);
		cineNuevo.setLongitud(-60.0);
		
		ModelAndView mav = controladorAdminCine.agregarNuevoCine(cineNuevo, usuarioDeRol("admin"));
		
		//busca si el modelmap tiene el msgExito, si lo tiene entonces se guardó el cine
		assertNotNull(mav.getModelMap().get("msgExito"));
	}
	
	@Test
	@Transactional @Rollback
	public void registroFallidoDeCinePorFaltaDeDatos(){
		Cine cineNuevo = new Cine();
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setLongitud(-60.0);
		
		doThrow(ExceptionCineCamposVacios.class)
			.when(servicioCine)
			.guardarCine(cineNuevo);
		
		ModelAndView mav = controladorAdminCine.agregarNuevoCine(cineNuevo, usuarioDeRol("admin"));
		
		assertNull(mav.getModelMap().get("msgExito"));
	}

	//metodo que devuelve el mock de HttpServletRequest ya completo, solo necesita el rol del usuario
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
