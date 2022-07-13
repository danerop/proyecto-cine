package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioBoleto {

	void guardarBoleto(Boleto boleto);
    Boleto buscarBoleto(Long id);
    Boleto buscarBoletUnicoPorDatos(Long idcliente, Long idfuncion, Long idbutaca);
    public void actualizarBoleto(Boleto boleto);
    public List<Boleto> buscarBoletosDeUnUsuario(Long idUsuario);
	List<Funcion> obtenerFuncionesCompradasPorUsuario(Usuario user);
	void eliminarBoleto(Boleto boleto);
}
