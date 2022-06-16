package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

@Service("servicioNotificacion")
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion{

	private RepositorioNotificacion repositorioNotificacionDao;
	private RepositorioNotificacionUsuario repositorioNotificacionUsuarioDao;
	private RepositorioUsuario repositorioUsuarioDao;
	
	@Autowired
	public ServicioNotificacionImpl(RepositorioNotificacion repositorioNotificacionDao, RepositorioNotificacionUsuario repositorioNotificacionUsuarioDao, RepositorioUsuario repositorioUsuarioDao){
		this.repositorioNotificacionDao = repositorioNotificacionDao;
		this.repositorioNotificacionUsuarioDao = repositorioNotificacionUsuarioDao;
		this.repositorioUsuarioDao=repositorioUsuarioDao;
	}

	@Override
	public List<Notificacion> obtenerTodasLasNotificaciones() {
		return repositorioNotificacionDao.obtenerTodasLasNotificaciones();
	}

	@Override
	public void registrarNotificacion(Notificacion notificacion) {
//		Date sqlDate = Date.valueOf();
//		notificacion.setFecha();
		repositorioNotificacionDao.registrarNotificacion(notificacion);	
	}

	@Override
	public void asociarNotificacionAUsuarios(Notificacion notifiacion, List<Long> idsUsuarios) {
		for (Long idUsuario : idsUsuarios) {
			NotificacionUsuario temp=new NotificacionUsuario();
			temp.setNotificacion(notifiacion);
			Usuario user=repositorioUsuarioDao.buscarUsuarioPorId(idUsuario);
			if (user!=null) {
				temp.setUsuario(user);
				repositorioNotificacionUsuarioDao.crearRegistroNotificacionUsuario(temp);
			}
			
		}
		
	}

	@Override
	public List<Usuario> obtenerListaUsuariosActivos() {
		return repositorioUsuarioDao.obtenerTodosLosUsuariosActivos();
	}

	@Override
	public List<NotificacionUsuario> obtenerNotificacionesDeUsuario(Usuario usuario) {
		return repositorioNotificacionUsuarioDao.obtenerNotificacionesDeUnUsuario(usuario);
	}
	
	
}
