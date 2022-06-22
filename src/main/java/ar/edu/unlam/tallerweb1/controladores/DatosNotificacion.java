package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

public class DatosNotificacion {
	private List<Long> idUsuario;
	private String texto;
	private String titulo;
	
	
	public List<Long> getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(List<Long> idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}

