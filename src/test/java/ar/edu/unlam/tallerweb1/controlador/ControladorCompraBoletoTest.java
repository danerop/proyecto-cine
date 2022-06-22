package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.controladores.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

public class ControladorCompraBoletoTest {

	private ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private ServicioBoleto servicioBoleto = mock(ServicioBoleto.class);
	private ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private ServicioButaca servicioButaca = mock(ServicioButaca.class);
	private ServicioButacaFuncion servicioButacaFuncion = mock(ServicioButacaFuncion.class);;
	private ControladorCompraBoleto controladorCompraBoleto = new ControladorCompraBoleto(servicioFuncion, servicioBoleto, servicioPelicula
			,servicioButaca, servicioButacaFuncion);
	
	
	@Test
	@Transactional @Rollback
	public void queSePuedeAccederSiElUsuarioEstaLogueado() {
		Usuario user = new Usuario();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		Boleto boleto=new Boleto();
		List<Funcion> funciones = new ArrayList<Funcion>();
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		ModelMap modelmap=mock(ModelMap.class);
		
		user.setId(1l);
		user.setRol("usuario");
		funcion.setId(1l);
		butaca.setId(1l);
		funciones.add(funcion);
		butacaFuncion.setId(1l);
		butacaFuncion.setOcupada(false);
		datos.setIdButaca(1l);
		boleto.setId(1l);
		boleto.setCliente(user);

		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioFuncion.obtenerFuncionesPorPelicula(Mockito.anyLong())).thenReturn(funciones);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(Mockito.any(Funcion.class), Mockito.anyLong())).thenReturn(butacaFuncion);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(boleto);


		ModelAndView mav =  controladorCompraBoleto.irACompra(Mockito.anyLong(), mockRequest);
		ModelAndView mav2 =  controladorCompraBoleto.irAElegirButaca(1l, mockRequest, datos);
		ModelAndView mav3 =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos);
		ModelAndView mav4 =  controladorCompraBoleto.irAConfirmar(1l, mockRequest, datos);
		ModelAndView mav5 =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		ModelAndView mav6 =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		
		assertEquals("compra", mav.getViewName());
		assertEquals("compra-butaca", mav2.getViewName());
		assertEquals("compra-metodo-pago", mav3.getViewName());
		assertEquals("compra-confirmacion", mav4.getViewName());
		assertEquals("redirect:/recibo?b="+null, mav5.getViewName());
		assertEquals("compra-recibocompra", mav6.getViewName());
	}
	@Test
	@Transactional @Rollback
	public void queSeRerideccioneAHomeSiSeIntetaComprarSinLoguear() {
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		List<Funcion> funciones = new ArrayList<Funcion>();
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		ModelMap modelmap=mock(ModelMap.class);
		
		funcion.setId(1l);
		butaca.setId(1l);
		funciones.add(funcion);
		butacaFuncion.setId(1l);
		butacaFuncion.setOcupada(false);
		datos.setIdButaca(1l);
	
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(null);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioFuncion.obtenerFuncionesPorPelicula(Mockito.anyLong())).thenReturn(funciones);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(Mockito.any(Funcion.class), Mockito.anyLong())).thenReturn(butacaFuncion);
		


		ModelAndView mav =  controladorCompraBoleto.irACompra(Mockito.anyLong(), mockRequest);
		ModelAndView mav2 =  controladorCompraBoleto.irAElegirButaca(1l, mockRequest, datos);
		ModelAndView mav3 =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos);
		ModelAndView mav4 =  controladorCompraBoleto.irAConfirmar(1l, mockRequest, datos);
		ModelAndView mav5 =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		ModelAndView mav6 =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("redirect:/inicio", mav2.getViewName());
		assertEquals("redirect:/inicio", mav3.getViewName());
		assertEquals("redirect:/inicio", mav4.getViewName());
		assertEquals("redirect:/inicio", mav5.getViewName());
		assertEquals("redirect:/inicio", mav6.getViewName());
	}
	@Test
	@Transactional @Rollback
	public void queSeRerideccioneACompraSiLaFuncionElegidaNoExiste() {
		Usuario user = new Usuario();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		user.setId(1l);
		user.setRol("usuario");

		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
	
		ModelAndView mav =  controladorCompraBoleto.irAElegirButaca(1l, mockRequest, datos);
		
		assertEquals("redirect:/compra?p=1", mav.getViewName());
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioSiEstaOcupada() {
		Usuario user = new Usuario();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Funcion funcion=new Funcion();	
		
		user.setId(1l);
		user.setRol("usuario");
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButacaFuncion.isButacaOcupada(Mockito.any(Funcion.class), Mockito.anyLong())).thenReturn(true);
	
		ModelAndView mav =  controladorCompraBoleto.irAConfirmar(1l, mockRequest, datos);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("La butaca elegida ya fue ocupada", mav.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeRerideccioneAlReciboCuandoSeGenereUnBoleto() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("usuario");
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		funcion.setPrecioMayor((float) 11.0);
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);

		ModelAndView mav =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		
		assertEquals(mav.getViewName(), "redirect:/recibo?b="+null);
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioSiLaFuncionSeleccionadaNoExisteCuandoSeIntetaGenerarUnBoleto() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("usuario");
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Butaca butaca=new Butaca();
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(null);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		doThrow(ExceptionFuncionNoEncontrada.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));

		ModelAndView mav =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("La función de la cual desea reservar boleto no existe", mav.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioSiLosDatosDelBoletoNoCoincidenConRegistroButacaFuncionCuandoSeIntetaGenerarUnBoleto() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("usuario");
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Butaca butaca=new Butaca();
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(null);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		doThrow(ExceptionDatosBoletoDiferentesARegistroButacaFuncion.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));

		ModelAndView mav =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("Los datos de la butaca seleccionada no corresponden a una válida", mav.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioSiLaButacaSeleccionadaEstaOcupadaCuandoSeIntentaGenerarUnBoleto() {
		Usuario user = new Usuario();
		user.setId(1l);
		user.setRol("usuario");
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Butaca butaca=new Butaca();
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(null);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		doThrow(ExceptionButacaYaOcupada.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));

		ModelAndView mav =  controladorCompraBoleto.irARecibo(1l, mockRequest, datos, redatt);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("La butaca seleccionada ya ha sido ocupada, por favor intente con otra", mav.getModel().get("msg"));
	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioCuandoElBoletoNoExiste() {
		Usuario user = new Usuario();
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		ModelMap modelmap=mock(ModelMap.class);
		
		user.setId(1l);
		user.setRol("usuario");

		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(null);


		ModelAndView mav =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("Boleto no encontrado", mav.getModel().get("msg"));

	}
	@Test
	@Transactional @Rollback
	public void queSeRedireccioneAInicioCuandoSeIntentaAccederAlBoletoDeOtroUsuario() {
		Usuario user = new Usuario();
		Usuario anotherUser = new Usuario();
		Boleto boleto=new Boleto();
		boleto.setId(1l);
		boleto.setCliente(anotherUser);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		ModelMap modelmap=mock(ModelMap.class);
		
		user.setId(1l);
		user.setRol("usuario");

		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(boleto);


		ModelAndView mav =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("No puedes acceder al recibo de otros usuarios", mav.getModel().get("msg"));

	}
	
	
}

