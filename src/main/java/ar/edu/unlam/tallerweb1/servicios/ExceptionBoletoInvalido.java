package ar.edu.unlam.tallerweb1.servicios;

public class ExceptionBoletoInvalido extends RuntimeException{
	public ExceptionBoletoInvalido (String msg) {
		super(msg);
	}
}
