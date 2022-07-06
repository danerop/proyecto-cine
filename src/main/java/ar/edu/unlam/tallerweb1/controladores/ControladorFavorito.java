package ar.edu.unlam.tallerweb1.controladores;

import java.util.Iterator;
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

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Favorito;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioFavorito;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenero;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

@Controller
public class ControladorFavorito {

	private ServicioFavorito servicioFavorito;
	private ServicioLogin servicioUsuario;
	private ServicioGenero servicioGenero;
	private ServicioNotificacion servicioNotificacion;

	@Autowired
	public ControladorFavorito(ServicioFavorito servicioFavorito, ServicioLogin servicioUsuario, ServicioGenero servicioGenero, ServicioNotificacion servicioNotificacion) {
		this.servicioFavorito = servicioFavorito;
		this.servicioUsuario = servicioUsuario;
		this.servicioGenero = servicioGenero;
		this.servicioNotificacion= servicioNotificacion;
	}
	
	@RequestMapping(path = "/generos", method = RequestMethod.GET)
	public ModelAndView seleccionarGenerosFavoritos(HttpServletRequest request) {
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap model = new ModelMap();
		
		model.put("usuario", servicioUsuario.consultarUsuario(usuarioSesion));
		model.put("notificaciones", servicioNotificacion.obtenerNotificacionesDeUsuario(usuarioSesion));
		model.addAttribute("datosFavoritos", new DatosFavoritos());
		model.put("listaDeGeneros", servicioGenero.obtenerTodosLosGeneros());
		model.put("generosFavoritos", servicioFavorito.obtenerFavoritoPorUsuario(usuarioSesion.getId()));

		return new ModelAndView("generos", model);
	}
	
	@RequestMapping(path = "/validarGenerosFavoritos", method = RequestMethod.POST)
	public ModelAndView guardarGenerosFavoritos(HttpServletRequest request, @ModelAttribute("datosFavoritos") DatosFavoritos datosFavoritos){
				
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			return new ModelAndView("redirect:/login");
		}
		
		ModelMap model = new ModelMap();
		
		
		if (datosFavoritos!=null && datosFavoritos.getIdGeneros()!=null && datosFavoritos.getIdGeneros().size()!=0) {
			Iterator<Long> iter = datosFavoritos.getIdGeneros().iterator();
			while(iter.hasNext()) {
				Genero genero = servicioGenero.obtenerGeneroPorid(iter.next());
				
				Favorito favorito = servicioFavorito.obtenerFavoritoPorUsuarioYGenero(usuarioSesion.getId(), genero.getId());
				
				if(favorito == null) {
					Favorito fav = new Favorito();
					fav.setGenero(genero);
					fav.setUsuario(usuarioSesion);
					servicioFavorito.insertarFavorito(fav);
				} else if (favorito.getActivo()==false) {
					favorito.setActivo(true);
					servicioFavorito.modificarFavorito(favorito);
				} {
					
				}
			}
		}


		return new ModelAndView("redirect:/generos", model);
	}
	@RequestMapping(path = "/eliminargenero", method = RequestMethod.GET)
	public ModelAndView eliminarrGenerosFavoritos(@RequestParam(value = "g") Long idRegistroFavorito, HttpServletRequest request){
				
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		if (usuarioSesion == null) {
			return new ModelAndView("redirect:/login");
		}
		
		servicioFavorito.inactivar(idRegistroFavorito);

		return new ModelAndView("redirect:/generos");
	}
}
