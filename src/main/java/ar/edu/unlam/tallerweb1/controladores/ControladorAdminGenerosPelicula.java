package ar.edu.unlam.tallerweb1.controladores;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.PeliculaGenero;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGenero;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;

@Controller
public class ControladorAdminGenerosPelicula {
	 
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servicioGenero;
	
	@Autowired
	public ControladorAdminGenerosPelicula(ServicioPelicula servicioPelicula, ServicioGenero servicioGenero) {
		this.servicioGenero=servicioGenero;
		this.servicioPelicula=servicioPelicula;
	}
	
	@RequestMapping( path = "/admin-asignargeneros", method = RequestMethod.GET)
	public ModelAndView irAAdminAsignarGeneros(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosAsignarGeneroPelicula", new DatosAsignarGeneroPelicula());
		modelo.put("listaPeliculas", servicioPelicula.obtenerTodosLasPeliculas());
		modelo.put("listaDeGeneros", servicioGenero.obtenerTodosLosGeneros());
		modelo.put("registrosGeneroPelicula", servicioGenero.obtenerTodosLosRegistrosGeneroPelicula());
		
		return new ModelAndView("admin-asignargeneros", modelo);
	}
	
	@RequestMapping(path = "/validarGenerosAsignados", method = RequestMethod.POST)
	public ModelAndView guardarGenerosFavoritos(HttpServletRequest request, @ModelAttribute("datosAsignarGeneroPelicula") DatosAsignarGeneroPelicula datosAsignarGeneroPelicula){
				
		System.out.println(datosAsignarGeneroPelicula.getIdPelicula());
		for (Long z : datosAsignarGeneroPelicula.getIdsGeneros()) {
			System.out.println(z);
		}
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap model = new ModelMap();
		
		Pelicula peli=servicioPelicula.buscarPeliculaPorID(datosAsignarGeneroPelicula.getIdPelicula());
		if (datosAsignarGeneroPelicula!=null && datosAsignarGeneroPelicula.getIdsGeneros()!=null && datosAsignarGeneroPelicula.getIdsGeneros().size()!=0) {
			Iterator<Long> iter = datosAsignarGeneroPelicula.getIdsGeneros().iterator();
			while(iter.hasNext()) {
				Genero genero = servicioGenero.obtenerGeneroPorid(iter.next());

				PeliculaGenero registrotemp=servicioGenero.obtenerRegistroPorPeliculaYGenero(datosAsignarGeneroPelicula.getIdPelicula(), genero.getId());
				
				if(registrotemp == null) {
					PeliculaGenero registro=new PeliculaGenero();
					registro.setGenero(genero);
					registro.setPelicula(peli);
					servicioGenero.guardarGeneroPelicula(registro);
				} else if (registrotemp.getActivo()==false) {
					registrotemp.setActivo(true);
					servicioGenero.guardarGeneroPelicula(registrotemp);
				} {
					
				}
			}
		}


		return new ModelAndView("redirect:/admin-asignargeneros", model);
	}
	
	@RequestMapping(path = "/eliminargeneropelicula", method = RequestMethod.GET)
	public ModelAndView eliminarrGenerosDePelicula(@RequestParam(value = "g") Long idRegistroPeliGenero, HttpServletRequest request){
				
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		servicioGenero.inactivar(idRegistroPeliGenero);

		return new ModelAndView("redirect:/admin-asignargeneros");
	}
	
}
