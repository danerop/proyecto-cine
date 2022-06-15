package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;

import com.google.zxing.WriterException;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;

public interface ServicioBoleto {
	public void guardarBoleto(Boleto boleto, ButacaFuncion temp);
	public Boleto buscarBoleto(Long id);
	public Boleto buscarBoletoUnicoPorDatos(Long idCliente, Long idFuncion, Long idButaca);
	public void registrarAsistenciaBoleto(Boleto boleto);
	
}
