package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.*;

public interface ServicioNotificacion {
	
	List<Notificacion> obtenerTodasLasNotificaciones();
	void registrarNotificacion(Notificacion notificacion);
	void asociarNotificacionAUsuarios(Notificacion notifiacion, List<Long> idsUsuarios);
	List<Usuario> obtenerListaUsuariosActivos();
	List<NotificacionUsuario> obtenerNotificacionesDeUsuario(Usuario usuario);

}
