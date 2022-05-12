package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorPelicula {
	
private ServicioPelicula servicioPelicula;
	
	@Autowired
	public ControladorPelicula(ServicioPelicula servicioPelicula){
		this.servicioPelicula = servicioPelicula;
	}
	
	@RequestMapping(path = "/agregar-pelicula", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosPelicula") DatosPelicula datosPelicula) {
		
		Pelicula nuevaPelicula = new Pelicula();
		nuevaPelicula.setNombre(datosPelicula.getNombre());
		nuevaPelicula.setAnio(datosPelicula.getAnio());
		nuevaPelicula.setDescripcion(datosPelicula.getDescripcion());
		nuevaPelicula.setDuracion(datosPelicula.getDuracion());
		nuevaPelicula.setUrlImagenPelicula(datosPelicula.getUrlImagenPelicula());
		
		servicioPelicula.guardarPelicula(nuevaPelicula);
		
		return new ModelAndView("admin");
	}
	
}
