package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Butaca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean ocupada = false;
	private Integer numFila;
	private Integer numColumna;
	
	@ManyToOne
	private Sala sala;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Boolean getOcupada() {
		return ocupada;
	}
	public void setOcupada(Boolean ocupada) {
		this.ocupada = ocupada;
	}
	public Integer getNumFila() {
		return numFila;
	}
	public void setNumFila(Integer numFila) {
		this.numFila = numFila;
	}
	public Integer getNumColumna() {
		return numColumna;
	}
	public void setNumColumna(Integer numColumna) {
		this.numColumna = numColumna;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numColumna == null) ? 0 : numColumna.hashCode());
		result = prime * result + ((numFila == null) ? 0 : numFila.hashCode());
		result = prime * result + ((ocupada == null) ? 0 : ocupada.hashCode());
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
		Butaca other = (Butaca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numColumna == null) {
			if (other.numColumna != null)
				return false;
		} else if (!numColumna.equals(other.numColumna))
			return false;
		if (numFila == null) {
			if (other.numFila != null)
				return false;
		} else if (!numFila.equals(other.numFila))
			return false;
		if (ocupada == null) {
			if (other.ocupada != null)
				return false;
		} else if (!ocupada.equals(other.ocupada))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}
}
