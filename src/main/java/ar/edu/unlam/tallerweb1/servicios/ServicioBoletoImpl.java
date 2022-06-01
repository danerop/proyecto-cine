package ar.edu.unlam.tallerweb1.servicios;

import java.io.IOException;
import java.nio.file.Paths;

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
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
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
	public void guardarBoleto(Boleto boleto) {
		boleto.getFuncion().setEntradasDisponibles(boleto.getFuncion().getEntradasDisponibles()-1);

		this.repositorioBoletoDao.guardarBoleto(boleto);
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
	public void generarQr(Long idBoleto) throws WriterException, IOException {
		String data="http://localhost:8080/proyecto-cine/validar-boleto?b="+idBoleto;
		String path="C:\\Users\\beybr\\git\\proyecto-cine\\src\\main\\webapp\\img\\boletoqr\\boleto"+idBoleto+".jpg";
		BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
		MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
	}
}
