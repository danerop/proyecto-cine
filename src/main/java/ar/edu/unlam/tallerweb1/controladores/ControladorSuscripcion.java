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
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

@Controller
public class ControladorSuscripcion {

	private ServicioSuscripcion servicioSuscripcion;
	private ServicioDetalleSuscripcion servicioDetalleSuscripcion;

	@Autowired
	public ControladorSuscripcion(ServicioSuscripcion servicioSuscripcion, ServicioDetalleSuscripcion servicioDetalleSuscripcion) {
		this.servicioSuscripcion = servicioSuscripcion;
		this.servicioDetalleSuscripcion = servicioDetalleSuscripcion;
	}

	@RequestMapping(path = "/suscripcion", method = RequestMethod.GET)
	public ModelAndView irASuscripcion() {
		
		ModelMap model = new ModelMap();
		
		model.addAttribute(new DatosBuscar());
		model.put("listaDeDetallesSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());

		return new ModelAndView("suscripcion", model);
	}

	@RequestMapping(path = "/pago-suscripcion", method = RequestMethod.GET)
	public ModelAndView suscripcionElegida(HttpServletRequest request,
										   @RequestParam(value = "d") Long idDetalleSuscripcion)
										   {
		
		ModelMap model = new ModelMap();
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		
		DatosSuscripcion ds = new DatosSuscripcion();
		ds.setIdDetalleSuscripcion(idDetalleSuscripcion);
		
		if (usuarioSesion != null) {
				model.addAttribute("datosSuscripcion", ds);
				model.put("d", idDetalleSuscripcion);
				model.put("servicioElegido", servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(idDetalleSuscripcion));

				return new ModelAndView("suscripcion-pago", model);
		}
			return new ModelAndView("redirect:/login", model);
	}
	

	@RequestMapping(path = "/procesarSuscripcion", method = RequestMethod.POST)
	public ModelAndView reciboSuscripcion(@ModelAttribute("datosSuscripcion") DatosSuscripcion datosSuscripcion,
										   HttpServletRequest request){
				
				Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
				Suscripcion s = usuarioSesion.getSuscripcion();
				DetalleSuscripcion ds = servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(datosSuscripcion.getIdDetalleSuscripcion());
				
				s.setCantidadDeBoletosGratisRestantes(ds.getCantidadBoletosGratis());
				s.setDetalleSuscripcion(ds);
				servicioSuscripcion.modificarSuscripcion(s);
				
				ModelMap model = new ModelMap();
				model.put("datosSuscripcion", datosSuscripcion);
				model.put("usuarioSuscripto", usuarioSesion);

				return new ModelAndView("suscripcion-validar", model);
	}
	
}
