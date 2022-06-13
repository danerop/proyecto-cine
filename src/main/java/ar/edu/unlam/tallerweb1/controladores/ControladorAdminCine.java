package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;

@Controller
public class ControladorAdminCine {
	
	private ServicioCine servicioCine;
	
	@Autowired
	public ControladorAdminCine(ServicioCine servicioCine) {
		this.servicioCine = servicioCine;
	}
	
	@RequestMapping( path = "/admin-cines", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarCine(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("datosCine", new Cine());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-cines", modelo);
	}
	
	@RequestMapping(path = "/agregar-cine", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosCine") Cine datosCine) {
		
		ModelMap model = new ModelMap();
		model.addAttribute("datosCine", new Cine());
		
		try {
			
			servicioCine.guardarCine(datosCine);
			
		} catch (ExceptionCine e) {
			model.put("listaCines", servicioCine.obtenerTodosLosCines());
			model.put("msgError", e.getMessage() );
			return new ModelAndView("admin-cines", model);
		}
		
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		model.put("msgExito", "Cine guardado con exito");
		return new ModelAndView("admin-cines", model);
	}
}
