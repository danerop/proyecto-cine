package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;

public class DatosFuncion {
	private Long id;
	private String fechaHora;
	private Float precioMayor;
	private Float precioMenor;
	private Long idPelicula;
	private Long idCine;
	private Long idSala;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Float getPrecioMayor() {
		return precioMayor;
	}
	public void setPrecioMayor(Float precioMayor) {
		this.precioMayor = precioMayor;
	}
	public Float getPrecioMenor() {
		return precioMenor;
	}
	public void setPrecioMenor(Float precioMenor) {
		this.precioMenor = precioMenor;
	}
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	public Long getIdCine() {
		return idCine;
	}
	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}
	public Long getIdSala() {
		return idSala;
	}
	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
}
