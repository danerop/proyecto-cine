package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
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
	
	@RequestMapping(path = "/iniciorecepcionista", method = RequestMethod.GET)
	public ModelAndView irAIniciouRecepcionista() {
		ModelMap modelo = new ModelMap();
		return new ModelAndView("inicio-recepcionista", modelo);

	}
	@RequestMapping(path = "/validar-boleto", method = RequestMethod.POST)
	public ModelAndView validarBoleto(@RequestParam(value = "b") Long idBoleto) {
		ModelMap modelo = new ModelMap();
		Boleto boleto=servicioBoleto.buscarBoleto(idBoleto);
		modelo.put("boletoGenerado", null);
		if (boleto!=null) {
			if (!boleto.getUsado()) {
				modelo.put("boletoGenerado", boleto);
				if (servicioRecepcionista.validarFechaBoleto(boleto)) {
					return new ModelAndView("validarboleto-recepcionista", modelo);
		        }
		        else {
		        	modelo.put("msg", "Error, la entrada no es para la fecha actual");
				}
			} else {
	        	modelo.put("msg", "La entrada ya fue usada");
	        	modelo.put("fueusado", true);
			}
			return new ModelAndView("validarboleto-recepcionista", modelo);
		}
		
		modelo.put("msg", "La entrada no existe");
		return new ModelAndView("validarboleto-recepcionista", modelo);
        

	}
	
}
