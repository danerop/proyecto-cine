package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioBoleto {

	void guardarBoleto(Boleto boleto);
    Boleto buscarBoleto(Long id);
    Boleto buscarBoletUnicoPorDatos(Long idcliente, Long idfuncion, Long idbutaca);
    void actualizarBoleto(Boleto boleto);
    List<Boleto> buscarBoletosDeUnUsuario(Long idUsuario);
	List<Funcion> obtenerFuncionesCompradasPorUsuario(Usuario user);

	void eliminarBoleto(Boleto boleto);

	List<Pelicula> obtenerPeliculasDeFuncionesCompradasPorUsuario(Usuario user);
	Long obtenerCantidadUsuariosQueVieronLaPelicula(Pelicula pelicula);

}
