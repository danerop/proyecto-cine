package ar.edu.unlam.tallerweb1.servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBoleto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFuncion;

@Service("servicioBoleto")
@Transactional
public class ServicioBoletoImpl implements ServicioBoleto{
	private RepositorioBoleto repositorioBoletoDao;
	private ServicioButacaFuncion servicioButacaFuncion;
	
	@Autowired
	public ServicioBoletoImpl(RepositorioBoleto repositorioFuncionDao,ServicioButacaFuncion servicioButacaFuncion){
		this.repositorioBoletoDao = repositorioFuncionDao;
		this.servicioButacaFuncion=servicioButacaFuncion;
	}

	@Override
	public void guardarBoleto(Boleto boleto, ButacaFuncion temp) {	

		if (boleto.getFuncion()==null || boleto.getFuncion().getId()==null || boleto.getFuncion().getEntradasDisponibles()<=0) {
			throw new ExceptionFuncionNoEncontrada("La funci�n de la cual desea reservar boleto no existe");
		}
		if (temp.getFuncion().getId()!=boleto.getFuncion().getId() || temp.getButaca().getId() != boleto.getButaca().getId()) {
			throw new ExceptionDatosBoletoDiferentesARegistroButacaFuncion("Los datos de la butaca seleccionada no corresponden a una v�lida");
		}

		if (boleto.getButaca()==null || temp==null || temp.getOcupada()==true ) {
			throw new ExceptionButacaYaOcupada("La butaca seleccionada ya ha sido ocupada, por favor intente con otra");
		}
		
		
		boleto.getFuncion().setEntradasDisponibles(boleto.getFuncion().getEntradasDisponibles()-1);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dateObj.format(formatter);
		boleto.setFechaComprado(Date.valueOf(fechaActual));
		this.repositorioBoletoDao.guardarBoleto(boleto);
		
		
		temp.setOcupada(true);
		servicioButacaFuncion.actualizarButacaFuncion(temp);
	}

	@Override
	public Boleto buscarBoleto(Long id) {
		return repositorioBoletoDao.buscarBoleto(id);
	}

	@Override
	public Boleto buscarBoletoUnicoPorDatos(Long idCliente, Long idFuncion, Long idButaca) {
		return repositorioBoletoDao.buscarBoletUnicoPorDatos(idCliente, idFuncion, idButaca);
	}

	@Override
	public void registrarAsistenciaBoleto(Boleto boleto) {
		Boleto temp=repositorioBoletoDao.buscarBoleto(boleto.getId());
		temp.setUsado(true);
		repositorioBoletoDao.actualizarBoleto(temp);
	}

	@Override
	public List<Boleto> buscarBoletosDeUnUsuario(Usuario user) {
		return repositorioBoletoDao.buscarBoletosDeUnUsuario(user.getId());
	}

	@Override
	public Float aplicarDescuento(Boleto boleto, Float precio) {
		Float preciofinal=precio;
		
		if (boleto.getCliente()!=null && boleto.getCliente().getSuscripcion()!=null && boleto.getCliente().getSuscripcion().getDetalleSuscripcion()!=null) {
			if (boleto.getCliente().getSuscripcion().getDetalleSuscripcion().getDescuentoEnBoletos()>0) {
				Float descuento=(precio*boleto.getCliente().getSuscripcion().getDetalleSuscripcion().getDescuentoEnBoletos())/100;
				preciofinal-=descuento;
			}	
		}
		
		return preciofinal;
	}

	@Override
	public Boolean validarPrecioDeFuncionDelBoleto(Boleto boleto, Float precio) {
		Boolean res=false;
		if (boleto!=null && boleto.getFuncion()!=null && precio!=null) {
			if (boleto.getFuncion().getPrecioMayor().equals(precio)) {
				res=true;
			}
			if (boleto.getFuncion().getPrecioMenor().equals(precio)) {
				res=true;
			}
		}
		return res;
	}

	@Override
	public Boolean validarPrecioDeFuncionDelBoleto(Funcion funcion, Float precio, Usuario user) {
		Boolean res=false;
		if (funcion!=null && precio!=null) {
			if (funcion.getPrecioMayor().equals(precio)) {
				res=true;
			}
			if (funcion.getPrecioMenor().equals(precio)) {
				res=true;
			}
			Boleto temp=new Boleto();
			temp.setFuncion(funcion);
			temp.setCliente(user);
			if (aplicarDescuento(temp, funcion.getPrecioMayor()).equals(precio)) {
				res=true;
			}
			if (aplicarDescuento(temp, funcion.getPrecioMenor()).equals(precio)) {
				res=true;
			}
		}
		
		return res;
	}

	public List<Funcion> obtenerFuncionesCompradasPorUsuario(Usuario user) {
		return repositorioBoletoDao.obtenerFuncionesCompradasPorUsuario(user);

	}
}
