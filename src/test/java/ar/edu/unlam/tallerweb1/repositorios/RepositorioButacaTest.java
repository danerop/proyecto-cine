package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Butaca;

public class RepositorioButacaTest extends SpringTest{
	
	@Autowired
	RepositorioButaca repoButaca;

	@Test
	@Transactional @Rollback
	public void queSePuedaBuscarButacaPorId() {			
		Butaca butaca= new Butaca();
		Butaca butaca2= new Butaca();
		Butaca butaca3= new Butaca();
		
		session().save(butaca);
		session().save(butaca2);
		session().save(butaca3);

		Butaca butacaEsperada = repoButaca.obtenerButaca(butaca2.getId());
		assertNotNull(butacaEsperada);
		assertEquals(butacaEsperada, butaca2);
	}
}
