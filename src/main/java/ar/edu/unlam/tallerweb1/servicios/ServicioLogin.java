package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario buscarUsuario(String email, String password);
	Usuario consultarUsuarioPorId(Long id);
	Usuario consultarUsuarioPorRol(String rol);
	Usuario consultarUsuario(Usuario usuario);
	Long insertarUsuario(Usuario usuario);
	Usuario consultarUsuarioPorRolEmail(String rol, String email);
	
}
