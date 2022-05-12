package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

public interface RepositorioBoleto {

	void guardarBoleto(Boleto boleto);
    Boleto buscarBoleto(Long id);
}
