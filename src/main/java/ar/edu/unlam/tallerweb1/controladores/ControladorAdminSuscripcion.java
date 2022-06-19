package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionBoletosGratisNoValido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionDescuentoNoValido;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;

@Controller
public class ControladorAdminSuscripcion {

	private ServicioDetalleSuscripcion servicioDetalleSuscripcion;
	
	@Autowired
	public ControladorAdminSuscripcion(ServicioDetalleSuscripcion servicioDetalleSuscripcion) {
		this.servicioDetalleSuscripcion = servicioDetalleSuscripcion;
	}
	
	@RequestMapping( path = "/admin-suscripciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSuscripcion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		modelo.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		
		return new ModelAndView("admin-suscripciones", modelo);
	}
	@RequestMapping(path = "/agregar-suscripcion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSuscripcion( @ModelAttribute("datosSuscripcion") DetalleSuscripcion datos, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		try {
			servicioDetalleSuscripcion.guardarDetalleSuscripcion(datos);
		}catch(ExceptionDetalleSuscripcionCamposVacios e) {
			model.addAttribute("datosSuscripcion", datos);
			model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-suscripciones", model);
		}catch(ExceptionDetalleSuscripcionBoletosGratisNoValido e) {
			model.addAttribute("datosSuscripcion", datos);
			model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
			model.put("msgError", "La cantidad de boletos ingresada no es valida");
			return new ModelAndView("admin-suscripciones", model);
		}catch(ExceptionDetalleSuscripcionDescuentoNoValido e) {
			model.addAttribute("datosSuscripcion", datos);
			model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
			model.put("msgError", "El descuento ingresado no es valido");
			return new ModelAndView("admin-suscripciones", model);
		}
		
		model.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("msgExito", "Suscripción guardada con exito");
		return new ModelAndView("admin-suscripciones", model);
	}
}
