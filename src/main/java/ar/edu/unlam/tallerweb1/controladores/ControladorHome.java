package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorHome {

	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioPelicula servicioPelicula;
	private ServicioLogin servicioUsuario;

	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioSala servicioSala,
			ServicioLogin servicioUsuario) {
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioUsuario = servicioUsuario;
	}

	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request, @ModelAttribute("mapping1Form") ModelMap model2) {

		ModelMap model = new ModelMap();
		model.addAllAttributes(model2);
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");

		if (user != null) {
			model.put("usuario", servicioUsuario.consultarUsuario(user));
			model.put("rol", servicioUsuario.consultarUsuarioPorRol(user));
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			return new ModelAndView("inicio", model);
		}
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		return new ModelAndView("inicio", model);
	}

	/*
	 * @RequestMapping(path = "/peliculas", method = RequestMethod.GET) public
	 * ModelAndView pelicula(){
	 * 
	 * return new ModelAndView("peliculas");
	 * 
	 * }
	 */

}
