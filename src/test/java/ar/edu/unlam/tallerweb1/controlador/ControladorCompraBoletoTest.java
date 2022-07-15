package ar.edu.unlam.tallerweb1.controlador;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

import ar.edu.unlam.tallerweb1.controladores.*;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

public class ControladorCompraBoletoTest {

	private ServicioFuncion servicioFuncion = mock(ServicioFuncion.class);
	private ServicioBoleto servicioBoleto = mock(ServicioBoleto.class);
	private ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);
	private ServicioButaca servicioButaca = mock(ServicioButaca.class);
	private ServicioButacaFuncion servicioButacaFuncion = mock(ServicioButacaFuncion.class);
	private ServicioSuscripcion servicioSuscripcion = mock(ServicioSuscripcion.class);
	private ServicioUsuario servicioUsuario = mock(ServicioUsuario.class);
	private ServicioNotificacion servicioNotificacion= mock(ServicioNotificacion.class);
	private ControladorCompraBoleto controladorCompraBoleto = new ControladorCompraBoleto(servicioFuncion, servicioBoleto, servicioPelicula
			,servicioButaca, servicioButacaFuncion, servicioSuscripcion, servicioUsuario, servicioNotificacion);
	
	
	@Test
	@Transactional @Rollback
	public void queSePuedeAccederSiElUsuarioEstaLogueado() throws MPException, MPApiException {
		Usuario user = new Usuario();
		Pelicula peli=new Pelicula();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		Boleto temp=new Boleto();
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
		peli.setId(1l);
		temp.setId(1l);
		funciones.add(funcion);
		butacaFuncion.setId(1l);
		butacaFuncion.setOcupada(false);
		datos.setIdButaca(1l);
		temp.setId(1l);
		temp.setCliente(user);
		
		funcion.setPelicula(peli);
		temp.setFuncion(funcion);
		user.setTemp(temp);

		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioFuncion.obtenerFuncionesPorPelicula(Mockito.anyLong())).thenReturn(funciones);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(Mockito.any(Funcion.class), Mockito.anyLong())).thenReturn(butacaFuncion);
		when(servicioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(temp);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		when(servicioUsuario.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(user);

		ModelAndView mav =  controladorCompraBoleto.irACompra(Mockito.anyLong(), mockRequest);
		ModelAndView mav2 =  controladorCompraBoleto.irAElegirButaca(1l, mockRequest, datos);
		ModelAndView mav3 =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos, redatt);
		ModelAndView mav4 =  controladorCompraBoleto.irAConfirmar(mockRequest);
		ModelAndView mav5 =  controladorCompraBoleto.irARecibo(false, mockRequest, redatt);
		ModelAndView mav6 =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		ModelAndView mav7= controladorCompraBoleto.irASeleccionarTipoEntrada(1l, mockRequest, datos);
		
		assertEquals("compra", mav.getViewName());
		assertEquals("compra-butaca", mav2.getViewName());
		assertEquals("redirect:/comprar-confirmar?p=1", mav3.getViewName());
		assertEquals("compra-confirmacion", mav4.getViewName());
		assertEquals("redirect:/recibo?b="+null, mav5.getViewName());
		assertEquals("compra-recibocompra", mav6.getViewName());
		assertEquals("compra-tipoboleto", mav7.getViewName());
	}
	
	@Test
	@Transactional @Rollback
	public void queSeRerideccioneAHomeSiSeIntetaComprarSinLoguear() throws MPException, MPApiException {
		Usuario user = new Usuario();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		List<Funcion> funciones = new ArrayList<Funcion>();
		ButacaFuncion butacaFuncion=new ButacaFuncion();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		ModelMap modelmap=mock(ModelMap.class);
		
		user.setId(1l);
		user.setRol("nusuario");
		funcion.setId(1l);
		butaca.setId(1l);
		funciones.add(funcion);
		butacaFuncion.setId(1l);
		butacaFuncion.setOcupada(false);
		datos.setIdButaca(1l);
	
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioFuncion.obtenerFuncionesPorPelicula(Mockito.anyLong())).thenReturn(funciones);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButacaFuncion.obtenerPorButacaYFuncion(Mockito.any(Funcion.class), Mockito.anyLong())).thenReturn(butacaFuncion);
		when(servicioUsuario.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(user);


		ModelAndView mav =  controladorCompraBoleto.irACompra(Mockito.anyLong(), mockRequest);
		ModelAndView mav2 =  controladorCompraBoleto.irAElegirButaca(1l, mockRequest, datos);
		ModelAndView mav3 =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos, redatt);
		ModelAndView mav4 =  controladorCompraBoleto.irAConfirmar(mockRequest);
		ModelAndView mav5 =  controladorCompraBoleto.irARecibo(false, mockRequest, redatt);
		ModelAndView mav6 =  controladorCompraBoleto.ReciboGenerado(1l, mockRequest, modelmap);
		ModelAndView mav7 = controladorCompraBoleto.irASeleccionarTipoEntrada(1l, mockRequest, datos);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("redirect:/inicio", mav2.getViewName());
		assertEquals("redirect:/inicio", mav3.getViewName());
		assertEquals("redirect:/inicio", mav4.getViewName());
		assertEquals("redirect:/inicio", mav5.getViewName());
		assertEquals("redirect:/inicio", mav6.getViewName());
		assertEquals("redirect:/inicio", mav7.getViewName());
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
	public void queSeRerideccioneAlReciboCuandoSeGenereUnBoleto() {
		Usuario user2 = new Usuario();
		Boleto temp=new Boleto();
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		funcion.setId(1l);
		temp.setId(1l);
		funcion.setPrecioMayor((float) 11.0);
		butaca.setId(1l);
		temp.setFuncion(funcion);
		user2.setId(1l);
		user2.setRol("usuario");
		user2.setTemp(temp);
		

		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user2);
		when(servicioUsuario.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(user2);
		
		
		ModelAndView mav =  controladorCompraBoleto.irARecibo(false, mockRequest, redatt);
		
		assertEquals("redirect:/recibo?b="+1, mav.getViewName());
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
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		doThrow(ExceptionFuncionNoEncontrada.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));

		ModelAndView mav =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos, redatt);
		
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
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		doThrow(ExceptionDatosBoletoDiferentesARegistroButacaFuncion.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));

		ModelAndView mav =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos, redatt);
		
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
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		doThrow(ExceptionButacaYaOcupada.class).when(servicioBoleto).guardarBoleto(Mockito.any(Boleto.class), Mockito.any(ButacaFuncion.class));


		ModelAndView mav =  controladorCompraBoleto.irAMetodoDePago(1l, mockRequest, datos, redatt);
		
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

	@Test
	@Transactional @Rollback
	public void queSePuedaContinuarUsandoUnaEntradaGratisCuandoSeValidaLaCompra() {
		Usuario user = new Usuario();
		Boleto temp = new Boleto();
		Suscripcion sus=new Suscripcion();
		sus.setCantidadDeBoletosGratisRestantes(1l);
		temp.setId(1l);
		user.setId(1l);
		user.setRol("usuario");
		user.setSuscripcion(sus);
		user.setTemp(temp);
		
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		when(servicioUsuario.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(user);
		

		ModelAndView mav =  controladorCompraBoleto.irARecibo(false, mockRequest, redatt);
		
		assertEquals(mav.getViewName(), "redirect:/recibo?b="+temp.getId());
	}
	@Test
	@Transactional @Rollback
	public void queSeRerideccioneAInicioSiSeIntentaUsarUnaEntradaGratisYNoSePoseeNingunaCuandoSeValidaLaCompra() {
		Usuario user = new Usuario();
		Boleto temp = new Boleto();
		Suscripcion sus=new Suscripcion();
		sus.setCantidadDeBoletosGratisRestantes(0l);
		temp.setId(1l);
		user.setId(1l);
		user.setRol("usuario");
		user.setSuscripcion(sus);
		user.setTemp(temp);
		
		
		DatosCompraBoleto datos=new DatosCompraBoleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		butaca.setId(1l);
		
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession sessionmock=mock(HttpSession.class);
		RedirectAttributes redatt=mock(RedirectAttributes.class);
		
		when(mockRequest.getSession()).thenReturn(sessionmock);
		when(mockRequest.getSession().getAttribute("usuario")).thenReturn(user);
		when(servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyString()
				, Mockito.anyString(), Mockito.anyLong())).thenReturn(funcion);
		when(servicioButaca.obtenerButaca(Mockito.anyLong())).thenReturn(butaca);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Funcion.class), Mockito.anyFloat(), Mockito.any(Usuario.class))).thenReturn(true);
		when(servicioBoleto.validarPrecioDeFuncionDelBoleto(Mockito.any(Boleto.class), Mockito.anyFloat())).thenReturn(true);
		when(servicioUsuario.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(user);
		

		ModelAndView mav =  controladorCompraBoleto.irARecibo(true, mockRequest, redatt);
		
		assertEquals("redirect:/inicio", mav.getViewName());
		assertEquals("No te quedan entradas gratis por usar", mav.getModel().get("msg"));
	}

}

