package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioPeliculaGenero;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;

@Controller
public class ControladorHome {

	private ServicioCine servicioCine;
	private ServicioBoleto servicioBoleto;
	private ServicioPelicula servicioPelicula;
	private ServicioPeliculaGenero servicioPeliculaGenero;
	private ServicioNotificacion servicioNotificacion;
	private ServicioFuncion servicioFuncion;

	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioBoleto servicioBoleto,
			ServicioNotificacion servicioNotificacion, ServicioFuncion servicioFuncion, ServicioPeliculaGenero servicioPeliculaGenero) {
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioBoleto = servicioBoleto;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioFuncion = servicioFuncion;
		this.servicioPeliculaGenero = servicioPeliculaGenero;
	}

	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request, @ModelAttribute("mapping1Form") ModelMap model2) {
		
		ModelMap model = new ModelMap();
		model.addAllAttributes(model2);
		model.addAttribute(new DatosBuscar());
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		if (user == null) {
			//usuario no logueado
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			return new ModelAndView("inicio", model);
		}
		
		//usuario logueado
		model.put("usuario", user);
		model.put("rol", user.getRol());
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		
		//peliculas recomendadas
		model.put("PeliculasGenerosFavoritos", servicioPeliculaGenero.obtenerPeliculasRecomendadasPorGenerosFavoritos(user));
		model.put("PeliculasGenerosVistos", servicioPeliculaGenero.obtenerPeliculasRecomendadasPorBoletosComprados(user));
		
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
		List<Cine> listaCines = new ArrayList<Cine>();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		try {
			pelicula = servicioPelicula.buscarPeliculaPorID(id);
		}catch(ExceptionPeliculaNoEncontrada e) {
			return new ModelAndView("redirect:/inicio");
		}
		
		listaCines = servicioFuncion.obtenerCinesDisponiblesParaFunciones(pelicula.getId());
		listaFuncion = servicioFuncion.obtenerFuncionesFuturasDePelicula(id);
		
		model.put("listaCines", listaCines);
		model.put("listaFuncion", listaFuncion);
		model.put("pelicula", pelicula);
		model.put("usuarioQueLaVieron", servicioBoleto.obtenerCantidadUsuariosQueVieronLaPelicula(pelicula));
		model.addAttribute(new DatosBuscar());
		
		if (user != null) {
			model.put("usuario", user);
			return new ModelAndView("pelicula", model);
		}
		return new ModelAndView("pelicula", model);
	}
	
	@RequestMapping(path = "/historialcompras", method = RequestMethod.GET)
	public ModelAndView irAHistorialCompras(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.addAttribute(new DatosBuscar());
		model.put("boletosadquiridos", servicioBoleto.buscarBoletosDeUnUsuario(user));
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		model.put("usuario", user);
		return new ModelAndView("historial-compras", model);
	}

}
