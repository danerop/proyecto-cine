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
		
		ModelMap modelo = new ModelMap();
		modelo.addAttribute("datosRecepcionista", new DatosLogin());
		modelo.put("listaRecepcionistas", servicioUsuario.obtenerUsuariosPorRol("recepcionista"));
		
		return new ModelAndView("admin-recepcionistas", modelo);
	}
	
	@RequestMapping( path = "/agregar-recepcionista", method = RequestMethod.POST)
	public ModelAndView agregarNuevoRecepcionista( @ModelAttribute("datosRecepcionista") DatosLogin datosRecepcionista, HttpServletRequest request){
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		if (servicioUsuario.buscarUsuarioPorEmail(datosRecepcionista.getEmail()) == null) {
			Usuario recepcionista = new Usuario();
			recepcionista.setEmail(datosRecepcionista.getEmail());
			recepcionista.setPassword(datosRecepcionista.getPassword());
			recepcionista.setActivo(true);
			recepcionista.setRol("recepcionista");
			servicioUsuario.guardarUsuario(recepcionista);
			
			modelo.put("msgExito", "Recepcionista registrado correctamente");
		} else {
			modelo.put("msgError", "El email ya está en uso");
			modelo.addAttribute("datosRecepcionista", new DatosLogin());
			modelo.put("listaRecepcionistas", servicioUsuario.obtenerUsuariosPorRol("recepcionista"));
			return new ModelAndView("admin-recepcionistas", modelo);
		}
		
		modelo.addAttribute("datosRecepcionista", new DatosLogin());
		modelo.put("listaRecepcionistas", servicioUsuario.obtenerUsuariosPorRol("recepcionista"));
		return new ModelAndView("admin-recepcionistas", modelo);
	}
}
