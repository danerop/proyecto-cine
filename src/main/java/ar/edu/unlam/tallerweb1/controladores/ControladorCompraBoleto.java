package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.*;

@Controller
public class ControladorCompraBoleto {

	private ServicioFuncion servicioFuncion;
	private ServicioBoleto servicioBoleto;
	private ServicioPelicula servicioPelicula;
	private ServicioButaca servicioButaca;
	private ServicioButacaFuncion servicioButacaFuncion;
	private ServicioSuscripcion servicioSuscripcion;
	private ServicioUsuario servicioUsuario;

	@Autowired
	public ControladorCompraBoleto(ServicioFuncion servicioFuncion, ServicioBoleto servicioBoleto,
			ServicioPelicula servicioPelicula, ServicioButaca servicioButaca, ServicioButacaFuncion servicioButacaFuncion,
			ServicioSuscripcion servicioSuscripcion, ServicioUsuario servicioUsuario) {
		this.servicioFuncion = servicioFuncion;
		this.servicioBoleto = servicioBoleto;
		this.servicioPelicula = servicioPelicula;
		this.servicioButaca = servicioButaca;
		this.servicioButacaFuncion = servicioButacaFuncion;
		this.servicioSuscripcion = servicioSuscripcion;
		this.servicioUsuario = servicioUsuario;
	}
	@RequestMapping(path = "/compra", method = RequestMethod.GET)
	public ModelAndView irACompra(@RequestParam(value = "p") Long idPelicula,  HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		List<Funcion> funciones = servicioFuncion.obtenerFuncionesFuturasDePelicula(idPelicula);
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario") || funciones==null) {
			return new ModelAndView("redirect:/inicio");
		}
		
		
		modelo.put("datosCompraBoleto", new DatosCompraBoleto());
		modelo.put("funcionesDisponibles", funciones);
		modelo.put("cinesDisponibles", servicioFuncion.obtenerCinesDisponiblesParaFuncionesFuturas(idPelicula));
		modelo.put("p", idPelicula);
		modelo.put("peliculaElegida", servicioPelicula.buscarPeliculaPorID(idPelicula));
		return new ModelAndView("compra", modelo);

	}
	
	@RequestMapping(path = "/comprar-tipoentrada", method = RequestMethod.POST)
	public ModelAndView irASeleccionarTipoEntrada(@RequestParam(value = "p") Long idPelicula, HttpServletRequest request,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		ModelMap model = new ModelMap();
		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getFecha(),
				datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			return new ModelAndView("redirect:/inicio");
		}
		
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);
		model.put("user", servicioUsuario.buscarUsuarioPorId(user.getId()));
		model.put("funcionElegida", funcionElegida);
		return new ModelAndView("compra-tipoboleto", model);
	}
	
	@RequestMapping(path = "/comprar-butaca", method = RequestMethod.POST)
	public ModelAndView irAElegirButaca(@RequestParam(value = "p") Long idPelicula,	HttpServletRequest request,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			return new ModelAndView("redirect:/inicio");
		}
		
		
		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getFecha(),
				datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());		
		ModelMap model = new ModelMap();
		if (funcionElegida==null) {
			model.put("msg", "La butaca seleccionada no es v�lida");
			return new ModelAndView("redirect:/compra?p="+idPelicula, model);
		}
		model.put("butacas", servicioButacaFuncion.obtenerButacasPorFuncion(funcionElegida));
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);

		return new ModelAndView("compra-butaca", model);
	}

	@RequestMapping(path = "/comprar-pago", method = RequestMethod.POST)
	public ModelAndView irAMetodoDePago(@RequestParam(value = "p") Long idPelicula, HttpServletRequest request,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			return new ModelAndView("redirect:/inicio");
		}

		ModelMap model = new ModelMap();
		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);
		model.put("user", servicioUsuario.buscarUsuarioPorId(user.getId()));
		return new ModelAndView("compra-metodo-pago", model);
	}

	@RequestMapping(path = "/comprar-confirmar", method = RequestMethod.POST)
	public ModelAndView irAConfirmar(@RequestParam(value = "p") Long idPelicula, 
			@RequestParam(name = "ep", defaultValue= "false") Boolean usoEntradaGratis,
			HttpServletRequest request,
			@ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto) {
		ModelMap model = new ModelMap();
				
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			return new ModelAndView("redirect:/inicio");
		} 
		if ((usoEntradaGratis && user.getSuscripcion()==null) || (usoEntradaGratis && servicioUsuario.buscarUsuarioPorId(user.getId()).getSuscripcion().getCantidadDeBoletosGratisRestante()<1)) {
			model.put("msg", "No se cuenta con m�s entradas gratis para usar");
			return new ModelAndView("redirect:/inicio", model);
			
		}
		
		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getFecha(), datosCompraBoleto.getHora(),
				datosCompraBoleto.getIdSala());
		Butaca butaca = servicioButaca.obtenerButaca(datosCompraBoleto.getIdButaca());
		
		if (servicioButacaFuncion.isButacaOcupada(funcionElegida, datosCompraBoleto.getIdButaca())==true) {
			model.put("msg", "La butaca elegida ya fue ocupada");
			return new ModelAndView("redirect:/inicio", model);
		}
		if (!servicioBoleto.validarPrecioDeFuncionDelBoleto(funcionElegida, datosCompraBoleto.getPrecio(), user)) {
			return new ModelAndView("redirect:/inicio");
		}
		
		Boleto boletoAGuardar = new Boleto();
		boletoAGuardar.setButaca(butaca);
		boletoAGuardar.setFuncion(funcionElegida);
		boletoAGuardar.setCliente(servicioUsuario.buscarUsuarioPorId(user.getId()));
		boletoAGuardar.setPrecio(servicioBoleto.aplicarDescuento(boletoAGuardar, datosCompraBoleto.getPrecio()));

		model.put("datosCompraBoleto", datosCompraBoleto);
		model.put("p", idPelicula);
		model.put("user", servicioUsuario.buscarUsuarioPorId(user.getId()));
		model.put("usoEntradaGratis", usoEntradaGratis);
		model.put("boletoGenerado", boletoAGuardar);
		model.put("funcionElegida", funcionElegida);

		return new ModelAndView("compra-confirmacion", model);
	}

	@RequestMapping(path = "/validar-compra", method = RequestMethod.POST)
	public ModelAndView irARecibo(@RequestParam(value = "p") Long idPelicula,
			@RequestParam(name = "ep", defaultValue= "false") Boolean usoEntradaGratis,
			HttpServletRequest request, @ModelAttribute("datosCompraBoleto") DatosCompraBoleto datosCompraBoleto,
			RedirectAttributes redirectAttributes) {
		ModelMap model = new ModelMap();
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			model.put("msg", "Debes estar logueado para comprar un boleto");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			return new ModelAndView("redirect:/inicio", model);
		}
		
		Funcion funcionElegida = servicioFuncion.obtenerFuncionesPorCineFechaHoraSalaYPelicula(
				datosCompraBoleto.getIdcine(), idPelicula, datosCompraBoleto.getFecha(),
				datosCompraBoleto.getHora(), datosCompraBoleto.getIdSala());
		Butaca butaca=servicioButaca.obtenerButaca(datosCompraBoleto.getIdButaca());
		Boleto boletoAGuardar = new Boleto();
		boletoAGuardar.setButaca(butaca);
		boletoAGuardar.setFuncion(funcionElegida);
		boletoAGuardar.setCliente(user);
		// COMPROBAR SI HAY SALDO PARA PAGAR Y EL PRECIO ES CORRECTO
		// medio de pago convencional
		if (!servicioBoleto.validarPrecioDeFuncionDelBoleto(boletoAGuardar, datosCompraBoleto.getPrecio())) {
			return new ModelAndView("redirect:/inicio");
		}
		boletoAGuardar.setPrecio(servicioBoleto.aplicarDescuento(boletoAGuardar, datosCompraBoleto.getPrecio()));
		
		// Uso de entrada gratis
		if ((usoEntradaGratis && user.getSuscripcion()==null) || (usoEntradaGratis && servicioUsuario.buscarUsuarioPorId(user.getId()).getSuscripcion().getCantidadDeBoletosGratisRestante()<1)) {
			model.put("msg", "No te quedan entradas gratis por usar");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			return new ModelAndView("redirect:/inicio", model);
		}
		if (usoEntradaGratis) {
			boletoAGuardar.setFueAdquiridoConEntradaGratis(true);
			boletoAGuardar.setPrecio(null);
		}
		
		//
		

		ButacaFuncion temp=servicioButacaFuncion.obtenerPorButacaYFuncion(funcionElegida, butaca.getId());
		try {
			servicioBoleto.guardarBoleto(boletoAGuardar, temp);
		}  catch (ExceptionFuncionNoEncontrada e) {
			model.put("msg", "La funci�n de la cual desea reservar boleto no existe");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			return new ModelAndView("redirect:/inicio", model);
		} 	catch (ExceptionDatosBoletoDiferentesARegistroButacaFuncion e) {
			model.put("msg", "Los datos de la butaca seleccionada no corresponden a una v�lida");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			return new ModelAndView("redirect:/inicio", model);
		} 	catch (ExceptionButacaYaOcupada e) {
			model.put("msg", "La butaca seleccionada ya ha sido ocupada, por favor intente con otra");
			redirectAttributes.addFlashAttribute("mapping1Form", model);
			return new ModelAndView("redirect:/inicio", model);
		} 
		
		//PROCESAR PAGO
		//medio de pago convencional
		
		//usar entrada gratis
		if (usoEntradaGratis) {
			servicioSuscripcion.usarEntradaGratis(user);
		}
		//
		
		
		model.put("boletoGenerado", boletoAGuardar);
		model.put("nuevacompra", true);
		redirectAttributes.addFlashAttribute("mapping1Form", model);
		return new ModelAndView("redirect:/recibo?b=" + boletoAGuardar.getId());
		
	}

	@RequestMapping(path = "/recibo", method = RequestMethod.GET)
	public ModelAndView ReciboGenerado(@RequestParam(value = "b") Long idBoleto, HttpServletRequest request,
			@ModelAttribute("mapping1Form") ModelMap model){
		ModelMap modelo = new ModelMap();
		
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if (user == null || !user.getRol().equals("usuario")) {
			return new ModelAndView("redirect:/inicio");
		}
		Boleto boleto = servicioBoleto.buscarBoleto(idBoleto);
		if (boleto==null) {
			modelo.put("msg", "Boleto no encontrado");
			return new ModelAndView("redirect:/inicio", modelo);
		}
		if (boleto.getCliente().getId()!=user.getId()) {
			modelo.put("msg", "No puedes acceder al recibo de otros usuarios");
			return new ModelAndView("redirect:/inicio", modelo);
		}
		if (model != null) {
			modelo.addAllAttributes(model);
		}
		modelo.put("boletoGenerado", boleto);
		modelo.put("idBoleto", idBoleto);

		return new ModelAndView("compra-recibocompra", modelo);
	}
}
