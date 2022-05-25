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
	
	
}
