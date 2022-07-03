package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario buscarUsuarioPorId(Long id);
	Usuario buscarUsuarioPorEmail(String email);
	Usuario consultarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	void guardarUsuario(Usuario usuario);
	List<Usuario> obtenerUsuariosPorRol(String rol);
	List<Usuario> obtenerUsuariosActivos();
	
}
