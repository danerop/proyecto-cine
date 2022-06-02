package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioRecepcionista;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorRecepcionista {

	private ServicioFuncion servicioFuncion;
	private ServicioBoleto servicioBoleto;
	private ServicioLogin servicioUsuario;
	private ServicioPelicula servicioPelicula;
	private ServicioSala servicioSala;
	private ServicioButaca servicioButaca;
	private ServicioRecepcionista servicioRecepcionista;

	@Autowired
	public ControladorRecepcionista(ServicioFuncion servicioFuncion, ServicioBoleto servicioBoleto,
			ServicioLogin servicioUsuario, ServicioPelicula servicioPelicula, ServicioSala servicioSala,
			ServicioButaca servicioButaca, ServicioRecepcionista servicioRecepcionista) {
		this.servicioFuncion = servicioFuncion;
		this.servicioBoleto = servicioBoleto;
		this.servicioUsuario = servicioUsuario;
		this.servicioPelicula = servicioPelicula;
		this.servicioSala = servicioSala;
		this.servicioButaca = servicioButaca;
		this.servicioRecepcionista= servicioRecepcionista;
	}
	
	@RequestMapping(path = "/iniciorecepcionista",method = RequestMethod.GET)
	public ModelAndView irAIniciouRecepcionista(HttpServletRequest request) {
		Usuario temp=(Usuario) request.getSession().getAttribute("usuario");
		if (request.getSession().getAttribute("usuario") != null && temp.getRol().equals("recepcionista") ) {
			ModelMap modelo = new ModelMap();
			return new ModelAndView("recepcionista", modelo);
		}
		return new ModelAndView("redirect:/inicio");

	}
	@RequestMapping(path = "/validar-boleto", method = RequestMethod.POST)
	public ModelAndView validarBoleto(@RequestParam(value = "b") Long idBoleto) {
		ModelMap modelo = new ModelMap();
		modelo.put("b", idBoleto);
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("boletoGenerado", null);
		if (boleto!=null) {
        	modelo.put("fechadistinta", false);
			if (!boleto.getUsado()) {
				modelo.put("boletoGenerado", boleto);
				if (servicioRecepcionista.validarFechaBoleto(boleto)) {
					return new ModelAndView("recepcionista-validarboleto", modelo);
		        }
		        else {
		        	modelo.put("msg", "La entrada no es para la fecha actual");
		        	modelo.put("fechadistinta", true);
					return new ModelAndView("recepcionista-validarboleto", modelo);
				}
			} else {
	        	modelo.put("msg", "La entrada ya fue usada");
	        	modelo.put("fueusado", true);
	        	return new ModelAndView("recepcionista-validarboleto", modelo);
			}
		}
		modelo.put("msg", "La entrada no existe");
		return new ModelAndView("recepcionista-validarboleto", modelo);
	}
	
	@RequestMapping(path = "/registrar-asistencia-boleto", method = RequestMethod.GET)
	public ModelAndView registrarAsistenciaBoleto(@RequestParam(value = "b") Long idBoleto) {
		ModelMap modelo = new ModelMap();
		modelo.put("b", idBoleto);
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		if (boleto!=null && boleto.getUsado()==false) {
			servicioBoleto.registrarAsistenciaBoleto(boleto);
			return new ModelAndView("redirect:boleto-validado", modelo);
		}
		return new ModelAndView("redirect:iniciorecepcionista");
	}
	
	@RequestMapping(path = "/boleto-validado", method = RequestMethod.GET)
	public ModelAndView irABoletoValidado(@RequestParam(value = "b") Long idBoleto) {
		ModelMap modelo = new ModelMap();
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("boletoGenerado", boleto);
		return new ModelAndView("recepcionista-boletovalidado", modelo);
	}
	
}
