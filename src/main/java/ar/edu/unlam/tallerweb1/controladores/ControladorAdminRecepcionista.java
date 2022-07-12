package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorAdminRecepcionista {

	private ServicioUsuario servicioUsuario;
	
	@Autowired
	public ControladorAdminRecepcionista(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}
	
	@RequestMapping( path = "/admin-recepcionistas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarRecepcionista (HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("listaRecepcionistas", servicioUsuario.obtenerUsuariosPorRol("recepcionista"));
		
		return new ModelAndView("admin-recepcionistas", model);
	}
	
	@RequestMapping(path = "/form-recepcionista-nuevo", method = RequestMethod.GET)
	public ModelAndView crearRecepcionista (HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.addAttribute("datosRecepcionista", new DatosLogin());
		
		return new ModelAndView("admin-recepcionistas-form", model);
	}
	
	@RequestMapping( path = "/registrar-recepcionista-nuevo", method = RequestMethod.POST)
	public ModelAndView agregarNuevoRecepcionista( @ModelAttribute("datosRecepcionista") DatosLogin datosRecepcionista, HttpServletRequest request){
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		if (servicioUsuario.buscarUsuarioPorEmail(datosRecepcionista.getEmail()) == null) {
			Usuario recepcionista = new Usuario();
			recepcionista.setEmail(datosRecepcionista.getEmail());
			recepcionista.setPassword(datosRecepcionista.getPassword());
			recepcionista.setActivo(true);
			recepcionista.setRol("recepcionista");
			servicioUsuario.guardarUsuario(recepcionista);
			
			model.put("msgExito", "Recepcionista registrado correctamente");
		} else {
			model.put("msgError", "El email ya está en uso");
			model.addAttribute("datosRecepcionista", datosRecepcionista);
			return new ModelAndView("admin-recepcionistas-form", model);
		}
		
		model.put("listaRecepcionistas", servicioUsuario.obtenerUsuariosPorRol("recepcionista"));
		return new ModelAndView("admin-recepcionistas", model);
	}
}
