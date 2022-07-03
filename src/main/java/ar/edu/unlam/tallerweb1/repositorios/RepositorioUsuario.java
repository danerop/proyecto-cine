package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	/*
	Usuario buscarUsuario(String email, String password);
	void guardar(Usuario usuario);
    Usuario buscar(String email);
	void modificar(Usuario usuario);
	Usuario buscarUsuarioPorId(Long id);
	Usuario buscarUsuarioPorRol(Usuario usuario);
	Usuario consultarUsuario(Usuario usuario);
	Long insertarUsuario(Usuario usuario);
	Usuario buscarPorSuscripcion(String tipoSuscripcion);	
	Usuario buscarUsuarioPorSuscripcionID(Long idSuscripcion);
	void setSessionFactory(SessionFactory sessionFactory);
	SessionFactory getSessionFactory();
	List<Usuario> obtenerTodosLosUsuariosActivos();
	List<Usuario> obtenerUsuariosPorRol(String rol);
	*/
	
	Usuario buscarUsuarioPorId(Long id);
	Usuario buscarUsuarioPorEmail(String email);
	Usuario consultarUsuario(Usuario usuario);
	void actualizarUsuario(Usuario usuario);
	void guardarUsuario(Usuario usuario);
	List<Usuario> obtenerUsuariosPorRol(String rol);
	List<Usuario> obtenerUsuariosActivos();
	
}
