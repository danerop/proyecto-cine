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

	@Test
	@Transactional @Rollback
	public void queSeDevuelvanTodosLosCinesRegistrados() {
		registrarNuevoCine("cine 1", "dir", "tel", 0.0, 0.0);
		registrarNuevoCine("cine 2", "dir", "tel", 0.0, 0.0);
		registrarNuevoCine("cine 3", "dir", "tel", 0.0, 0.0);
		registrarNuevoCine("cine 4", "dir", "tel", 0.0, 0.0);
		
		
	}
	
	public void registrarNuevoCine(String nom, String dir, String tel, Double lat, Double lon) {
		Cine cine = new Cine();
		
		cine.setNombreLocal(nom);
		cine.setDireccion(dir);
		cine.setTelefono(tel);
		cine.setLatitud(lat);
		cine.setLongitud(lon);
		
		repositorioCine.guardarCine(cine);
	}
}
