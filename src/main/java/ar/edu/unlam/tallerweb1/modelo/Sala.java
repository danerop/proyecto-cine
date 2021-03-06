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

	@ManyToOne
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantColumnas == null) ? 0 : cantColumnas.hashCode());
		result = prime * result + ((cantFilas == null) ? 0 : cantFilas.hashCode());
		result = prime * result + ((cine == null) ? 0 : cine.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Sala other = (Sala) obj;
		if (cantColumnas == null) {
			if (other.cantColumnas != null)
				return false;
		} else if (!cantColumnas.equals(other.cantColumnas))
			return false;
		if (cantFilas == null) {
			if (other.cantFilas != null)
				return false;
		} else if (!cantFilas.equals(other.cantFilas))
			return false;
		if (cine == null) {
			if (other.cine != null)
				return false;
		} else if (!cine.equals(other.cine))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}	
}
