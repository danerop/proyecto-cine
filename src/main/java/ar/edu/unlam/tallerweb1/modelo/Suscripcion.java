package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Suscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	private Double descuentoEnBoletos;
	private Integer cantidadBoletosGratis;
	private Double cuota;
	
	@OneToOne (mappedBy = "suscripcion")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getDescuentoEnBoletos() {
		return descuentoEnBoletos;
	}
	public void setDescuentoEnBoletos(Double descuentoEnBoletos) {
		this.descuentoEnBoletos = descuentoEnBoletos;
	}
	public Integer getCantidadBoletosGratis() {
		return cantidadBoletosGratis;
	}
	public void setCantidadBoletosGratis(Integer cantidadBoletosGratis) {
		this.cantidadBoletosGratis = cantidadBoletosGratis;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadBoletosGratis == null) ? 0 : cantidadBoletosGratis.hashCode());
		result = prime * result + ((cuota == null) ? 0 : cuota.hashCode());
		result = prime * result + ((descuentoEnBoletos == null) ? 0 : descuentoEnBoletos.hashCode());
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
		Suscripcion other = (Suscripcion) obj;
		if (cantidadBoletosGratis == null) {
			if (other.cantidadBoletosGratis != null)
				return false;
		} else if (!cantidadBoletosGratis.equals(other.cantidadBoletosGratis))
			return false;
		if (cuota == null) {
			if (other.cuota != null)
				return false;
		} else if (!cuota.equals(other.cuota))
			return false;
		if (descuentoEnBoletos == null) {
			if (other.descuentoEnBoletos != null)
				return false;
		} else if (!descuentoEnBoletos.equals(other.descuentoEnBoletos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
