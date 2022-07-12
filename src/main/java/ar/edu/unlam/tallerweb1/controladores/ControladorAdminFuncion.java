package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionCineNoEncontrado;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFuncionCamposVacios;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFuncionHoraIncorrecta;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFuncionNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ExceptionFuncionPrecioIncorrecto;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ExceptionSalaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioButacaFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorAdminFuncion {

	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioButaca servicioButaca;
	private ServicioPelicula servicioPelicula;
	private ServicioFuncion servicioFuncion;
	private ServicioButacaFuncion servicioButacaFuncion;
	
	@Autowired
	public ControladorAdminFuncion(ServicioCine servicioCine, ServicioSala servicioSala, ServicioButaca servicioButaca,
			ServicioPelicula servicioPelicula, ServicioFuncion servicioFuncion, ServicioButacaFuncion servicioButacaFuncion) {
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioButaca = servicioButaca;
		this.servicioPelicula = servicioPelicula;
		this.servicioFuncion = servicioFuncion;
		this.servicioButacaFuncion = servicioButacaFuncion;
	}
	
	public void CargarModel(ModelMap mod, DatosFuncion df) {
		mod.addAttribute("datosFuncion", df);
		mod.put("listaCines", servicioCine.obtenerTodosLosCines());
		mod.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		mod.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
	}
	
	@RequestMapping( path = "/admin-funciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarFuncion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		
		return new ModelAndView("admin-funciones", model);
	}
	
	@RequestMapping(path = "/form-funcion-nueva", method = RequestMethod.GET)
	public ModelAndView crearFuncion(HttpServletRequest request) {
	
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		CargarModel(model, new DatosFuncion());
		model.put("elementoNuevo", true);
		
		return new ModelAndView("admin-funciones-form", model);
	}
	
	@RequestMapping(path = "/registrar-funcion-nueva", method = RequestMethod.POST)
	public ModelAndView agregarNuevaFuncion( @ModelAttribute("datosFuncion") DatosFuncion datosFuncion, HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		Funcion nuevaFuncion = new Funcion();
		Cine cineSeleccionado = new Cine();
		Sala salaSeleccionada = new Sala();
		Pelicula peliculaSeleccionada = new Pelicula();
		
		try {
			cineSeleccionado = servicioCine.buscarCinePorID(datosFuncion.getIdCine());
			salaSeleccionada = servicioSala.buscarSalaPorId(datosFuncion.getIdSala());
			peliculaSeleccionada = servicioPelicula.buscarPeliculaPorID(datosFuncion.getIdPelicula());
		}catch(ExceptionCineNoEncontrado e) {
			model.put("msgError", "Algo salió mal, el cine no está registrado");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionSalaNoEncontrada e) {
			model.put("msgError", "Algo salió mal, la sala no está registrada");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionPeliculaNoEncontrada e) {
			model.put("msgError", "Algo salió mal, la película no está registrada");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}
		
		if(datosFuncion.getFechaHora() == "" || datosFuncion.getFechaHora() == null) {
			model.put("msgError", "No se seleccionó una fecha para la función");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}
		Date fecha = Date.valueOf(datosFuncion.getFechaHora());
		
		nuevaFuncion.setFechaHora(fecha);
		nuevaFuncion.setPrecioMayor(datosFuncion.getPrecioMayor());
		nuevaFuncion.setPrecioMenor(datosFuncion.getPrecioMenor());
		nuevaFuncion.setCine(cineSeleccionado);
		nuevaFuncion.setSala(salaSeleccionada);
		nuevaFuncion.setPelicula(peliculaSeleccionada);
		nuevaFuncion.setHora(datosFuncion.getHora());
		nuevaFuncion.setEntradasDisponibles(servicioButaca.cantidadDeButacasEnSala(salaSeleccionada.getId()));
		
		try {
			servicioFuncion.guardarFuncion(nuevaFuncion);
		}catch(ExceptionFuncionCamposVacios e) {
			model.put("msgError", e.getMessage());
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionFuncionHoraIncorrecta e) {
			model.put("msgError", "La hora ingresada no está en el formato correcto (hh:mm)");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionFuncionPrecioIncorrecto e) {
			model.put("msgError", "Algo salió mal, el cine no está registrado");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", true);
			return new ModelAndView("admin-funciones-form", model);
		}
			
		List<Butaca> butacas=servicioButaca.obtenerButacasPorSala(salaSeleccionada.getId());
		servicioButacaFuncion.asociarButacasAFuncion(nuevaFuncion, butacas);
		
		model.put("msgExito", "Función guardada con exito");
		model.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		return new ModelAndView("admin-funciones", model);
	}
	
	@RequestMapping(path = "/editar-funcion-vieja", method = RequestMethod.GET)
	public ModelAndView modificarFuncion(@RequestParam(value = "id") Long id, HttpServletRequest request) {
	
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		Funcion funcionAEditar = new Funcion();
		DatosFuncion funcionFormulario = new DatosFuncion();
		
		try {
			funcionAEditar = servicioFuncion.buscarFuncion(id);
		}catch(ExceptionFuncionNoEncontrada e) {
			return new ModelAndView("redirect:/admin-funciones");
		}
		funcionFormulario.setId(id);
		funcionFormulario.setFechaHora(funcionAEditar.getFechaHora().toString());
		funcionFormulario.setPrecioMayor(funcionAEditar.getPrecioMayor());
		funcionFormulario.setPrecioMenor(funcionAEditar.getPrecioMenor());
		funcionFormulario.setIdPelicula(funcionAEditar.getPelicula().getId());
		funcionFormulario.setIdCine(funcionAEditar.getCine().getId());
		funcionFormulario.setIdSala(funcionAEditar.getSala().getId());
		funcionFormulario.setHora(funcionAEditar.getHora());
		
		CargarModel(model, funcionFormulario);
		model.put("elementoNuevo", false);
		
		return new ModelAndView("admin-funciones-form", model);
	}
	
	@RequestMapping(path = "/actualizar-funcion-vieja", method = RequestMethod.POST)
	public ModelAndView actualizarFuncion(@RequestParam(value = "id") Long id, @ModelAttribute("datosFuncion") DatosFuncion datosFuncion, HttpServletRequest request) {

		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		Funcion nuevaFuncion = new Funcion();
		Cine cineSeleccionado = new Cine();
		Sala salaSeleccionada = new Sala();
		Pelicula peliculaSeleccionada = new Pelicula();
		
		try {
			cineSeleccionado = servicioCine.buscarCinePorID(datosFuncion.getIdCine());
			salaSeleccionada = servicioSala.buscarSalaPorId(datosFuncion.getIdSala());
			peliculaSeleccionada = servicioPelicula.buscarPeliculaPorID(datosFuncion.getIdPelicula());
		}catch(ExceptionCineNoEncontrado e) {
			model.put("msgError", "Algo salió mal, el cine no está registrado");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionSalaNoEncontrada e) {
			model.put("msgError", "Algo salió mal, la sala no está registrada");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionPeliculaNoEncontrada e) {
			model.put("msgError", "Algo salió mal, la película no está registrada");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}
		
		if(datosFuncion.getFechaHora() == "" || datosFuncion.getFechaHora() == null) {
			model.put("msgError", "No se seleccionó una fecha para la función");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}
		Date fecha = Date.valueOf(datosFuncion.getFechaHora());
		
		nuevaFuncion.setId(id);
		nuevaFuncion.setFechaHora(fecha);
		nuevaFuncion.setPrecioMayor(datosFuncion.getPrecioMayor());
		nuevaFuncion.setPrecioMenor(datosFuncion.getPrecioMenor());
		nuevaFuncion.setCine(cineSeleccionado);
		nuevaFuncion.setSala(salaSeleccionada);
		nuevaFuncion.setPelicula(peliculaSeleccionada);
		nuevaFuncion.setHora(datosFuncion.getHora());
		nuevaFuncion.setEntradasDisponibles(servicioButaca.cantidadDeButacasEnSala(salaSeleccionada.getId()));
		
		try {
			servicioFuncion.actualizarFuncion(nuevaFuncion);
		}catch(ExceptionFuncionCamposVacios e) {
			model.put("msgError", e.getMessage());
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionFuncionHoraIncorrecta e) {
			model.put("msgError", "La hora ingresada no está en el formato correcto (hh:mm)");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}catch(ExceptionFuncionPrecioIncorrecto e) {
			model.put("msgError", "Algo salió mal, el cine no está registrado");
			CargarModel(model, datosFuncion);
			model.put("elementoNuevo", false);
			return new ModelAndView("admin-funciones-form", model);
		}
			
		List<Butaca> butacas=servicioButaca.obtenerButacasPorSala(salaSeleccionada.getId());
		servicioButacaFuncion.asociarButacasAFuncion(nuevaFuncion, butacas);
		
		model.put("msgExito", "Función actualizada con exito");
		model.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		return new ModelAndView("admin-funciones", model);
	}
}
