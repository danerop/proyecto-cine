package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario repositorioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuarioDao){
		this.repositorioUsuarioDao = repositorioUsuarioDao;
	}

	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		return repositorioUsuarioDao.buscarUsuarioPorId(id);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		return repositorioUsuarioDao.buscarUsuarioPorEmail(email);
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		return repositorioUsuarioDao.consultarUsuario(usuario);
	}
	
	@Override
	public void actualizarUsuario(Usuario usuario) {
		repositorioUsuarioDao.actualizarUsuario(usuario);
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		repositorioUsuarioDao.guardarUsuario(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuariosPorRol(String rol) {
		return repositorioUsuarioDao.obtenerUsuariosPorRol(rol);
	}

	@Override
	public List<Usuario> obtenerUsuariosActivos() {
		return repositorioUsuarioDao.obtenerUsuariosActivos();
	}
	@Override
	public void cambiarmodoautomatico(Usuario usuario) {
		if (usuario!=null && usuario.getModoautomatico()!=null) {
			if (usuario.getModoautomatico()) {
				usuario.setModoautomatico(false);
			} else {
				usuario.setModoautomatico(true);
			}
			actualizarUsuario(usuario);
		} else {
			usuario.setModoautomatico(false);
			actualizarUsuario(usuario);
		}
	}
	@Override
	public void seleccionarFuncionAutomatica(Usuario usuario, Funcion funcion) {
		if (usuario!=null && usuario.getModoautomatico()!=null && funcion!=null) {
			usuario.setFuncionModoAutomatico(funcion);
			actualizarUsuario(usuario);
		}
	}

}
