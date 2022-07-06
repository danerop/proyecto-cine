package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionBoletoInvalido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionBoletoYaUsado;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFechaDistinta;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecepcionista;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorRecepcionista {

	private ServicioBoleto servicioBoleto;
	private ServicioRecepcionista servicioRecepcionista;

	@Autowired
	public ControladorRecepcionista(ServicioBoleto servicioBoleto, ServicioRecepcionista servicioRecepcionista) {
		this.servicioBoleto = servicioBoleto;
		this.servicioRecepcionista= servicioRecepcionista;
	}
	
	@RequestMapping(path = "/iniciorecepcionista",method = RequestMethod.GET)
	public ModelAndView irAIniciouRecepcionista(HttpServletRequest request) {
		Usuario temp=(Usuario) request.getSession().getAttribute("usuario");
		if (request.getSession().getAttribute("usuario") != null && temp.getRol().equals("recepcionista") ) {
			ModelMap modelo = new ModelMap();
			return new ModelAndView("recepcionista", modelo);
		}
		return new ModelAndView("redirect:/inicio");

	}
	@RequestMapping(path = "/validar-boleto", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView validarBoleto(@RequestParam(value = "b") Long idBoleto,
			HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		ModelMap modelo = new ModelMap();
		modelo.put("b", idBoleto);
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
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
		modelo.put("boletoGenerado", boleto);
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
		if (user == null || !user.getRol().equals("recepcionista") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("boletoGenerado", boleto);
		return new ModelAndView("recepcionista-boletovalidado", modelo);
	}
	
}
