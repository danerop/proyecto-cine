package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaAnioNoValido;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaDuracionNoValida;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenero;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorAdminPelicula {

	private ServicioPelicula servicioPelicula;
	
	@Autowired
	public ControladorAdminPelicula(ServicioPelicula servicioPelicula){
		this.servicioPelicula = servicioPelicula;
	}
	
	@RequestMapping( path = "/admin-peliculas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarPelicula(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosPelicula", new Pelicula());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		
		return new ModelAndView("admin-peliculas", modelo);
	}
	
	@RequestMapping(path = "/agregar-pelicula", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosPelicula") Pelicula datosPelicula, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		try{
			servicioPelicula.guardarPelicula(datosPelicula);
		}catch(ExceptionPeliculaCamposVacios e){
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			model.put("msgError", e.getMessage());
			return new ModelAndView("admin-peliculas", model);
		}catch(ExceptionPeliculaAnioNoValido e){
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			model.put("msgError", "El año ingresado no es valido");
			return new ModelAndView("admin-peliculas", model);
		}catch(ExceptionPeliculaDuracionNoValida e){
			model.addAttribute("datosPelicula", datosPelicula);
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			model.put("msgError", "La duración ingresada no es valida");
			return new ModelAndView("admin-peliculas", model);
		}
		
		model.addAttribute("datosPelicula", new Pelicula());
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("msgExito", "Película guardada con exito");
		return new ModelAndView("admin-peliculas", model);
	}
}
