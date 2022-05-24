package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
// import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
// import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

// import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

//import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
//import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
//import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

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
}
