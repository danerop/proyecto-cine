package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.*;
	

public class ServicioBoletoTest {
	private RepositorioBoleto repositorioBoleto = mock(RepositorioBoleto.class);
	private ServicioButacaFuncion servicioButacaFuncion = mock(ServicioButacaFuncion.class);
	private ServicioBoleto servicioBoleto = new ServicioBoletoImpl(repositorioBoleto, servicioButacaFuncion);
	

	@Test
	@Transactional @Rollback
	public void buscarBoletoPorId() {
		Boleto boleto=new Boleto();
		
		servicioBoleto.buscarBoleto(boleto.getId());
		
		verify(repositorioBoleto, times(1)).buscarBoleto(boleto.getId());
	}
	@Test
	@Transactional @Rollback
	public void queSePuedaGuardarUnBoleto() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion registro=new ButacaFuncion();
		boleto.setFuncion(funcion);
		boleto.setButaca(butaca);
		butaca.setId(1l);
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
		registro.setButaca(butaca);
		registro.setFuncion(funcion);
		

		
		servicioBoleto.guardarBoleto(boleto, registro);
		
		verify(repositorioBoleto, times(1)).guardarBoleto(boleto);
		verify(servicioButacaFuncion, times(1)).actualizarButacaFuncion(registro);
	}
	@Test(expected = ExceptionFuncionNoEncontrada.class)
	@Transactional @Rollback
	public void queNoSeGenereUnBoletoSiLaFuncionNoExiste() {
		Boleto boleto=new Boleto();
		Funcion funcion=null;
		Butaca butaca=new Butaca();
		ButacaFuncion registro=new ButacaFuncion();
		boleto.setFuncion(funcion);
		boleto.setButaca(butaca);
		butaca.setId(1l);
		registro.setButaca(butaca);
		registro.setFuncion(funcion);	

		
		servicioBoleto.guardarBoleto(boleto, registro);
	}
	@Test(expected = ExceptionDatosBoletoDiferentesARegistroButacaFuncion.class)
	@Transactional @Rollback
	public void queNoSeGenereUnBoletoSiLaLosDatosNoCoincidenConRegistroButacaFuncion() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion registro=new ButacaFuncion();
		boleto.setFuncion(funcion);
		boleto.setButaca(butaca);
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);

		
		servicioBoleto.guardarBoleto(boleto, registro);
	}
	@Test(expected = ExceptionButacaYaOcupada.class)
	@Transactional @Rollback
	public void queNoSeGenereUnBoletoSiLaButacaYaFueOcupada() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion registro=new ButacaFuncion();
		boleto.setFuncion(funcion);
		boleto.setButaca(butaca);
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
		registro.setOcupada(true);
		registro.setButaca(butaca);
		registro.setFuncion(funcion);
		
		servicioBoleto.guardarBoleto(boleto, registro);
	}
}
