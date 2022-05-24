package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

public interface ServicioBoleto {
	public void guardarBoleto(Boleto boleto);
	public Boleto buscarBoleto(Long id);
	
}
