package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCineCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCineNoEncontrado;
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
		
		ModelMap model = new ModelMap();
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-cines", model);
	}
	
	@RequestMapping( path = "/form-cine-nuevo", method = RequestMethod.GET)
	public ModelAndView crearCine(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", true);
		model.addAttribute("datosCine", new Cine());
		
		return new ModelAndView("admin-cines-form", model);
	}
	
	@RequestMapping(path = "/registrar-cine-nuevo", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosCine") Cine datosCine, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		try {
			servicioCine.guardarCine(datosCine);
			
		} catch (ExceptionCineCamposVacios e) {
			model.put("elementoNuevo", true);
			model.addAttribute("datosCine", datosCine);
			model.put("msgError", e.getMessage() );
			return new ModelAndView("admin-cines-form", model);
		}
		
		model.put("msgExito", "Cine guardado con exito");
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		return new ModelAndView("admin-cines", model);
	}
	
	@RequestMapping(path = "/editar-cine-viejo", method = RequestMethod.GET)
	public ModelAndView modificarCine(@RequestParam(value = "id") Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", false);
		
		Cine cineAEditar = new Cine();
		
		try {
			cineAEditar = servicioCine.buscarCinePorID(id);
		}catch(ExceptionCineNoEncontrado e) {
			return new ModelAndView("redirect:/admin-cines");
		}
		
		model.addAttribute("datosCine", cineAEditar);

		return new ModelAndView("admin-cines-form", model);
	}
	
	@RequestMapping(path = "/actualizar-cine-viejo", method = RequestMethod.POST)
	public ModelAndView actualizarCine( @RequestParam(value = "id") Long id, @ModelAttribute("datosCine") Cine datosCine, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		datosCine.setId(id);
		
		try {
			servicioCine.actualizarCine(datosCine);
			
		} catch (ExceptionCineCamposVacios e) {
			model.put("elementoNuevo", false);
			model.addAttribute("datosCine", datosCine);
			model.put("msgError", e.getMessage() );
			return new ModelAndView("admin-cines-form", model);
		}
		
		model.put("msgExito", "Cine actualizado con exito");
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		return new ModelAndView("admin-cines", model);
	}
}
