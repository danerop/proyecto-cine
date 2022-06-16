package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.*;

public interface RepositorioNotificacionUsuario {
	void crearRegistroNotificacionUsuario(NotificacionUsuario notificacionUsuario);
	List<NotificacionUsuario> obtenerTodosLosRegistros();
	List<NotificacionUsuario> obtenerNotificacionesDeUnUsuario(Usuario usuario);
	List<NotificacionUsuario> obtenerRegistrosPorNotificacion(Notificacion notificacion);
	

}
