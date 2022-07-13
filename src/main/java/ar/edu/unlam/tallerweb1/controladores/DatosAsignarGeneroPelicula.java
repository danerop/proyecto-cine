package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

public class DatosAsignarGeneroPelicula {
	
	private Long idPelicula;
	private List<Long> idsGeneros;
	
	
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	public List<Long> getIdsGeneros() {
		return idsGeneros;
	}
	public void setIdsGeneros(List<Long> idsGeneros) {
		this.idsGeneros = idsGeneros;
	}
	
}
