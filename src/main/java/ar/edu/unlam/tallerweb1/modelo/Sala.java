package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer cantFilas;
	private Integer cantColumnas;
	
	@Enumerated(EnumType.STRING)
	private TipoDeSala tipo;

	@ManyToOne			/*ManyToMany*/
	private Cine cine;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoDeSala getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeSala tipo) {
		this.tipo = tipo;
	}
	public Cine getCine() {
		return cine;
	}
	public void setCine(Cine cine) {
		this.cine = cine;
	}
	public Integer getCantFilas() {
		return cantFilas;
	}
	public void setCantFilas(Integer cantFilas) {
		this.cantFilas = cantFilas;
	}
	public Integer getCantColumnas() {
		return cantColumnas;
	}
	public void setCantColumnas(Integer cantColumnas) {
		this.cantColumnas = cantColumnas;
	}
	
}
