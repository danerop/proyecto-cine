package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Butaca;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioButaca;

@Service("servicioButaca")
@Transactional
public class ServicioButacaImpl implements ServicioButaca{

	private RepositorioButaca repositorioButacaDao; 
	
	@Autowired
	public ServicioButacaImpl(RepositorioButaca repositorioButacaDao) {
		this.repositorioButacaDao = repositorioButacaDao;
	}
	
	@Override
	public List<Butaca> obtenerButacasPorSala(Long idSala) {
		return repositorioButacaDao.obtenerButacasPorSala(idSala);
	}

	@Override
	public void guardarButaca(Butaca butaca) {
		this.repositorioButacaDao.guardarButaca(butaca);
	}

	@Override
	public void guardarButacas(List<Integer> ubicaciones, Sala sala) {
		for (Integer i : ubicaciones) {
			Butaca temp=new Butaca();
			temp.setNroUbicacion(i);
			temp.setSala(sala);
			this.repositorioButacaDao.guardarButaca(temp);
		}
	}

	@Override
	public Butaca obtenerButaca(Long idSala) {
		
		return repositorioButacaDao.obtenerButaca(idSala);
	}

	@Override
	public Integer cantidadDeButacasEnSala(Long idSala) {
		
		return repositorioButacaDao.obtenerButacasPorSala(idSala).size();
	}

		
}
