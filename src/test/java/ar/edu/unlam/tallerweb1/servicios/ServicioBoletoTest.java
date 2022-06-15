package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.mock;

import ar.edu.unlam.tallerweb1.repositorios.*;
	

public class ServicioBoletoTest {
	private RepositorioBoleto repositorioBoleto = mock(RepositorioBoleto.class);
	private ServicioButacaFuncion servicioButacaFuncion = mock(ServicioButacaFuncion.class);
	private ServicioBoleto servicioCine = new ServicioBoletoImpl(repositorioBoleto, servicioButacaFuncion);
}
