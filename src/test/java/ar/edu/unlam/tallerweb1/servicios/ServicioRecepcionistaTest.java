package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPelicula;

public class ServicioRecepcionistaTest {
	private ServicioRecepcionista servicioRecep = new ServicioRecepcionistaImpl();

	@Test
	@Transactional @Rollback
	public void queDevuelvaTrueSiLaFechaEsLaActual() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dateObj.format(formatter);
		
        funcion.setFechaHora(Date.valueOf(fechaActual));
		boleto.setFuncion(funcion);
		
		Boolean poseelafechaactual = servicioRecep.validarFechaBoleto(boleto);
		
		assertTrue(poseelafechaactual);
	}
	@Test
	@Transactional @Rollback
	public void queDevuelvaFalseSiLaFechaNoEsLaActual() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		
        funcion.setFechaHora(Date.valueOf("2022-06-20"));
		boleto.setFuncion(funcion);
		
		Boolean poseelafechaactual = servicioRecep.validarFechaBoleto(boleto);
		
		assertFalse(poseelafechaactual);
	}
	@Test(expected = ExceptionBoletoInvalido.class)
	@Transactional @Rollback
	public void queLanceUnaExcepcionSiElBoletoNoExiste() {
		Boleto boleto=null;

		servicioRecep.ConsultarBoletoValido(boleto);
	}
	@Test(expected = ExceptionFechaDistinta.class)
	@Transactional @Rollback
	public void queLanceUnaExcepcionSiElBoletoNoPoseeLaFechaActual() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		funcion.setFechaHora(Date.valueOf("2022-06-19"));
		boleto.setFuncion(funcion);
		servicioRecep.ConsultarBoletoValido(boleto);
	}
	@Test(expected = ExceptionBoletoYaUsado.class)
	@Transactional @Rollback
	public void queLanceUnaExcepcionSiElBoletoYaFueUsado() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dateObj.format(formatter);
		
		funcion.setFechaHora(Date.valueOf(fechaActual));
		boleto.setFuncion(funcion);
		boleto.setUsado(true);
		servicioRecep.ConsultarBoletoValido(boleto);
	}
	@Test
	@Transactional @Rollback
	public void queNoLanceExcepcionesSiElBoletoCumpleLosRequisitos() {
		Boleto boleto=new Boleto();
		Funcion funcion=new Funcion();
		
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dateObj.format(formatter);
		
		funcion.setFechaHora(Date.valueOf(fechaActual));
		boleto.setFuncion(funcion);
		boleto.setUsado(false);
		servicioRecep.ConsultarBoletoValido(boleto);
		
	}
}
