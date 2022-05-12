package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;


@Controller
public class ControladorCargarCine {
	
	private ServicioCine servicioCine;
	
	@Autowired
	public ControladorCargarCine(ServicioCine servicioCine){
		this.servicioCine = servicioCine;
	}
	
	
	@RequestMapping(path = "/busqueda-cine", method = RequestMethod.POST)
	public ModelAndView buscarCinePorId( @ModelAttribute("datosCine") DatosCine DatosCine) {
		ModelMap model = new ModelMap();
		
		Cine cineBuscado = servicioCine.buscarCinePorID(DatosCine.getId());
		
		if(cineBuscado != null) {
			model.put("cinenombre", cineBuscado.getNombreLocal());
			model.put("cinedireccion", cineBuscado.getDireccion());
			return new ModelAndView("admin", model);
		}
		
		return new ModelAndView("admin");
	}

	@RequestMapping(path = "/agregar-cine", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosCine") DatosCine datosCine) {
		
		Cine nuevoCine = new Cine();
		nuevoCine.setNombreLocal(datosCine.getNombreLocal());
		nuevoCine.setDireccion(datosCine.getDireccion());
		nuevoCine.setTelefono(datosCine.getTelefono());
		nuevoCine.setEmail(datosCine.getEmail());
		nuevoCine.setUrlImagenCine(datosCine.getUrlImagenCine());
		
		servicioCine.guardarCine(nuevoCine);
		
		return new ModelAndView("admin");
	}
}
