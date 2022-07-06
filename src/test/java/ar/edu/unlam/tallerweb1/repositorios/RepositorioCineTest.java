package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		List<Cine> listaCines = new ArrayList<Cine>();
		
		agregarNuevoCineALaLista(listaCines, "IMAX");
		agregarNuevoCineALaLista(listaCines, "CINEMAX");
		agregarNuevoCineALaLista(listaCines, "OTRO");
		agregarNuevoCineALaLista(listaCines, "OTRO2");
		
		Iterator<Cine> iter = listaCines.iterator();
		while (iter.hasNext()) {
		    repositorioCine.guardarCine(iter.next());
		}
		
		assertEquals(listaCines,repositorioCine.obtenerTodosLosCines());
	}
	
	public void agregarNuevoCineALaLista(List<Cine> listaCines, String nom) {
		Cine cine = new Cine();
		cine.setNombreLocal(nom);
		listaCines.add(cine);
	}
}
