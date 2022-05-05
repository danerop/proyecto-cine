package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCompraBoleto {

	@RequestMapping(path="/compra", method = RequestMethod.GET )
	public ModelAndView irACompra() {
		return new ModelAndView("compra");
	}
	@RequestMapping(path="/recibo", method = RequestMethod.GET )
	public ModelAndView irARecibo() {
		return new ModelAndView("recibocompra");
	}
}
