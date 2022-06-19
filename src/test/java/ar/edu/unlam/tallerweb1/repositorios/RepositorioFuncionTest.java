package ar.edu.unlam.tallerweb1.repositorios;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import ar.edu.unlam.tallerweb1.SpringTest;

public class RepositorioFuncionTest extends SpringTest{

	@Autowired
	private RepositorioFuncion repositorioFuncion;
	
	@Test
	@Transactional @Rollback
	public void test() {
		fail("Not yet implemented");
	}

}
