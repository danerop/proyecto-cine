package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.DetalleSuscripcion;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDetalleSuscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioSuscripcion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorLogin {

	// La anotacion @Autowired indica a Spring que se debe utilizar el contructor
	// como mecanismo de inyecciÃ³n de dependencias,
	// es decir, qeue lo parametros del mismo deben ser un bean de spring y el
	// framewrok automaticamente pasa como parametro
	// el bean correspondiente, en este caso, un objeto de una clase que implemente
	// la interface ServicioLogin,
	// dicha clase debe estar anotada como @Service o @Repository y debe estar en un
	// paquete de los indicados en
	// applicationContext.xml
	private ServicioLogin servicioLogin;
	private ServicioSuscripcion servicioSuscripcion;
	private ServicioDetalleSuscripcion servicioDetalleSuscripcion;

	@Autowired
	public ControladorLogin(ServicioLogin servicioLogin, ServicioSuscripcion servicioSuscripcion, ServicioDetalleSuscripcion servicioDetalleSuscripcion) {
		this.servicioLogin = servicioLogin;
		this.servicioSuscripcion = servicioSuscripcion;
		this.servicioDetalleSuscripcion = servicioDetalleSuscripcion;
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es
	// invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		// Se agrega al modelo un objeto con key 'usuario' para que el mismo sea
		// asociado
		// al model attribute del form que esta definido en la vista 'login'
		model.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando
		// el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma dentro del modelo
		return new ModelAndView("login", model);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con
	// metodo http POST
	// El metodo recibe un objeto Usuario el que tiene los datos ingresados en el
	// form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		
		if (usuarioBuscado != null) {
			switch (usuarioBuscado.getRol()) {
			case "recepcionista":
				request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
				request.getSession().setAttribute("usuario", usuarioBuscado);
				return new ModelAndView("redirect:/iniciorecepcionista");
			case "admin":
				request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
				request.getSession().setAttribute("usuario", usuarioBuscado);
				return new ModelAndView("redirect:/admin");
			default:
				request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
				request.getSession().setAttribute("usuario", usuarioBuscado);
				model.put("sesion", request.getAttribute("usuario"));
				model.put("sesionRol", request.getAttribute("ROL"));
				return new ModelAndView("redirect:/inicio", model);
			}

		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping("/registroUsuario")
	public ModelAndView registro(HttpServletRequest request) {

		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("registro-usuario", modelo);
	}

	@RequestMapping(path = "/procesarRegistro", method = RequestMethod.POST)
	public ModelAndView procesarRegistroUsuario(@ModelAttribute("usuario") Usuario usuario,
			@RequestParam(value = "repassword", required = false) String repassword) {
		// validar password con repassword
		ModelMap modelo = new ModelMap();

		if (servicioLogin.consultarUsuario(usuario) == null) {
			if (usuario.getPassword().equals(repassword)) {
				// guardo en la base
				usuario.setActivo(true);
				usuario.setRol("usuario");
				if (servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(1l)!=null && servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(1l).getTipo().equals("comun")) {
					Suscripcion registrosus=new Suscripcion();
					registrosus.setDetalleSuscripcion(servicioDetalleSuscripcion.obtenerDetalleSuscripcionPorId(1l));
					servicioSuscripcion.guardarSuscripcion(registrosus);
					usuario.setSuscripcion(registrosus);	
				}
				servicioLogin.insertarUsuario(usuario);
				modelo.put("correcto", "Usuario registrado correctamente " + usuario.getEmail());
			} else {
				modelo.put("error", "Las contraseñas no coinciden");
				return new ModelAndView("registro-usuario", modelo);
			}
		} else {
			modelo.put("error", "Ya existe el usuario");
			return new ModelAndView("registro-usuario", modelo);
		}

		return new ModelAndView("login", modelo);
	}

	@RequestMapping("/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la
	// url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/inicio");
	}
}
