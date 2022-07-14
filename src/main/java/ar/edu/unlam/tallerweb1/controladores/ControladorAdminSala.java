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
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoDeSala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCineNoEncontrado;
import ar.edu.unlam.tallerweb1.servicios.ExceptionSalaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorAdminSala {

	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioButaca servicioButaca;
	private ServicioNotificacion servicioNotificacion;
	
	@Autowired
	public ControladorAdminSala(ServicioCine servicioCine, ServicioSala servicioSala, ServicioButaca servicioButaca, ServicioNotificacion servicioNotificacion) {
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioButaca = servicioButaca;
		this.servicioNotificacion = servicioNotificacion;
	}
	
	@RequestMapping( path = "/admin-salas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSala(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		model.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		
		return new ModelAndView("admin-salas", model);
	}
	
	@RequestMapping(path = "/form-sala-nueva", method = RequestMethod.GET)
	public ModelAndView crearSala(HttpServletRequest request){
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", true);
		model.addAttribute("datosSala", new DatosSala());
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-salas-form", model);
		
	}
	
	@RequestMapping(path= "/registrar-sala-nueva", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSala( @ModelAttribute("datosSala") DatosSala datosSala, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		Sala nuevaSala = new Sala();
		Cine cineSeleccionado = new Cine();
		TipoDeSala tipo = TipoDeSala.Comun;
		
		try {
			cineSeleccionado = servicioCine.buscarCinePorID(datosSala.getIdCine());
		}catch(ExceptionCineNoEncontrado e) {
			model.addAttribute("datosSala", datosSala);
			model.put("elementoNuevo", true);
			model.put("listaCines", servicioCine.obtenerTodosLosCines());
			model.put("msgError", "Algo salió mal, el cine elegido no está registrado");
			return new ModelAndView("admin-salas-form", model);
		}
		
		switch(datosSala.getTipo()) {
		case 1: tipo = TipoDeSala.Comun; break;
		case 2: tipo = TipoDeSala.Sala3D; break;
		case 3: tipo = TipoDeSala.Sala4D; break;
		case 4: tipo = TipoDeSala.GoldenClass; break;
		default: 
			model.addAttribute("datosSala", datosSala);
			model.put("elementoNuevo", true);
			model.put("listaCines", servicioCine.obtenerTodosLosCines());
			model.put("msgError", "Algo salió mal, el tipo de sala no existe");
			return new ModelAndView("admin-salas-form", model);
		}
		
		nuevaSala.setCine(cineSeleccionado);
		nuevaSala.setTipo(tipo);
		
		servicioSala.guardarSala(nuevaSala);
		servicioButaca.guardarButacas(datosSala.getButacas(), nuevaSala);
		
		model.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		model.put("msgExito", "Sala guardada con exito");
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-salas", model);
	}
	
	@RequestMapping(path = "/editar-sala-vieja", method = RequestMethod.GET)
	public ModelAndView modificarSala(@RequestParam(value = "id") Long id, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("elementoNuevo", false);
		
		Sala salaAEditar = new Sala();
		DatosSala salaFormulario = new DatosSala();
		
		try {
			salaAEditar = servicioSala.buscarSalaPorId(id);
		}catch(ExceptionSalaNoEncontrada e) {
			return new ModelAndView("redirect:/admin-salas");
		}
		
		salaFormulario.setId(id);
		salaFormulario.setIdCine(salaAEditar.getCine().getId());
		salaFormulario.setTipo(salaAEditar.getTipo().ordinal()+1);
		salaFormulario.setButacas(servicioButaca.obtenerIntegersButacasPorSala(salaAEditar));
		
		model.addAttribute("datosSala", salaFormulario);
		model.put("elementoNuevo", false);
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-salas-form", model);
	}
	
	@RequestMapping(path = "/actualizar-sala-vieja", method = RequestMethod.POST)
	public ModelAndView actualizarSala(@RequestParam(value = "id") Long id, @ModelAttribute("datosSala") DatosSala datosSala, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		Sala nuevaSala = new Sala();
		Cine cineSeleccionado = new Cine();
		TipoDeSala tipo = TipoDeSala.Comun;
		
		try {
			cineSeleccionado = servicioCine.buscarCinePorID(datosSala.getIdCine());
		}catch(ExceptionCineNoEncontrado e) {
			model.addAttribute("datosSala", datosSala);
			model.put("elementoNuevo", false);
			model.put("listaCines", servicioCine.obtenerTodosLosCines());
			model.put("msgError", "Algo salió mal, el cine elegido no está registrado");
			return new ModelAndView("admin-salas-form", model);
		}
		
		switch(datosSala.getTipo()) {
		case 1: tipo = TipoDeSala.Comun; break;
		case 2: tipo = TipoDeSala.Sala3D; break;
		case 3: tipo = TipoDeSala.Sala4D; break;
		case 4: tipo = TipoDeSala.GoldenClass; break;
		default: 
			model.addAttribute("datosSala", datosSala);
			model.put("elementoNuevo", false);
			model.put("listaCines", servicioCine.obtenerTodosLosCines());
			model.put("msgError", "Algo salió mal, el tipo de sala no existe");
			return new ModelAndView("admin-salas-form", model);
		}
		
		nuevaSala.setId(id);
		nuevaSala.setCine(cineSeleccionado);
		nuevaSala.setTipo(tipo);
		
		servicioSala.actualizarSala(nuevaSala);
		servicioButaca.actualizarButacas(datosSala.getButacas(), nuevaSala);
		
		model.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		model.put("msgExito", "Sala actualizada con exito");
		model.put("usuario", user);
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("admin-salas", model);
	}
}
