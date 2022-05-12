package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorHome {
	
	private ServicioCine servicioCine;
	
	@Autowired
	public ControladorHome(ServicioCine servicioCine){
	this.servicioCine = servicioCine;
	}
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
	
		
		//Iterator<Cine> iter = listaCines.iterator();

		modelo.put("listaCines", servicioCine.obtenerTodosLosCines() );
		return new ModelAndView("admin", modelo);
	}
}
