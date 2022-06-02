package ar.edu.unlam.tallerweb1.controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Message.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;

import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBoleto;
import ar.edu.unlam.tallerweb1.servicios.ServicioButaca;
import ar.edu.unlam.tallerweb1.servicios.ServicioButacaFuncion;
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
	private ServicioButacaFuncion servicioButacaFuncion;

	@Autowired
	public ControladorCompraBoleto(ServicioFuncion servicioFuncion, ServicioBoleto servicioBoleto,
			ServicioLogin servicioUsuario, ServicioPelicula servicioPelicula, ServicioSala servicioSala,
			ServicioButaca servicioButaca, ServicioButacaFuncion servicioButacaFuncion) {
		this.servicioFuncion = servicioFuncion;
		this.servicioBoleto = servicioBoleto;
		this.servicioUsuario = servicioUsuario;
		this.servicioPelicula = servicioPelicula;
		this.servicioSala = servicioSala;
		this.servicioButaca = servicioButaca;
		this.servicioButacaFuncion = servicioButacaFuncion;
	}

	@RequestMapping(path = "/compra", method = RequestMethod.GET)
	public ModelAndView irACompra(@RequestParam(value = "p") Long idPelicula) {
		ModelMap modelo = new ModelMap();
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		modelo.put("funcionesDisponibles", servicioFuncion.obtenerFuncionesPorPelicula(idPelicula));
		modelo.put("cinesDisponibles", servicioFuncion.obtenerCinesDisponiblesParaFunciones(idPelicula));
		modelo.put("p", idPelicula);
		modelo.put("peliculaElegida", servicioPelicula.buscarPeliculaPorID(idPelicula));
		return new ModelAndView("compra", modelo);

	}

	@RequestMapping(path = "/comprar-butaca", method = RequestMethod.POST)
	public ModelAndView irAElegirButaca(@RequestParam(value = "p") Long idPelicula,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		ModelMap model = new ModelMap();
		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getDateSql(),
				datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());
		model.put("butacas", servicioButacaFuncion.obtenerButacasPorFuncion(funcionElegida));
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);

		return new ModelAndView("compra-butaca", model);
	}

	@RequestMapping(path = "/comprar-pago", method = RequestMethod.POST)
	public ModelAndView irAMetodoDePago(@RequestParam(value = "p") Long idPelicula,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		ModelMap model = new ModelMap();
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);

		return new ModelAndView("compra-metodo-pago", model);
	}

	@RequestMapping(path = "/comprar-confirmar", method = RequestMethod.POST)
	public ModelAndView irAConfirmar(@RequestParam(value = "p") Long idPelicula,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {

		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getDateSql(), datosCompraBoleto.getHora(),
				datosCompraBoleto.getIdSala());
		Boleto boletoAGuardar = new Boleto();
		boletoAGuardar.setButaca(servicioButaca.obtenerButaca(datosCompraBoleto.getIdButaca()));
		boletoAGuardar.setFuncion(funcionElegida);
		boletoAGuardar.setPrecio(funcionElegida.getPrecioMayor());

		ModelMap model = new ModelMap();
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);
		model.put("boletoGenerado", boletoAGuardar);
		model.put("funcionElegida", funcionElegida);

		return new ModelAndView("compra-confirmacion", model);
	}

	@RequestMapping(path = "/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@RequestParam(value = "p") Long idPelicula,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws WriterException, IOException {
		ModelAndView ret = new ModelAndView();
		ModelMap model = new ModelMap();
		if (request.getSession().getAttribute("usuario") != null) {

			Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
					datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getDateSql(),
					datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());

			model.put("msg", "La butaca seleccionada ya ha sido ocupada, por favor intente con otra");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			ret = new ModelAndView("redirect:/inicio", model);
			

			if (funcionElegida != null
					&& datosCompraBoleto.getIdButaca()!=null
					&& servicioButacaFuncion.obtenerPorButacaYFuncion(funcionElegida, datosCompraBoleto.getIdButaca()).getOcupada() == false) {
			

				Boleto boletoAGuardar = new Boleto();
				Usuario user = (Usuario) request.getSession().getAttribute("usuario");
				boletoAGuardar.setButaca(servicioButaca.obtenerButaca(datosCompraBoleto.getIdButaca()));
				boletoAGuardar.setFuncion(funcionElegida);
				boletoAGuardar.setPrecio(funcionElegida.getPrecioMayor());
				boletoAGuardar.setCliente(user);

				servicioBoleto.guardarBoleto(boletoAGuardar);
				servicioBoleto.generarQr(boletoAGuardar.getId());
				
				ButacaFuncion temp=servicioButacaFuncion.obtenerPorButacaYFuncion(boletoAGuardar.getFuncion(), boletoAGuardar.getButaca().getId());
				temp.setOcupada(true);
				servicioButacaFuncion.actualizarButacaFuncion(temp);
				
				model.put("boletoGenerado", boletoAGuardar);
				model.put("nuevacompra", true);
				redirectAttributes.addFlashAttribute("mapping1Form", model);

				ret = new ModelAndView("redirect:/recibo?b=" + boletoAGuardar.getId());
			}
		} else {
			model.put("msg", "Debes estar logueado para comprar un boleto");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			ret = new ModelAndView("redirect:/inicio", model);
		}

		return ret;
	}

	@RequestMapping(path = "/recibo", method = RequestMethod.GET)
	public ModelAndView ReciboGenerado(@RequestParam(value = "b") Long idBoleto, HttpServletRequest request,
			@ModelAttribute("mapping1Form") ModelMap model) throws IOException, WriterException {
		ModelMap modelo = new ModelMap();
		if (model != null) {
			modelo.addAllAttributes(model);
		}
		modelo.put("boletoGenerado", servicioBoleto.buscarBoleto(idBoleto));

		return new ModelAndView("compra-recibocompra", modelo);
	}
}
