package ar.edu.unlam.tallerweb1.servicios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Boleto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioBoleto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioFuncion;

@Repository("ServicioBoleto")
public class ServicioBoletoImpl implements ServicioBoleto{
	private RepositorioBoleto repositorioBoletoDao;
	
	@Autowired
	public ServicioBoletoImpl(RepositorioBoleto repositorioFuncionDao){
		this.repositorioBoletoDao = repositorioFuncionDao;
	}

	@Override
	public void guardarBoleto(Boleto boleto) {
		this.repositorioBoletoDao.guardarBoleto(boleto);
	}

	@Override
	public Boleto buscarBoleto(Long id) {
		return repositorioBoletoDao.buscarBoleto(id);
	}
}
