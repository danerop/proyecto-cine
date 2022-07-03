package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Boleto;

public class RepositorioBoletoTest  extends SpringTest{
	
	@Autowired
	RepositorioBoleto repoBoleto;

	@Test
	@Transactional @Rollback
	public void queSePuedaBuscarBoletoPorId() {			
		Boleto boleto=new Boleto();
		Boleto boleto2=new Boleto();
		session().save(boleto);
		session().save(boleto2);
		
		Boleto boletoEsperado= repoBoleto.buscarBoleto(boleto.getId());
		
		assertNotNull(boletoEsperado);
		assertEquals(boletoEsperado, boleto);
	}
	@Test
	@Transactional @Rollback
	public void queSePuedaGuardarUnBoleto() {			
		Boleto boleto=new Boleto();
		
		repoBoleto.guardarBoleto(boleto);
		
		assertNotNull(boleto.getId());
	}


}
