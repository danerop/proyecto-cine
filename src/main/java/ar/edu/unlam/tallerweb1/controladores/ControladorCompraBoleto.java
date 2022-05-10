package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCompraBoleto {

	@RequestMapping(path="/compra", method = RequestMethod.GET )
	public ModelAndView irACompra() {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		return new ModelAndView("compra", modelo);
	}
	@RequestMapping(path="/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		
		
		return new ModelAndView("recibocompra");
	}
}
