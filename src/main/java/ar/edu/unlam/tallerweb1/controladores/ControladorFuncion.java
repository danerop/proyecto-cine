package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;

@Controller
public class ControladorFuncion {
	
	private ServicioFuncion servicioFuncion;
	
	@Autowired
	public ControladorFuncion(ServicioFuncion servicioFuncion){
		this.servicioFuncion = servicioFuncion;
	}
	
	@RequestMapping(path = "/agregar-funcion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaFuncion( @ModelAttribute("datosFuncion") DatosFuncion datosFuncion ) {
		
		Funcion nuevaFuncion = new Funcion();
		
		nuevaFuncion.setCine(datosFuncion.getCine());
		nuevaFuncion.setPelicula(datosFuncion.getPelicula());
		nuevaFuncion.setSala(datosFuncion.getSala());
		nuevaFuncion.setPrecioMayor(datosFuncion.getPrecioMayor());
		nuevaFuncion.setPrecioMenor(datosFuncion.getPrecioMenor());
		nuevaFuncion.setFechaHora(datosFuncion.getFechaHora());
		
		return new ModelAndView("admin");
		
	}
	
}
