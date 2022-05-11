package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;

@Controller
public class ControladorCompraBoleto {
	
	private ServicioFuncion servicioFuncion;
	
	@Autowired
		public ControladorCompraBoleto(ServicioFuncion servicioFuncion){
		this.servicioFuncion = servicioFuncion;
	}

	@RequestMapping(path="/compra", method = RequestMethod.GET )
	public ModelAndView irACompra(@RequestParam(value="i") Long idPelicula) {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		modelo.put("funcionesDisponibles", servicioFuncion.obtenerFuncionesPorPelicula(idPelicula));
		return new ModelAndView("compra", modelo);
	}
	@RequestMapping(path="/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		
		
		return new ModelAndView("recibocompra");
	}
}
