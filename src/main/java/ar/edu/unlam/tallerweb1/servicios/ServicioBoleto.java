package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;

import com.google.zxing.WriterException;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

public interface ServicioBoleto {
	public void guardarBoleto(Boleto boleto);
	public Boleto buscarBoleto(Long id);
	public Boleto buscarBoletoUnicoPorDatos(Long idCliente, Long idFuncion, Long idButaca);
	public void generarQr(Long idBoleto) throws WriterException, IOException;
	public void registrarAsistenciaBoleto(Boleto boleto);
	
}
