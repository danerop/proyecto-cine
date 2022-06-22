package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorBusqueda {
	
	private ServicioPelicula servicioPelicula;
	
	@Autowired
	public ControladorBusqueda(ServicioPelicula servicioPelicula) {
		this.servicioPelicula = servicioPelicula;
	}
	
	@RequestMapping(path = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar(@ModelAttribute("datosBuscar") DatosBuscar datosBuscar) {
		
		ModelMap model = new ModelMap();
		
		model.addAttribute("datosBuscar", new DatosBuscar());
		model.put("listaPeliculas" , servicioPelicula.buscarPeliculasPorNombre(datosBuscar.getBusqueda()));
		
		return new ModelAndView("busqueda", model);
	}
}
