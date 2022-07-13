package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

@Controller
public class ControladorAdminNotificacion {
	
	private ServicioNotificacion servicioNotificacion;

	
	@Autowired
	public ControladorAdminNotificacion(ServicioNotificacion servicioNotificacion) {
		this.servicioNotificacion = servicioNotificacion;
	}

	@RequestMapping( path = "/admin-notificaciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarNotificaciones(HttpServletRequest request
		
			) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("datosNotificacion", new DatosNotificacion());
		modelo.put("listaNotificaciones", servicioNotificacion.obtenerTodasLasNotificaciones());
		modelo.put("listaUsuarios", servicioNotificacion.obtenerListaUsuariosActivos());
		return new ModelAndView("admin-notificaciones", modelo);
	}
	@RequestMapping(path = "/agregar-notificacion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaNotificacion( @ModelAttribute("datosNotificacion") DatosNotificacion datosNotificacion, 
			HttpServletRequest request) {
		ModelMap model = new ModelMap();
	
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") || datosNotificacion==null ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		Notificacion nuevaNotificacion= new Notificacion();
		
		nuevaNotificacion.setTexto(datosNotificacion.getTexto());
		nuevaNotificacion.setTitulo(datosNotificacion.getTitulo());
		
		servicioNotificacion.registrarNotificacion(nuevaNotificacion);
		servicioNotificacion.asociarNotificacionAUsuarios(nuevaNotificacion, datosNotificacion.getIdUsuario());
//		como mando el msg a otro controlador?
		model.put("mens", "Notificacion generada con exito");
		return new ModelAndView("redirect:/admin-notificaciones");
	}
}
