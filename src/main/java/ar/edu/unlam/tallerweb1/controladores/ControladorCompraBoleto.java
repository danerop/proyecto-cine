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
		
		Integer cantFilas = servicioSala.buscarSalaPorId(datosCompraBoleto.getIdSala()).getCantFilas();
		Integer cantColumnas = servicioSala.buscarSalaPorId(datosCompraBoleto.getIdSala()).getCantColumnas();
		
		
		//Creador de matriz
		char matrizButacas[][] = new char[cantFilas][cantColumnas];
		for(int i=0; i < matrizButacas.length; i++) {
			for(int j=0; j < matrizButacas[i].length; j++) {
				matrizButacas[i][j] = 'V';	//lugar vacio (sin butaca)
			}
		}
		
		//marcado de butacas disponibles dentro de la matriz
		for(Butaca but: servicioButaca.obtenerButacasPorSala(datosCompraBoleto.getIdSala())){
			int fil = but.getNumFila()-1;
			int col = but.getNumColumna()-1;
			
			if(but.getOcupada()) {
				matrizButacas[fil][col] = 'N';	//butaca no disponible
			} else {
				matrizButacas[fil][col] = 'D';	//butaca disponible
			}
		}
		
		model.put("cantFilas", cantFilas );
		model.put("cantColumnas", cantColumnas );
		model.put("matrizButacas", matrizButacas);
		return new ModelAndView("compra-butaca", model);
	}
	
	@RequestMapping(path="/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@RequestParam(value="p") Long idPelicula,
			  					  @RequestParam(value="u") Long idUsuario,
								  @ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) throws ParseException {


		Funcion funcionElegida=servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getDateSql(), datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());
		ModelMap model = new ModelMap();
		
		if (funcionElegida!=null) {
			
			Boleto boletoAGuardar=new Boleto();
			boletoAGuardar.setFuncion(funcionElegida);
			boletoAGuardar.setPrecio(funcionElegida.getPrecioMayor());
			
			servicioBoleto.guardarBoleto(boletoAGuardar);
			model.put("boletoGenerado", boletoAGuardar);
		}

			
		return new ModelAndView("recibocompra", model);
	}
}
