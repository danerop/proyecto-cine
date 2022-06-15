package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

public class ServicioFuncionTest {
	private RepositorioFuncion repositorioFuncion = mock(RepositorioFuncion.class);
	private ServicioFuncion servicioFuncion = new ServicioFuncionImpl(repositorioFuncion);

	@Test
	public void registroExitosoDeCine() {
		Cine cineNuevo = new Cine();
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setDireccion("direccion");
		cineNuevo.setTelefono("telefono");
		cineNuevo.setLatitud(-30.0);
		cineNuevo.setLongitud(-60.0);
		
		servicioCine.guardarCine(cineNuevo);
		
		verify(repositorioCine).guardarCine(cineNuevo);
	}
}
