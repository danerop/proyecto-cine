package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;

import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Sala;

public class DatosFuncion {
	private Long id;
	private Date fechaHora;
	private Float precioMayor;
	private Float precioMenor;
	private Pelicula pelicula;
	private Cine cine;
	private Sala sala;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fechaHora) {
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
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public Cine getCine() {
		return cine;
	}
	public void setCine(Cine cine) {
		this.cine = cine;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	
}
