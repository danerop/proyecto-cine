package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ExceptionPeliculaNoEncontrada;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFavorito;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioPeliculaGenero;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;

@Controller
public class ControladorHome {

	private ServicioCine servicioCine;
	private ServicioBoleto servicioBoleto;
	private ServicioPelicula servicioPelicula;
	private ServicioPeliculaGenero servicioPeliculaGenero;
	private ServicioLogin servicioUsuario;
	private ServicioNotificacion servicioNotificacion;
	private ServicioFuncion servicioFuncion;
	private ServicioFavorito servicioFavorito;

	@Autowired
	public ControladorHome(ServicioPelicula servicioPelicula, ServicioCine servicioCine, ServicioBoleto servicioBoleto,
			ServicioLogin servicioUsuario, ServicioNotificacion servicioNotificacion, ServicioFuncion servicioFuncion, ServicioFavorito servicioFavorito, ServicioPeliculaGenero servicioPeliculaGenero) {
		this.servicioPelicula = servicioPelicula;
		this.servicioCine = servicioCine;
		this.servicioBoleto = servicioBoleto;
		this.servicioUsuario = servicioUsuario;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioFuncion = servicioFuncion;
		this.servicioFavorito = servicioFavorito;
		this.servicioPeliculaGenero = servicioPeliculaGenero;
	}

	@RequestMapping(path = "/inicio", method = RequestMethod.GET)
	public ModelAndView inicio(HttpServletRequest request, @ModelAttribute("mapping1Form") ModelMap model2) {

		ModelMap model = new ModelMap();
		model.addAllAttributes(model2);
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		model.addAttribute(new DatosBuscar());
		
		if (user == null) {
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
			return new ModelAndView("inicio", model);
		}
		
		model.put("usuario", servicioUsuario.consultarUsuario(user));
		model.put("rol", servicioUsuario.consultarUsuarioPorRol(user));
		
		//peliculas recomendadas por generos favoritos
		List<Genero> listaGenerosFAV = new ArrayList<Genero>();
		if (servicioFavorito.obtenerFavoritoPorUsuario(user.getId())!=null && servicioFavorito.obtenerFavoritoPorUsuario(user.getId()).size()>1) {
			Iterator<Favorito> iterFavoritos = servicioFavorito.obtenerFavoritoPorUsuario(user.getId()).iterator();
			
			
			while(iterFavoritos.hasNext()) {
				listaGenerosFAV.add(iterFavoritos.next().getGenero());
			}
		}

		
		//peliculas recomendadas por boletos comprados
		Iterator<Funcion> iterFuncionesCompradas = servicioBoleto.obtenerFuncionesCompradasPorUsuario(user).iterator();
		
		List<Pelicula> listaPeliculasCompradas = new ArrayList<Pelicula>();
		
		while(iterFuncionesCompradas.hasNext()) {
			Pelicula pelicula = iterFuncionesCompradas.next().getPelicula();
			if(!listaPeliculasCompradas.contains(pelicula)) {
				listaPeliculasCompradas.add(pelicula);
			}
			System.out.println(pelicula);
		}
		
		if (servicioFavorito.obtenerFavoritoPorUsuario(user.getId())!=null && servicioFavorito.obtenerFavoritoPorUsuario(user.getId()).size()>1) {
			model.put("listaPeliculas", servicioPeliculaGenero.obtenerPeliculasRecomendadas(listaGenerosFAV, listaPeliculasCompradas));
		} else {
			model.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		}
		
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		return new ModelAndView("inicio", model);
	}
	
	@RequestMapping(path = "/mapa", method = RequestMethod.GET)
	public ModelAndView mapa() {
		
		ModelMap model = new ModelMap();
		
		model.addAttribute(new DatosBuscar());
		model.put("listaCines", servicioCine.obtenerTodosLosCines());
		return new ModelAndView("mapa", model);
	}
	
	@RequestMapping(path = "/peliculas/{id}", method = RequestMethod.GET)
	public ModelAndView irAPaginaDePelicula(@PathVariable Long id, HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		Pelicula pelicula = new Pelicula();
		List<Funcion> listaFuncion = new ArrayList<Funcion>();
		List<Cine> listaCines = new ArrayList<Cine>();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		
		try {
			pelicula = servicioPelicula.buscarPeliculaPorID(id);
		}catch(ExceptionPeliculaNoEncontrada e) {
			return new ModelAndView("redirect:/inicio");
		}
		
		listaCines = servicioFuncion.obtenerCinesDisponiblesParaFunciones(pelicula.getId());
		listaFuncion = servicioFuncion.obtenerFuncionesFuturasDePelicula(id);
		
		model.put("listaCines", listaCines);
		model.put("listaFuncion", listaFuncion);
		model.put("pelicula", pelicula);
		model.addAttribute(new DatosBuscar());
		
		if (user != null) {
			model.put("usuario", servicioUsuario.consultarUsuario(user));
			return new ModelAndView("pelicula", model);
		}
		return new ModelAndView("pelicula", model);
	}
	
	@RequestMapping(path = "/historialcompras", method = RequestMethod.GET)
	public ModelAndView irAHistorialCompras(HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		model.addAttribute(new DatosBuscar());
		model.put("boletosadquiridos", servicioBoleto.buscarBoletosDeUnUsuario(user));
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(user));
		model.put("usuario", servicioUsuario.consultarUsuario(user));
		return new ModelAndView("historial-compras", model);
	}

}
