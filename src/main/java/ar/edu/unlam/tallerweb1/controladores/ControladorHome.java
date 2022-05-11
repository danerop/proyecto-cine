package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorHome {
	
	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula){
		this.servicioPelicula = servicioPelicula;
	}
	
	@Inject
	private ServicioCine servicioCine;
	private ServicioPelicula servicioPelicula;
	
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(){
		
		ModelMap model = new ModelMap();
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
				
		return new ModelAndView("inicio", model);
	
	}
	
	@RequestMapping(path = "/peliculas", method = RequestMethod.GET)
	public ModelAndView pelicula(){
		
		return new ModelAndView("peliculas");
	
	}
	
	@RequestMapping("/admin")
	public ModelAndView irAAdmin() {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCine", new DatosCine());
		modelo.put("datosFuncion", new DatosFuncion());

		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());

		return new ModelAndView("admin", modelo);
	}
}
