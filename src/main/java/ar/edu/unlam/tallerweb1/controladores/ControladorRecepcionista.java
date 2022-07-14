package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;
import net.bytebuddy.asm.Advice.This;


@Controller
public class ControladorRecepcionista {

	private ServicioBoleto servicioBoleto;
	private ServicioRecepcionista servicioRecepcionista;

	private ServicioFuncion servicioFuncion;
	private ServicioUsuario servicioUsuario;
	private ServicioNotificacion servicioNotificacion;

	@Autowired
	public ControladorRecepcionista(ServicioBoleto servicioBoleto, ServicioRecepcionista servicioRecepcionista, 
			ServicioFuncion servicioFuncion, ServicioUsuario servicioUsuario, ServicioNotificacion servicioNotificacion) {
		this.servicioBoleto = servicioBoleto;
		this.servicioRecepcionista= servicioRecepcionista;
		this.servicioFuncion=servicioFuncion;
		this.servicioUsuario=servicioUsuario;
		this.servicioNotificacion = servicioNotificacion;

	}
	
	@RequestMapping(path = "/iniciorecepcionista",method = RequestMethod.GET)
	public ModelAndView irAIniciouRecepcionista(HttpServletRequest request) {
		Usuario temp=(Usuario) request.getSession().getAttribute("usuario");
		temp= servicioUsuario.buscarUsuarioPorId(temp.getId());
		if (request.getSession().getAttribute("usuario") != null && temp.getRol().equals("recepcionista") ) {
			ModelMap modelo = new ModelMap();
			modelo.put("usuario", temp);
			modelo.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(temp));

			return new ModelAndView("recepcionista", modelo);
		}
		return new ModelAndView("redirect:/inicio");

	}
	@RequestMapping(path = "/validar-boleto", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView validarBoleto(@RequestParam(value = "b") Long idBoleto,
			HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("b", idBoleto);
		modelo.put("boletoGenerado", null);
		
		try {
			servicioRecepcionista.ConsultarBoletoValido(boleto);
		} catch (ExceptionBoletoInvalido e) {
			modelo.put("msg", "La entrada no existe");
			return new ModelAndView("recepcionista-validarboleto", modelo);
		} catch (ExceptionFechaDistinta e) {
        	modelo.put("msg", "La entrada no es para la fecha actual");
        	modelo.put("fechadistinta", true);
			return new ModelAndView("recepcionista-validarboleto", modelo);
		} catch (ExceptionBoletoYaUsado e) {
        	modelo.put("msg", "La entrada ya fue usada");
        	modelo.put("fueusado", true);
        	return new ModelAndView("recepcionista-validarboleto", modelo);
		}
		
		if (user.getModoautomatico()) {
			if (user.getFuncionModoAutomatico()!=null && boleto.getFuncion().getId()==user.getFuncionModoAutomatico().getId()) {
				return new ModelAndView("redirect:/registrar-asistencia-boleto?b="+idBoleto);
			} else {
				modelo.put("msg", "La entrada no pertenece a la funci�n indicada en el modo autom�tico");
				return new ModelAndView("recepcionista-validarboleto", modelo);
			}
		}
		modelo.put("boletoGenerado", boleto);
		modelo.put("usuario", user);
		modelo.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("recepcionista-validarboleto", modelo);
		
//		if (boleto!=null) {
//        	modelo.put("fechadistinta", false);
//			if (!boleto.getUsado()) {
//				modelo.put("boletoGenerado", boleto);
//				if (servicioRecepcionista.validarFechaBoleto(boleto)) {
//					return new ModelAndView("recepcionista-validarboleto", modelo);
//		        }
//		        else {
//		        	modelo.put("msg", "La entrada no es para la fecha actual");
//		        	modelo.put("fechadistinta", true);
//					return new ModelAndView("recepcionista-validarboleto", modelo);
//				}
//			} else {
//	        	modelo.put("msg", "La entrada ya fue usada");
//	        	modelo.put("fueusado", true);
//	        	return new ModelAndView("recepcionista-validarboleto", modelo);
//			}
//		}
//		modelo.put("msg", "La entrada no existe");
//		return new ModelAndView("recepcionista-validarboleto", modelo);
	}
	
	@RequestMapping(path = "/registrar-asistencia-boleto", method = RequestMethod.GET)
	public ModelAndView registrarAsistenciaBoleto(@RequestParam(value = "b") Long idBoleto, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		modelo.put("b", idBoleto);
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		if (boleto!=null && boleto.getUsado()==false) {
			servicioBoleto.registrarAsistenciaBoleto(boleto);
			return new ModelAndView("redirect:/boleto-validado", modelo);
		}
		return new ModelAndView("redirect:/iniciorecepcionista");
	}
	
	@RequestMapping(path = "/boleto-validado", method = RequestMethod.GET)
	public ModelAndView irABoletoValidado(@RequestParam(value = "b") Long idBoleto, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("boletoGenerado", boleto);
		modelo.put("usuario", user);
		modelo.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("recepcionista-boletovalidado", modelo);
	}
	
	@RequestMapping(path = "/recepcionista-seleccionarfuncion", method = RequestMethod.GET)
	public ModelAndView irASeleccionarFuncion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		List<Funcion> funcionesdehoy=servicioFuncion.obtenerFuncionesFechaActual();
		
		ModelMap modelo = new ModelMap();
		modelo.put("funcionesdehoy", funcionesdehoy);
		return new ModelAndView("recepcionista-automatico", modelo);
	}
	
	@RequestMapping(path = "/cambiarmodoautomatico", method = RequestMethod.GET)
	public ModelAndView cambiarModoAutomatico(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		
		servicioUsuario.cambiarmodoautomatico(user);
		
		
		return new ModelAndView("redirect:/iniciorecepcionista");
	}
	@RequestMapping(path = "/asociarfuncionautomatica", method = RequestMethod.GET)
	public ModelAndView seleccionarFuncionModoAutomatico(HttpServletRequest request,
			@RequestParam(value = "f") Long idFuncion) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		user= servicioUsuario.buscarUsuarioPorId(user.getId());
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		Funcion funcionSeleecionada=servicioFuncion.buscarFuncion(idFuncion);
		servicioUsuario.seleccionarFuncionAutomatica(user, funcionSeleecionada);
		
		
		return new ModelAndView("redirect:/iniciorecepcionista");
	}
	
}
