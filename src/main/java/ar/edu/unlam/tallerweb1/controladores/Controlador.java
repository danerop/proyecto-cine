package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {
	
	@RequestMapping("/map", method = RequestMethod.GET)
	public ModelAndView irAMapa() {
		return new ModelAndView("map");
	}
	
	@RequestMapping("/search", method = RequestMethod.GET)
	public ModelAndView irABusqueda() {
		return new ModelAndView("search");
	}
}
