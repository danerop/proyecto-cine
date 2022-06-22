package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Boleto;

@Service("servicioRecepcionista")
@Transactional
public class ServicioRecepcionistaImpl implements ServicioRecepcionista{

	@Override
	public Boolean validarFechaBoleto(Boleto boleto) {
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActual = dateObj.format(formatter);
        String fechaDeBoleto=(boleto.getFuncion().getFechaHora().toString());
		return fechaDeBoleto.equals(fechaActual);
	}

	@Override
	public void ConsultarBoletoValido(Boleto boleto) {
		if (boleto==null) {
			throw new ExceptionBoletoInvalido("Boleto no encontrado");
		}
		if (!validarFechaBoleto(boleto)) {
			throw new ExceptionFechaDistinta("La fecha del boleto no corresponde al dia actual");
		}
		if (boleto.getUsado()==true) {
			throw new ExceptionBoletoYaUsado("El boleto ya fue usado");
		}
	}
}
