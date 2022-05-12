package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;


@Controller
public class ControladorHome {
	
	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioSala servicioSala){
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
	}
	
	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
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
	
	@RequestMapping( path = "/admin", method = RequestMethod.GET)
	public ModelAndView irAAdminConSeleccion( @RequestParam(value="sel") String sel ) {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosCine", new DatosCine());
		modelo.addAttribute("datosFuncion", new DatosFuncion());
		modelo.addAttribute("datosSala", new DatosSala());
		modelo.addAttribute("datosPelicula", new DatosPelicula());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		modelo.put("seleccion", sel);
		
		return new ModelAndView("admin", modelo);
	}
}
