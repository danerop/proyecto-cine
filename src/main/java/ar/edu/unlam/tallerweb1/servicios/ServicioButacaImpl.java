package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
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
		if(ubicaciones != null){
			for (Integer i : ubicaciones) {
				Butaca temp=new Butaca();
				temp.setNroUbicacion(i);
				temp.setSala(sala);
				this.repositorioButacaDao.guardarButaca(temp);
			}
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

	@Override
	public List<Integer> obtenerIntegersButacasPorSala(Sala salaAEditar) {
		List<Butaca> butacas = repositorioButacaDao.obtenerButacasPorSala(salaAEditar.getId());
		List<Integer> butacasInteger = new ArrayList<Integer>();
	
		for(Butaca i : butacas) {
			butacasInteger.add(i.getNroUbicacion());
		}
	
		return butacasInteger;
	}

	@Override
	public void actualizarButacas(List<Integer> ubicacionesNuevas, Sala sala) {
		List<Butaca> butacasPrevias = repositorioButacaDao.obtenerButacasPorSala(sala.getId());
		
		List<Butaca> butacasABorrar = new ArrayList<Butaca>();
		List<Butaca> butacasAGuardar = new ArrayList<Butaca>();
		
		for (Butaca bp : butacasPrevias) {
			if(!ubicacionesNuevas.isEmpty()) {
				if(!ubicacionesNuevas.contains(bp.getNroUbicacion())) {
					butacasABorrar.add(bp);
				} else {
					ubicacionesNuevas.remove(bp.getNroUbicacion());
				}
			} else {
				butacasABorrar.add(bp);
			}
		}
		
		if(ubicacionesNuevas != null) {
			for (Integer i : ubicacionesNuevas) {
				Butaca temp = new Butaca();
				temp.setNroUbicacion(i);
				temp.setSala(sala);
				
				butacasAGuardar.add(temp);
			}
		}
		
		
		for (Butaca bb : butacasABorrar) {
			this.repositorioButacaDao.borrarButaca(bb);
		}
		for (Butaca bg : butacasAGuardar) {
			this.repositorioButacaDao.guardarButaca(bg);
		}
	}

		
}
