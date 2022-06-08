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
import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoDeSala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioButacaFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorAdministrador {
	
	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioPelicula servicioPelicula;
	private ServicioLogin servicioUsuario;
	private ServicioFuncion servicioFuncion;
	private ServicioButaca servicioButaca;
	private ServicioButacaFuncion servicioButacaFuncion;
	private ServicioDetalleSuscripcion servicioDetalleSuscripcion;
	
	@Autowired
	public ControladorAdministrador(ServicioPelicula servicioPelicula, ServicioCine servicioCine,
			ServicioSala servicioSala, ServicioLogin servicioUsuario, ServicioFuncion servicioFuncion,
			ServicioButaca servicioButaca, ServicioButacaFuncion servicioButacaFuncion, ServicioDetalleSuscripcion servicioDetalleSuscripcion){
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioUsuario = servicioUsuario;
		this.servicioFuncion = servicioFuncion;
		this.servicioButaca = servicioButaca;
		this.servicioButacaFuncion = servicioButacaFuncion;
		this.servicioDetalleSuscripcion = servicioDetalleSuscripcion;
	}
	
	@RequestMapping( path = "/admin", method = RequestMethod.GET)
	public ModelAndView irAAdmin(HttpServletRequest request) {
		
		//Acá debería haber una comprobación de si el usuario es un administrador
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (request.getSession().getAttribute("usuario") == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		return new ModelAndView("admin");
		
	}
	
	@RequestMapping( path = "/admin-cines", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarCine() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosCine", new Cine());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		
		return new ModelAndView("admin-cines", modelo);
	}
	
	@RequestMapping( path = "/admin-salas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSala() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosSala", new DatosSala());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		
		return new ModelAndView("admin-salas", modelo);
	}
	
	@RequestMapping( path = "/admin-peliculas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarPelicula() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosPelicula", new Pelicula());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		
		return new ModelAndView("admin-peliculas", modelo);
	}
	
	@RequestMapping( path = "/admin-funciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarFuncion() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosFuncion", new DatosFuncion());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		modelo.put("listaFunciones", servicioFuncion.obtenerTodasLasFunciones());
		
		return new ModelAndView("admin-funciones", modelo);
	}
	
	@RequestMapping( path = "/admin-suscripciones", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSuscripcion() {
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		modelo.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		
		return new ModelAndView("admin-suscripciones", modelo);
	}
	
	@RequestMapping(path = "/agregar-cine", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosCine") Cine datosCine) {
		
		ModelMap model = new ModelMap();
		Cine nuevoCine = new Cine();
		
		nuevoCine.setNombreLocal(datosCine.getNombreLocal());
		nuevoCine.setDireccion(datosCine.getDireccion());
		nuevoCine.setTelefono(datosCine.getTelefono());
		nuevoCine.setEmail(datosCine.getEmail());
		nuevoCine.setUrlImagenCine(datosCine.getUrlImagenCine());
		nuevoCine.setLatitud(datosCine.getLatitud());
		nuevoCine.setLongitud(datosCine.getLongitud());
		
		servicioCine.guardarCine(nuevoCine);
		
		model.addAttribute("datosCine", new Cine());
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		model.put("mens", "Cine guardado con exito");
		return new ModelAndView("admin-cines", model);
	}
	@RequestMapping(path= "/agregar-sala", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSala( @ModelAttribute("datosSala") DatosSala datosSala ) {
		
		ModelMap model = new ModelMap();
		Sala nuevaSala = new Sala();
		Cine cineSeleccionado = new Cine();
		TipoDeSala tipo = TipoDeSala.Comun;
		
		cineSeleccionado = servicioCine.buscarCinePorID(datosSala.getIdCine());
		switch(datosSala.getTipo()) {
		case 1: tipo = TipoDeSala.Comun; break;
		case 2: tipo = TipoDeSala.Sala3D; break;
		case 3: tipo = TipoDeSala.Sala4D; break;
		case 4: tipo = TipoDeSala.GoldenClass; break;
		}
		
		
		nuevaSala.setCine(cineSeleccionado);
		nuevaSala.setTipo(tipo);
		
		servicioSala.guardarSala(nuevaSala);
		servicioButaca.guardarButacas(datosSala.getButacas(), nuevaSala);
		
		model.addAttribute("datosSala", new DatosSala());
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		model.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		model.put("mens", "Sala guardada con exito");
		return new ModelAndView("admin-salas", model);
	}
	@RequestMapping(path = "/agregar-pelicula", method = RequestMethod.POST)
	public ModelAndView agregarNuevoCine( @ModelAttribute("datosPelicula") Pelicula datosPelicula) {
		
		ModelMap model = new ModelMap();
		Pelicula nuevaPelicula = new Pelicula();
		
		nuevaPelicula.setNombre(datosPelicula.getNombre());
		nuevaPelicula.setAnio(datosPelicula.getAnio());
		nuevaPelicula.setDescripcion(datosPelicula.getDescripcion());
		nuevaPelicula.setDuracion(datosPelicula.getDuracion());
		nuevaPelicula.setUrlImagenPelicula(datosPelicula.getUrlImagenPelicula());
		
		servicioPelicula.guardarPelicula(nuevaPelicula);
		
		model.addAttribute("datosPelicula", new Pelicula());
		model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		model.put("mens", "Película guardada con exito");
		return new ModelAndView("admin-peliculas", model);
	}
	@RequestMapping(path = "/agregar-funcion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaFuncion( @ModelAttribute("datosFuncion") DatosFuncion datosFuncion ) {
		
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
	@RequestMapping(path = "/agregar-suscripcion", method = RequestMethod.POST)
	public ModelAndView agregarNuevaSuscripcion( @ModelAttribute("datosSuscripcion") DetalleSuscripcion datos) {
		
		ModelMap model = new ModelMap();
		DetalleSuscripcion nuevaSuscripcion = new DetalleSuscripcion();
		
		nuevaSuscripcion.setTipo(datos.getTipo());
		nuevaSuscripcion.setDescuentoEnBoletos(datos.getDescuentoEnBoletos());
		nuevaSuscripcion.setCantidadBoletosGratis(datos.getCantidadBoletosGratis());
		nuevaSuscripcion.setCuota(datos.getCuota());
		
		servicioDetalleSuscripcion.guardarDetalleSuscripcion(nuevaSuscripcion);
		
		model.addAttribute("datosSuscripcion", new DetalleSuscripcion());
		model.put("listaDetalleSuscripciones", servicioDetalleSuscripcion.obtenerTodasLasSuscripciones());
		model.put("mens", "Suscripción guardada con exito");
		return new ModelAndView("admin-suscripciones", model);
	}
}
