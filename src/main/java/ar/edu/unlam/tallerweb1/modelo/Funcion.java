package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Funcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fechaHora;
	private Float precioMayor;
	private Float precioMenor;


	private Long entradasDisponibles;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	public Cine cine;
	
	@ManyToOne
	private Sala sala;
	
	private String hora;
	
	public Funcion() {
		
	}
	
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
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Long getEntradasDisponibles() {
		return entradasDisponibles;
	}
	public void setEntradasDisponibles(Long entradasDisponibles) {
		this.entradasDisponibles = entradasDisponibles;
	}
	
}
