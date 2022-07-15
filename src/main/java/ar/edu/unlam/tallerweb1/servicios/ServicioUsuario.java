package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {
	Usuario buscarUsuarioPorId(Long id);
	Usuario buscarUsuarioPorEmail(String email);
	Usuario consultarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	void guardarUsuario(Usuario usuario);
	List<Usuario> obtenerUsuariosPorRol(String rol);
	List<Usuario> obtenerUsuariosActivos();
	void cambiarmodoautomatico(Usuario usuario);
	void seleccionarFuncionAutomatica(Usuario usuario, Funcion funcion);
}
