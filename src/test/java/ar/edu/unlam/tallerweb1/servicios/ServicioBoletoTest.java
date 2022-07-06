package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;

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
		Funcion otraFuncion=new Funcion();
		Butaca butaca=new Butaca();
		ButacaFuncion registro=new ButacaFuncion();
		registro.setId(1l);
		otraFuncion.setId(2l);
		funcion.setId(1l);
		funcion.setEntradasDisponibles(10);
		boleto.setFuncion(funcion);
		boleto.setButaca(butaca);
		registro.setButaca(butaca);
		registro.setFuncion(otraFuncion);
		
		
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
	@Test
	@Transactional @Rollback
	public void queSePuedaRegistrarAsistenciaBoleto() {
		Boleto boleto=new Boleto();

		when(repositorioBoleto.buscarBoleto(Mockito.anyLong())).thenReturn(boleto);
		servicioBoleto.registrarAsistenciaBoleto(boleto);;
		
		verify(repositorioBoleto, times(1)).actualizarBoleto(boleto);
	}
	@Test
	@Transactional @Rollback
	public void queSeApliquenDescuentosCorrectamente() {
		Boleto boleto=new Boleto();
		Usuario cliente=new Usuario();
		Suscripcion sus=new Suscripcion();
		DetalleSuscripcion detalleSus=new DetalleSuscripcion();
		detalleSus.setDescuentoEnBoletos((float)0);
		sus.setDetalleSuscripcion(detalleSus);
		cliente.setSuscripcion(sus);
		boleto.setCliente(cliente);
		
		Float precio=(float)1500.0;
		Float precionConDescuento20Porciento=(float)1200;
		
		Float precioFinalSinDescuento=servicioBoleto.aplicarDescuento(boleto, precio);
		detalleSus.setDescuentoEnBoletos((float)20);
		Float precioFinalConDescuento20porciento=servicioBoleto.aplicarDescuento(boleto, precio);
		
		assertEquals(precio, precioFinalSinDescuento);
		assertEquals(precionConDescuento20Porciento, precioFinalConDescuento20porciento);
	}
}
