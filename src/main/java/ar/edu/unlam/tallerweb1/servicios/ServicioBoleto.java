package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;
import java.util.List;

import com.google.zxing.WriterException;

import ar.edu.unlam.tallerweb1.modelo.*;

public interface ServicioBoleto {
	public void guardarBoleto(Boleto boleto, ButacaFuncion temp);
	public Boleto buscarBoleto(Long id);
	public Boleto buscarBoletoUnicoPorDatos(Long idCliente, Long idFuncion, Long idButaca);
	public void registrarAsistenciaBoleto(Boleto boleto);
	public List<Boleto> buscarBoletosDeUnUsuario(Usuario user);
}
