package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;


import ar.edu.unlam.tallerweb1.modelo.*;

public interface ServicioBoleto {
	
	public void guardarBoleto(Boleto boleto, ButacaFuncion temp);
	public Boleto buscarBoleto(Long id);
	public Boleto buscarBoletoUnicoPorDatos(Long idCliente, Long idFuncion, Long idButaca);
	public void registrarAsistenciaBoleto(Boleto boleto);
	public List<Boleto> buscarBoletosDeUnUsuario(Usuario user);

	public Float aplicarDescuento(Boleto boleto, Float precio);
	public Boolean validarPrecioDeFuncionDelBoleto(Boleto boleto, Float precio);
	public Boolean validarPrecioDeFuncionDelBoleto(Funcion funcion, Float precio, Usuario user);	

	public List<Funcion> obtenerFuncionesCompradasPorUsuario(Usuario user);

	
	public String metodopago(Boleto boleto) throws MPException, MPApiException;
	void cancelarCompraBoleto(Boleto boletoacancelar);
	void actualizarrBoleto(Boleto boletoactualizar);

	public List<Pelicula> obtenerPeliculasDeFuncionesCompradasPorUsuario(Usuario user);
	public Long obtenerCantidadUsuariosQueVieronLaPelicula(Pelicula pelicula);


}
