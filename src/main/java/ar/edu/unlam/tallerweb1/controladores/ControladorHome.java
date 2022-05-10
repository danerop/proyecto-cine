package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;

@Controller
public class ControladorHome {
	
	private ServicioCine servicioCine;
	
	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(){
		
		return new ModelAndView("inicio");
	
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
		
		//List<Cine> listaCines = new ArrayList<Cine>();
		//listaCines.add(servicioCine.buscarCinePorID(2L));
		//Iterator<Cine> iter = listaCines.iterator();

		//modelo.put("listaCine", listaCines);
		return new ModelAndView("admin", modelo);
	}
}
