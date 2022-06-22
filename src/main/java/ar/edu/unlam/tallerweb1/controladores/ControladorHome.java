package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorHome {

	private ServicioCine servicioCine;
	private ServicioBoleto servicioBoleto;
	private ServicioPelicula servicioPelicula;
	private ServicioLogin servicioUsuario;
	private ServicioNotificacion servicioNotificacion;

	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioBoleto servicioBoleto,
			ServicioLogin servicioUsuario, ServicioNotificacion servicioNotificacion) {
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioBoleto = servicioBoleto;
		this.servicioUsuario = servicioUsuario;
		this.servicioNotificacion=servicioNotificacion;
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
			model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
			return new ModelAndView("inicio", model);
		}
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		return new ModelAndView("inicio", model);
	}
	
	@RequestMapping(path = "/mapa", method = RequestMethod.GET)
	public ModelAndView mapa() {
		
		ModelMap model = new ModelMap();
		
		model.addAttribute(new DatosBuscar());
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		return new ModelAndView("mapa", model);
	}
	
	@RequestMapping(path = "/peliculas/{id}", method = RequestMethod.GET)
	public ModelAndView irAPaginaDePelicula(@PathVariable Long id, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		Pelicula pelicula = new Pelicula();
		List<Funcion> listaFuncion = new ArrayList<Funcion>();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		try {
			pelicula = servicioPelicula.buscarPeliculaPorID(id);
		}catch(ExceptionPeliculaNoEncontrada e) {
			return new ModelAndView("redirect:/inicio");
		}
		
		model.put("listaCines", listaFuncion);
		model.put("pelicula", pelicula);
		
		if (user != null) {
			model.put("usuario", servicioUsuario.consultarUsuario(user));
			return new ModelAndView("pelicula", model);
		}
		return new ModelAndView("pelicula", model);
	}
	
	/*
	 * @RequestMapping(path = "/peliculas", method = RequestMethod.GET) public
	 * ModelAndView pelicula(){
	 * 
	 * return new ModelAndView("peliculas");
	 * 
	 * }
	 */
	@RequestMapping(path = "/historialcompras", method = RequestMethod.GET)
	public ModelAndView irAHistorialCompras(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario") ) {
			return new ModelAndView("redirect:/inicio");
		}

		ModelMap model = new ModelMap();
		model.put("boletosadquiridos", servicioBoleto.buscarBoletosDeUnUsuario(user));
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		model.put("usuario", servicioUsuario.consultarUsuario(user));
		return new ModelAndView("historial-compras", model);
	}

}
