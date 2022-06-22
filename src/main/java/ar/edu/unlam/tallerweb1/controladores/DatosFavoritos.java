package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

public class DatosFavoritos {

	private Long idGenero;
	private Long idUsuario;
	private List<Long> idGeneros;
	
	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public List<Long> getIdGeneros() {
		return idGeneros;
	}
	public void setIdGeneros(List<Long> idGeneros) {
		this.idGeneros = idGeneros;
	}
}
