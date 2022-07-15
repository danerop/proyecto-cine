package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.ButacaFuncion;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioButacaFuncion;

@Service("servicioButacaFuncion")
@Transactional
public class ServicioButacaFuncionImpl implements ServicioButacaFuncion{

private RepositorioButacaFuncion repositorioButacaDao; 
	
	@Autowired
	public ServicioButacaFuncionImpl(RepositorioButacaFuncion repositorioButacaDao) {
		this.repositorioButacaDao = repositorioButacaDao;
	}

	@Override
	public void asociarButacasAFuncion(Funcion funcion, List<Butaca> butacas) {
		for (Butaca butaca : butacas) {
			ButacaFuncion temp= new ButacaFuncion();
			temp.setButaca(butaca);
			temp.setFuncion(funcion);
			repositorioButacaDao.guardarButacaFuncion(temp);
		}
		
	}

	@Override
	public List<ButacaFuncion> obtenerButacasPorFuncion(Funcion funcion) {
		
		return repositorioButacaDao.obtenerButacasPorFuncion(funcion);
	}

	@Override
	public ButacaFuncion obtenerPorButacaYFuncion(Funcion funcion, Long idButaca) {
		return repositorioButacaDao.obtenerPorButacaYFuncion(funcion, idButaca);
	}

	@Override
	public void actualizarButacaFuncion(ButacaFuncion butacaFuncion) {
		repositorioButacaDao.actualizarButacaFuncion(butacaFuncion);
		
	}
	
	@Override
	public Boolean isButacaOcupada(Funcion funcion, Long idButaca) {
		ButacaFuncion temp=repositorioButacaDao.obtenerPorButacaYFuncion(funcion, idButaca);
		if (temp==null) {
			return true;
		}
		return temp.getOcupada();
	}
}
