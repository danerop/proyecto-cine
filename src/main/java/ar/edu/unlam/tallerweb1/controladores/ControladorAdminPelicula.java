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

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaAnioNoValido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaDuracionNoValida;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorAdminPelicula {

	private ServicioPelicula servicioPelicula;
	private ServicioNotificacion servicioNotificacion;
	
	@Autowired
	public ControladorAdminPelicula(ServicioPelicula servicioPelicula, ServicioNotificacion servicioNotificacion){
		this.servicioPelicula = servicioPelicula;
		this.servicioNotificacion = servicioNotificacion;
	}
	
	@RequestMapping( path = "/admin-peliculas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarPelicula(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		
		return new ModelAndView("admin-peliculas", model);
	}
	
	@RequestMapping( path = "/form-pelicula-nueva", method = RequestMethod.GET)
	public ModelAndView crearPelicula(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", true);
		model.addAttribute("datosPelicula", new Pelicula());
		
		return new ModelAndView("admin-peliculas-form", model);
	}
	
	@RequestMapping(path = "/registrar-pelicula-nueva", method = RequestMethod.POST)
	public ModelAndView agregarNuevaPelicula( @ModelAttribute("datosPelicula") Pelicula datosPelicula, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		try{
			servicioPelicula.guardarPelicula(datosPelicula);
			
		}catch(ExceptionPeliculaCamposVacios e){
			model.put("elementoNuevo", true);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-peliculas-form", model);
		}catch(ExceptionPeliculaAnioNoValido e){
			model.put("elementoNuevo", true);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", "El año ingresado no es valido");
			return new ModelAndView("admin-peliculas-form", model);
		}catch(ExceptionPeliculaDuracionNoValida e){
			model.put("elementoNuevo", true);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", "La duración ingresada no es valida");
			return new ModelAndView("admin-peliculas-form", model);
		}
		
		model.put("msgExito", "Pelicula guardada con exito");
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-peliculas", model);
	}
	
	@RequestMapping(path = "/editar-pelicula-vieja", method = RequestMethod.GET)
	public ModelAndView modificarPelicula(@RequestParam(value = "id") Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", false);
		
		Pelicula peliculaAEditar = new Pelicula();
		
		try {
			peliculaAEditar = servicioPelicula.buscarPeliculaPorID(id);
		}catch(ExceptionPeliculaNoEncontrada e) {
			return new ModelAndView("redirect:/admin-peliculas");
		}
		
		model.addAttribute("datosPelicula", peliculaAEditar);

		return new ModelAndView("admin-peliculas-form", model);
	}
	
	@RequestMapping(path = "/actualizar-pelicula-vieja", method = RequestMethod.POST)
	public ModelAndView actualizarPelicula( @RequestParam(value = "id") Long id, @ModelAttribute("datosPelicula") Pelicula datosPelicula, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		datosPelicula.setId(id);
		
		try{
			servicioPelicula.actualizarPelicula(datosPelicula);
			
		}catch(ExceptionPeliculaCamposVacios e){
			model.put("elementoNuevo", false);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-peliculas-form", model);
		}catch(ExceptionPeliculaAnioNoValido e){
			model.put("elementoNuevo", false);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", "El año ingresado no es valido");
			return new ModelAndView("admin-peliculas-form", model);
		}catch(ExceptionPeliculaDuracionNoValida e){
			model.put("elementoNuevo", false);
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("msgError", "La duración ingresada no es valida");
			return new ModelAndView("admin-peliculas-form", model);
		}
		
		model.put("msgExito", "Pelicula actualizada con exito");
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-peliculas", model);
	}
}
