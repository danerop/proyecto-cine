package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoDeSala;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorSala {
	private ServicioSala servicioSala;
	private ServicioCine servicioCine;
	
	@Autowired
	public ControladorSala(ServicioSala servicioSala, ServicioCine servicioCine) {
		this.servicioSala = servicioSala;
		this.servicioCine = servicioCine;
	}
	
	@RequestMapping(path= "/agregar-sala", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSala( @ModelAttribute("datosSala") DatosSala datosSala ) {
		
		Sala nuevaSala = new Sala();
		Cine cineSeleccionado = new Cine();
		TipoDeSala tipo = TipoDeSala.Comun;
		
		cineSeleccionado = servicioCine.buscarCinePorID(datosSala.getIdCine());
		switch(datosSala.getTipo()) {
		case 1: tipo = TipoDeSala.Comun; break;
		case 2: tipo = TipoDeSala.Sala3D; break;
		case 3: tipo = TipoDeSala.Sala4D; break;
		case 4: tipo = TipoDeSala.GoldenClass; break;
		}
		
		nuevaSala.setCine(cineSeleccionado);
		nuevaSala.setTipo(tipo);
		
		servicioSala.guardarSala(nuevaSala);
		
		return new ModelAndView("admin");
	}
}
