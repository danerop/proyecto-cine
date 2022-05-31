package ar.edu.unlam.tallerweb1.servicios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBoleto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFuncion;

@Service("servicioBoleto")
@Transactional
public class ServicioBoletoImpl implements ServicioBoleto{
	private RepositorioBoleto repositorioBoletoDao;
	
	@Autowired
	public ServicioBoletoImpl(RepositorioBoleto repositorioFuncionDao){
		this.repositorioBoletoDao = repositorioFuncionDao;
	}

	@Override
	public void guardarBoleto(Boleto boleto) {
		boleto.getButaca().setOcupada(true);
		boleto.getFuncion().setEntradasDisponibles(boleto.getFuncion().getEntradasDisponibles()-1);
		this.repositorioBoletoDao.guardarBoleto(boleto);
//		this.repositorioBoletoDao.buscarBoleto(boleto.getId()).getButaca().setOcupada(true);
//		this.repositorioBoletoDao.buscarBoleto(boleto.getId()).getFuncion().setEntradasDisponibles(boleto.getFuncion().getEntradasDisponibles()-1);
	}

	@Override
	public Boleto buscarBoleto(Long id) {
		return repositorioBoletoDao.buscarBoleto(id);
	}
}
