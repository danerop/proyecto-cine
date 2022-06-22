package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

public interface RepositorioBoleto {

	void guardarBoleto(Boleto boleto);
    Boleto buscarBoleto(Long id);
    Boleto buscarBoletUnicoPorDatos(Long idcliente, Long idfuncion, Long idbutaca);
    public void actualizarBoleto(Boleto boleto);
    public List<Boleto> buscarBoletosDeUnUsuario(Long idUsuario);
}
