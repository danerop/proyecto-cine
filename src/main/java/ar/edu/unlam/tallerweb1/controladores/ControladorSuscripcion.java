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

import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

@Controller
public class ControladorSuscripcion {

	private ServicioSuscripcion servicioSuscripcion;
	private ServicioLogin servicioUsuario;

	@Autowired
	public ControladorSuscripcion(ServicioSuscripcion servicioSuscripcion, ServicioLogin servicioUsuario) {
		this.servicioSuscripcion = servicioSuscripcion;
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping(path = "/suscripcion", method = RequestMethod.GET)
	public ModelAndView irASuscripcion() {
		ModelMap model = new ModelMap();

		model.put("listaDeSuscripciones", servicioSuscripcion.obtenerTodasLasSuscripciones());

		return new ModelAndView("suscripcion", model);
	}

	@RequestMapping(path = "/validar-suscripcion", method = RequestMethod.GET)
	public ModelAndView suscripcionElegida(HttpServletRequest request, 
										   @RequestParam(value = "s") Long idSuscripcion)
										   {

		ModelMap modelo = new ModelMap();
		
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		Suscripcion sub = servicioSuscripcion.obtenerSuscripcionPorId(idSuscripcion);
		
		if (usuarioSesion != null) {
				modelo.put("s", idSuscripcion);
				modelo.put("suscripcion", idSuscripcion);

				usuarioSesion.setSuscripcion(sub);
				
				return new ModelAndView("validar-suscripcion", modelo);
		}
			return new ModelAndView("redirect:/login", modelo);
	}

}
