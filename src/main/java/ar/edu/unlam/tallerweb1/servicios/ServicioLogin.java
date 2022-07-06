package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario buscarUsuario(String email, String password);
	Usuario consultarUsuarioPorId(Long id);
	Usuario consultarUsuarioPorRol(Usuario usuario);
	Usuario consultarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	Long insertarUsuario(Usuario usuario);
	Usuario consultarUsuarioPorSuscripcion (Long idSuscripcion);
	
	List<Usuario> obtenerUsuariosPorRol(String rol);
	Usuario buscarUsuarioPorEmail(String email);
}
