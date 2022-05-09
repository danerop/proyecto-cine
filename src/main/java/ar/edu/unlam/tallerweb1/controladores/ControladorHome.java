package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorHome {
	
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(){
		
		return new ModelAndView("inicio");
	
	}
	
	@RequestMapping(path = "/peliculas", method = RequestMethod.GET)
	public ModelAndView pelicula(){
		
		return new ModelAndView("peliculas");
	
	}
	
	@RequestMapping(path = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(){
		
		return new ModelAndView("admin");
	
	}
}
