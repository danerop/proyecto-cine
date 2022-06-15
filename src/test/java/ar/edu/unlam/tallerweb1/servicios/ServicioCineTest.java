package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCine;

public class ServicioCineTest {

	private RepositorioCine repositorioCine = mock(RepositorioCine.class);
	private ServicioCine servicioCine = new ServicioCineImpl(repositorioCine);
	
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
	
	@Test (expected = ExceptionCineCamposVacios.class)
	public void registroFallidoDeCinePorFaltaDeDatos() {
		Cine cineNuevo = new Cine();
		cineNuevo.setNombreLocal("nombre");
		
		servicioCine.guardarCine(cineNuevo);
	}

}
