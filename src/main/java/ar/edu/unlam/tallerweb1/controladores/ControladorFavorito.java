package ar.edu.unlam.tallerweb1.controladores;

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
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

@Controller
public class ControladorFavorito {

	private ServicioFavorito servicioFavorito;
	private ServicioLogin servicioUsuario;
	private ServicioGenero servicioGenero;

	@Autowired
	public ControladorFavorito(ServicioFavorito servicioFavorito, ServicioLogin servicioUsuario, ServicioGenero servicioGenero) {
		this.servicioFavorito = servicioFavorito;
		this.servicioUsuario = servicioUsuario;
		this.servicioGenero = servicioGenero;
	}
	
	@RequestMapping(path = "/generos", method = RequestMethod.GET)
	public ModelAndView seleccionarGenerosFavoritos() {
		
		ModelMap model = new ModelMap();
		
		model.addAttribute("datosFavoritos", new DatosFavoritos());
		model.put("listaDeGeneros", servicioGenero.obtenerTodosLosGeneros());

		return new ModelAndView("generos", model);
	}
	
	@RequestMapping(path = "/validarGenerosFavoritos", method = RequestMethod.POST)
	public ModelAndView guardarGenerosFavoritos(HttpServletRequest request,
										   		@ModelAttribute("datosFavoritos") DatosFavoritos datosFavoritos){
				
				ModelMap model = new ModelMap();
				
				Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
				
				if (usuarioSesion != null) {
					
					Genero g = new Genero();
					g.setId(datosFavoritos.getIdGenero());
					
					Favorito favorito = new Favorito();
					favorito.setGenero(g);
					favorito.setUsuario(usuarioSesion);
					servicioFavorito.modificarFavorito(favorito);
					
					model.addAttribute("datosFavoritos", datosFavoritos);
					model.put("favoritoElegido", servicioFavorito.buscarFavoritoPorId(favorito.getId()));
					
					return new ModelAndView("generos-favoritos", model);
				}
		
				return new ModelAndView("redirect:/login", model);
	}
	
}
