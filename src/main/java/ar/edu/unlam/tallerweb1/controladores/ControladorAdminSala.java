package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoDeSala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorAdminSala {

	private ServicioCine servicioCine;
	private ServicioSala servicioSala;
	private ServicioButaca servicioButaca;
	
	@Autowired
	public ControladorAdminSala(ServicioCine servicioCine, ServicioSala servicioSala, ServicioButaca servicioButaca) {
		this.servicioCine = servicioCine;
		this.servicioSala = servicioSala;
		this.servicioButaca = servicioButaca;
	}
	
	@RequestMapping( path = "/admin-salas", method = RequestMethod.GET)
	public ModelAndView irAAdminCargarSala(HttpServletRequest request) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("admin") ) {
			return new ModelAndView("redirect:/inicio");
		}
		
		ModelMap modelo = new ModelMap();
		
		modelo.addAttribute("datosSala", new DatosSala());
		modelo.put("listaCines", servicioCine.obtenerTodosLosCines());
		modelo.put("listaSalas", servicioSala.obtenerTodasLasSalas());
		
		return new ModelAndView("admin-salas", modelo);
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
}
