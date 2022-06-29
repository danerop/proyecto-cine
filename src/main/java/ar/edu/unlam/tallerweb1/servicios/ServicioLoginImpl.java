package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	private RepositorioUsuario repositorioLoginDao;

	@Autowired
	public ServicioLoginImpl(RepositorioUsuario servicioLoginDao){
		this.repositorioLoginDao = servicioLoginDao;
	}

	@Override
	public Usuario buscarUsuario(String email, String password) {
		return repositorioLoginDao.buscarUsuario(email, password);
	}

	@Override
	public Usuario consultarUsuarioPorId(Long id) {
		return repositorioLoginDao.buscarUsuarioPorId(id);
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		return repositorioLoginDao.consultarUsuario(usuario);
	}
	@Override
	public Long insertarUsuario(Usuario usuario) {
		return repositorioLoginDao.insertarUsuario(usuario);
	}

	@Override
	public Usuario consultarUsuarioPorRol(Usuario usuario) {
		return repositorioLoginDao.buscarUsuarioPorRol(usuario);
	}

	@Override
	public Usuario consultarUsuarioPorSuscripcion(Long idSuscripcion) {
		return repositorioLoginDao.buscarUsuarioPorSuscripcionID(idSuscripcion);
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		repositorioLoginDao.modificar(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuariosPorRol(String rol) {
		return repositorioLoginDao.obtenerUsuariosPorRol(rol);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		return repositorioLoginDao.buscar(email);
	}
}
