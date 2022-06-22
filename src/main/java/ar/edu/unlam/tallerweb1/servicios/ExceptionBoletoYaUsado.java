package ar.edu.unlam.tallerweb1.servicios;

public class ExceptionBoletoYaUsado extends RuntimeException{
	public ExceptionBoletoYaUsado (String msg) {
		super(msg);
	}
}
