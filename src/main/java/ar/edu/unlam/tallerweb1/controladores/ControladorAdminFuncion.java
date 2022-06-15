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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
	
	@RequestMapping( path = "/admin-funciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarFuncion(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosFuncion", new DatosFuncion());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		modelo.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		
		return new ModelAndView("admin-funciones", modelo);
	}
	@RequestMapping(path = "/agregar-funcion", method = RequestMethod.POST)
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
		
		cineSeleccionado = servicioCine.buscarCinePorID(datosFuncion.getIdCine());
		salaSeleccionada = servicioSala.buscarSalaPorId(datosFuncion.getIdSala());
		peliculaSeleccionada = servicioPelicula.buscarPeliculaPorID(datosFuncion.getIdPelicula());
		
		Date fecha = Date.valueOf(datosFuncion.getFechaHora());
		
		nuevaFuncion.setFechaHora(fecha);
		nuevaFuncion.setPrecioMayor(datosFuncion.getPrecioMayor());
		nuevaFuncion.setPrecioMenor(datosFuncion.getPrecioMenor());
		nuevaFuncion.setCine(cineSeleccionado);
		nuevaFuncion.setSala(salaSeleccionada);
		nuevaFuncion.setPelicula(peliculaSeleccionada);
		nuevaFuncion.setHora(datosFuncion.getHora());
		nuevaFuncion.setEntradasDisponibles(servicioButaca.cantidadDeButacasEnSala(salaSeleccionada.getId()));
		
		servicioFuncion.guardarFuncion(nuevaFuncion);
		List<Butaca> butacas=servicioButaca.obtenerButacasPorSala(salaSeleccionada.getId());
		servicioButacaFuncion.asociarButacasAFuncion(nuevaFuncion, butacas);
		
		model.addAttribute("datosFuncion", datosFuncion);
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		model.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		model.put("mens", "Función guardada con exito");
		return new ModelAndView("admin-funciones", model);
		
	}
}
