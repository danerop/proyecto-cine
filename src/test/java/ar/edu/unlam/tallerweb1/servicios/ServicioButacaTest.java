package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;

public class ServicioButacaTest {
	private RepositorioButaca repoButaca=mock(RepositorioButaca.class);
	private ServicioButaca servicioButaca= new ServicioButacaImpl(repoButaca);

	@Test
	@Transactional @Rollback
	public void buscarButacaPorId() {
		Butaca butaca=new Butaca();
		
		servicioButaca.obtenerButaca(butaca.getId());
		
		verify(repoButaca, times(1)).obtenerButaca(butaca.getId());
	}


}
