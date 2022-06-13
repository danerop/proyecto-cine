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
	public ModelAndView agregarNuevaSuscripcion( @ModelAttribute("datosSuscripcion") DetalleSuscripcion datos) {
		
		ModelMap model = new ModelMap();
		DetalleSuscripcion nuevaSuscripcion = new DetalleSuscripcion();
		
		nuevaSuscripcion.setTipo(datos.getTipo());
		nuevaSuscripcion.setDescuentoEnBoletos(datos.getDescuentoEnBoletos());
		nuevaSuscripcion.setCantidadBoletosGratis(datos.getCantidadBoletosGratis());
		nuevaSuscripcion.setCuota(datos.getCuota());
		
		servicioDetalleSuscripcion.guardarDetalleSuscripcion(nuevaSuscripcion);
		
		model.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("mens", "Suscripción guardada con exito");
		return new ModelAndView("admin-suscripciones", model);
	}
}
