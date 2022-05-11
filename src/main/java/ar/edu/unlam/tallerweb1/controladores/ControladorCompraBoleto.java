package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorCompraBoleto {
	
	private ServicioFuncion servicioFuncion;
	private ServicioBoleto servicioBoleto;
	private ServicioLogin servicioUsuario;
	
	@Autowired
		public ControladorCompraBoleto(ServicioFuncion servicioFuncion, ServicioBoleto servicioBoleto, ServicioLogin servicioUsuario){
		this.servicioFuncion = servicioFuncion;
		this.servicioBoleto=servicioBoleto;
		this.servicioUsuario=servicioUsuario;
	}

	@RequestMapping(path="/compra", method = RequestMethod.GET )
	public ModelAndView irACompra(@RequestParam(value="p") Long idPelicula,
								  @RequestParam(value="u") Long idUsuario) {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		modelo.put("funcionesDisponibles", servicioFuncion.obtenerFuncionesPorPelicula(idPelicula));
		modelo.put("cinesDisponibles", servicioFuncion.obtenerCinesDisponiblesParaFunciones(idPelicula));
		modelo.put("idusuario", idUsuario);
		return new ModelAndView("compra", modelo);
	}
	@RequestMapping(path="/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto, 
								  @ModelAttribute("idUsuario") Long idUsuario) throws ParseException {
		Boleto boletoAGuardar=new Boleto(3311l, servicioUsuario.consultarUsuarioPorId(idUsuario), servicioFuncion.buscarFuncion(1l), (float) 900.0);
		if (servicioFuncion.obtenerFuncionesPorCineFechaHoraYPelicula(datosCompraBoleto.getIdcine(),datosCompraBoleto.getIdPelicula(), datosCompraBoleto.getDateSql(), datosCompraBoleto.getHora())!=null) {
			
			servicioBoleto.guardarBoleto(boletoAGuardar);
			
		}
		
		
		return new ModelAndView("recibocompra");
	}
}
