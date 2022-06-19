package ar.edu.unlam.tallerweb1.servicios;

public class ExceptionPeliculaNoEncontrada extends RuntimeException {
	public ExceptionPeliculaNoEncontrada(String msg) {
		super(msg);
	}
}
