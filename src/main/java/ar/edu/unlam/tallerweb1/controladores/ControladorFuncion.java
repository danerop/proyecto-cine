package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;
//import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorFuncion {
	
	private ServicioFuncion servicioFuncion;
	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioPelicula servicioPelicula;
	
	@Autowired
	public ControladorFuncion(ServicioFuncion servicioFuncion, ServicioCine servicioCine, ServicioSala servicioSala, ServicioPelicula servicioPelicula){
		this.servicioFuncion = servicioFuncion;
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioPelicula = servicioPelicula;
	}
	
	@RequestMapping(path = "/agregar-funcion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaFuncion( @ModelAttribute("datosFuncion") DatosFuncion datosFuncion ) {
		
		Funcion nuevaFuncion = new Funcion();
		Cine cineSeleccionado = new Cine();
		Sala salaSeleccionada = new Sala();
		Pelicula peliculaSeleccionada = new Pelicula();
		
		cineSeleccionado = servicioCine.buscarCinePorID(datosFuncion.getIdCine());
		salaSeleccionada = servicioSala.buscarSalaPorId(datosFuncion.getIdSala());
		peliculaSeleccionada = servicioPelicula.buscarPeliculaPorID(datosFuncion.getIdPelicula());
		
		Date fecha = Date.valueOf(datosFuncion.getFechaHora());
		
		nuevaFuncion.setFechaHora(fecha);
		nuevaFuncion.setPrecioMayor(datosFuncion.getPrecioMayor());
		nuevaFuncion.setPrecioMenor(datosFuncion.getPrecioMenor());
		nuevaFuncion.setCine(cineSeleccionado);
		nuevaFuncion.setSala(salaSeleccionada);
		nuevaFuncion.setPelicula(peliculaSeleccionada);
		nuevaFuncion.setHora(datosFuncion.getHora());
		
		servicioFuncion.guardarFuncion(nuevaFuncion);
		
		return new ModelAndView("admin");
		
	}
	
}
