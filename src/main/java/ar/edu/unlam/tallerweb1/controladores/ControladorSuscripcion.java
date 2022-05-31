package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(path = "/pago-suscripcion", method = RequestMethod.GET)
	public ModelAndView suscripcionElegida(@ModelAttribute("datosSuscripcion") DatosSuscripcion datosSuscripcion,
										   HttpServletRequest request, 
										   @RequestParam(value = "s") Long idSuscripcion,
										   @RequestParam(value = "u") Long idUsuario)
										   {

		ModelMap model = new ModelMap();
		/*Usuario usuario = servicioUsuario.consultarUsuarioPorId(idUsuario);*/
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
	
		/*Suscripcion sub = servicioSuscripcion.obtenerSuscripcionPorId(idSuscripcion);
		Suscripcion suscripcion = new Suscripcion();*/
		
		if (usuarioSesion != null) {
				model.put("datosSuscripcion", datosSuscripcion);
				model.put("s", idSuscripcion);
				model.put("u", idUsuario);
				model.put("servicioElegido", servicioSuscripcion.obtenerSuscripcionPorId(idSuscripcion));
				/*modelo.put("s", idSuscripcion);
				modelo.put("suscripcion", idSuscripcion);
				usuarioSesion.setSuscripcion(sub);
				usuario.setSuscripcion(sub);*/
				return new ModelAndView("pago-suscripcion", model);
		}
			return new ModelAndView("redirect:/login", model);
	}
	

	@RequestMapping(path = "/procesarSuscripcion", method = RequestMethod.POST)
	public ModelAndView reciboSuscripcion(@ModelAttribute("datosSuscripcion") DatosSuscripcion datosSuscripcion,
										   HttpServletRequest request, 
										   @RequestParam(value = "s") Long idSuscripcion,
										   @RequestParam(value = "u") Long idUsuario){
				
				Usuario usuarioSuscripto = servicioUsuario.consultarUsuarioPorId(idUsuario);
				Suscripcion sub = servicioSuscripcion.obtenerSuscripcionPorId(idSuscripcion);
				
				usuarioSuscripto.setSuscripcion(sub);
				servicioUsuario.actualizarUsuario(usuarioSuscripto);
				
				ModelMap model = new ModelMap();
				model.put("datosSuscripcion", datosSuscripcion);
				model.put("s", idSuscripcion);
				model.put("u", idUsuario);
				model.put("usuarioSuscripto", usuarioSuscripto);
		
				return new ModelAndView("validar-suscripcion", model);
	}				
}
