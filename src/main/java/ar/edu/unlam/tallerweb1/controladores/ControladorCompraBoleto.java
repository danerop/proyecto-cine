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
import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioCine;
import ar.edu.unlam.tallerweb1.servicios.ServicioFuncion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioPelicula;
import ar.edu.unlam.tallerweb1.servicios.ServicioSala;

@Controller
public class ControladorCompraBoleto {
	
	private ServicioFuncion servicioFuncion;
	private ServicioBoleto servicioBoleto;
	private ServicioLogin servicioUsuario;
	private ServicioPelicula servicioPelicula;
	private ServicioSala servicioSala;
	private ServicioButaca servicioButaca;
	
	@Autowired
		public ControladorCompraBoleto(ServicioFuncion servicioFuncion, ServicioBoleto servicioBoleto, ServicioLogin servicioUsuario, ServicioPelicula servicioPelicula, ServicioSala servicioSala, ServicioButaca servicioButaca){
		this.servicioFuncion=servicioFuncion;
		this.servicioBoleto=servicioBoleto;
		this.servicioUsuario=servicioUsuario;
		this.servicioPelicula=servicioPelicula;
		this.servicioSala=servicioSala;
		this.servicioButaca=servicioButaca;
	}

	@RequestMapping(path="/compra", method = RequestMethod.GET )
	public ModelAndView irACompra(@RequestParam(value="p") Long idPelicula,
								  @RequestParam(value="u") Long idUsuario
								  ) {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		modelo.put("funcionesDisponibles", servicioFuncion.obtenerFuncionesPorPelicula(idPelicula));
		modelo.put("cinesDisponibles", servicioFuncion.obtenerCinesDisponiblesParaFunciones(idPelicula));
		modelo.put("p", idPelicula);
		modelo.put("peliculaElegida", servicioPelicula.buscarPeliculaPorID(idPelicula));
		modelo.put("u", idUsuario);
		return new ModelAndView("compra", modelo);
	
	}
	@RequestMapping(path="/comprar-butaca", method = RequestMethod.POST)
	public ModelAndView irAElegirButaca(@RequestParam(value="p") Long idPelicula,
			  					  		@RequestParam(value="u") Long idUsuario,
			  					  		@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) throws ParseException {
		ModelMap model = new ModelMap();
		model.put("butacas", servicioButaca.obtenerButacasPorSala(datosCompraBoleto.getIdSala()));
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);
		model.put("u", idUsuario);
		
		return new ModelAndView("compra-butaca", model);
	}
	
	@RequestMapping(path="/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@RequestParam(value="p") Long idPelicula,
			  					  @RequestParam(value="u") Long idUsuario,
								  @ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) throws ParseException {
		

		Funcion funcionElegida=servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getDateSql(), datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());
		ModelMap model = new ModelMap();
		
		model.put("datosCompraBoleto", datosCompraBoleto);
		if (funcionElegida!=null) {
			
			Boleto boletoAGuardar=new Boleto();
			boletoAGuardar.setButaca(servicioButaca.obtenerButaca(datosCompraBoleto.getIdButaca()));
			boletoAGuardar.setFuncion(funcionElegida);
			boletoAGuardar.setPrecio(funcionElegida.getPrecioMayor());
			
			servicioBoleto.guardarBoleto(boletoAGuardar);
			model.put("boletoGenerado", boletoAGuardar);
		}

			
		return new ModelAndView("recibocompra", model);
	}
}
