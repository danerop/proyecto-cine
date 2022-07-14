package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionBoletosGratisNoValido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionDescuentoNoValido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionDetalleSuscripcionNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;

@Controller
public class ControladorAdminSuscripcion {

	private ServicioDetalleSuscripcion servicioDetalleSuscripcion;
	private ServicioNotificacion servicioNotificacion;
	
	@Autowired
	public ControladorAdminSuscripcion(ServicioDetalleSuscripcion servicioDetalleSuscripcion, ServicioNotificacion servicioNotificacion) {
		this.servicioDetalleSuscripcion = servicioDetalleSuscripcion;
		this.servicioNotificacion = servicioNotificacion;
	}
	
	@RequestMapping( path = "/admin-suscripciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSuscripcion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		
		return new ModelAndView("admin-suscripciones", model);
	}
	
	@RequestMapping(path = "/form-suscripcion-nueva", method = RequestMethod.GET)
	public ModelAndView crearSuscripcion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", true);
		model.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		
		return new ModelAndView("admin-suscripciones-form", model);
	}
	
	@RequestMapping(path = "/registrar-suscripcion-nueva", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSuscripcion( @ModelAttribute("datosSuscripcion") DetalleSuscripcion datos, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		try {
			servicioDetalleSuscripcion.guardarDetalleSuscripcion(datos);
			
		}catch(ExceptionDetalleSuscripcionCamposVacios e) {
			model.put("elementoNuevo", true);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-suscripciones-form", model);
		}catch(ExceptionDetalleSuscripcionBoletosGratisNoValido e) {
			model.put("elementoNuevo", true);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", "La cantidad de boletos ingresada no es valida");
			return new ModelAndView("admin-suscripciones-form", model);
		}catch(ExceptionDetalleSuscripcionDescuentoNoValido e) {
			model.put("elementoNuevo", true);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", "El descuento ingresado no es valido");
			return new ModelAndView("admin-suscripciones-form", model);
		}
		
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("msgExito", "Suscripción guardada con exito");
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-suscripciones", model);
	}
	
	@RequestMapping(path = "/editar-suscripcion-vieja", method = RequestMethod.GET)
	public ModelAndView modificarSuscripcion(@RequestParam (value = "id") Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", false);
		
		DetalleSuscripcion dsAEditar = new DetalleSuscripcion();
		
		try {
			dsAEditar = servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(id);
		}catch(ExceptionDetalleSuscripcionNoEncontrada e) {
			return new ModelAndView("redirect:/admin-suscripciones");
		}
		
		model.addAttribute("datosSuscripcion", dsAEditar);
		
		return new ModelAndView("admin-suscripciones-form", model);
	}
	
	@RequestMapping(path = "/actualizar-suscripcion-vieja", method = RequestMethod.POST)
	public ModelAndView actualizarSuscripcion(@RequestParam (value = "id") Long id, @ModelAttribute("datosSuscripcion") DetalleSuscripcion datos, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		datos.setId(id);
		
		try {
			servicioDetalleSuscripcion.modificarDetalleSuscripcion(datos);
			
		}catch(ExceptionDetalleSuscripcionCamposVacios e) {
			model.put("elementoNuevo", false);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-suscripciones-form", model);
		}catch(ExceptionDetalleSuscripcionBoletosGratisNoValido e) {
			model.put("elementoNuevo", false);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", "La cantidad de boletos ingresada no es valida");
			return new ModelAndView("admin-suscripciones-form", model);
		}catch(ExceptionDetalleSuscripcionDescuentoNoValido e) {
			model.put("elementoNuevo", false);
			model.addAttribute("datosSuscripcion", datos);
			model.put("msgError", "El descuento ingresado no es valido");
			return new ModelAndView("admin-suscripciones-form", model);
		}
		
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("msgExito", "Suscripción guardada con exito");
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-suscripciones", model);
	}
}
