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
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;


@Controller
public class ControladorHome {
	
	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioSala servicioSala, ServicioLogin servicioUsuario){
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioUsuario = servicioUsuario;
	}
	
	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioPelicula servicioPelicula;
	private ServicioLogin servicioUsuario;
	
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(){
		
		ModelMap model = new ModelMap();
		model.put("usuario", servicioUsuario.consultarUsuarioPorId(1L));
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
	
		return new ModelAndView("inicio", model);

	
	}
	
	@RequestMapping(path = "/peliculas", method = RequestMethod.GET)
	public ModelAndView pelicula(){
		
		return new ModelAndView("peliculas");
	
	}
	
	
	
	@RequestMapping( path = "/admin", method = RequestMethod.GET)
	public ModelAndView irAAdmin() {
		
		return new ModelAndView("admin");
		
	}
	
	@RequestMapping( path = "/admin-cargar-cine", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarCine() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosCine", new Cine());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-cargar-cine", modelo);
	}
	@RequestMapping( path = "/admin-cargar-sala", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSala() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosSala", new DatosSala());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		
		return new ModelAndView("admin-cargar-sala", modelo);
	}
	@RequestMapping( path = "/admin-cargar-pelicula", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarPelicula() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosPelicula", new Pelicula());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		
		return new ModelAndView("admin-cargar-pelicula", modelo);
	}
	@RequestMapping( path = "/admin-cargar-funcion", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarFuncion() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosFuncion", new DatosFuncion());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		
		return new ModelAndView("admin-cargar-funcion", modelo);
	}
}
