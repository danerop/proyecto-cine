package ar.edu.unlam.tallerweb1.servicios;

public class ExceptionCineNoEncontrado extends RuntimeException {
	public ExceptionCineNoEncontrado (String msg) {
		super(msg);
	}
}
