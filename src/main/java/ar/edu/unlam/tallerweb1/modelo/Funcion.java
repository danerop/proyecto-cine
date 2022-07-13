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
	private String hora;
	private Float precioMayor;
	private Float precioMenor;
	private Integer entradasDisponibles;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	public Cine cine;
	
	@ManyToOne
	private Sala sala;
	
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
	public void setFechaHora(Date fecha) {
		this.fechaHora = fecha;
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
	public Integer getEntradasDisponibles() {
		return entradasDisponibles;
	}
	public void setEntradasDisponibles(Integer entradasDisponibles) {
		this.entradasDisponibles = entradasDisponibles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cine == null) ? 0 : cine.hashCode());
		result = prime * result + ((entradasDisponibles == null) ? 0 : entradasDisponibles.hashCode());
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pelicula == null) ? 0 : pelicula.hashCode());
		result = prime * result + ((precioMayor == null) ? 0 : precioMayor.hashCode());
		result = prime * result + ((precioMenor == null) ? 0 : precioMenor.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcion other = (Funcion) obj;
		if (cine == null) {
			if (other.cine != null)
				return false;
		} else if (!cine.equals(other.cine))
			return false;
		if (entradasDisponibles == null) {
			if (other.entradasDisponibles != null)
				return false;
		} else if (!entradasDisponibles.equals(other.entradasDisponibles))
			return false;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pelicula == null) {
			if (other.pelicula != null)
				return false;
		} else if (!pelicula.equals(other.pelicula))
			return false;
		if (precioMayor == null) {
			if (other.precioMayor != null)
				return false;
		} else if (!precioMayor.equals(other.precioMayor))
			return false;
		if (precioMenor == null) {
			if (other.precioMenor != null)
				return false;
		} else if (!precioMenor.equals(other.precioMenor))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}
	
}
