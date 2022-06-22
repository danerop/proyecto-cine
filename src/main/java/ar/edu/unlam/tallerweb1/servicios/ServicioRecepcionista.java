package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

public interface ServicioRecepcionista {
	Boolean validarFechaBoleto(Boleto boleto);
	void ConsultarBoletoValido(Boleto boleto);
}
