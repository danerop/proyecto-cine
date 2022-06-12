package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cine;

public class RepositorioCineTest extends SpringTest{

	@Autowired
	private RepositorioCine repositorioCine;
	
	@Test
	@Transactional @Rollback
	public void queSePuedaGuardarUnCine() {
		
		Cine cineNuevo = new Cine();
		cineNuevo.setNombreLocal("nombre");
		cineNuevo.setDireccion("direccion");
		cineNuevo.setTelefono("telefono");
		cineNuevo.setLatitud(-30.0);
		cineNuevo.setLongitud(-60.0);
		
		repositorioCine.guardarCine(cineNuevo);
		
		assertNotNull(cineNuevo.getId());
	}

}
