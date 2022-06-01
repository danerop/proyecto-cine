package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class DetalleSuscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	private Double descuentoEnBoletos;
	private Long cantidadBoletosGratis;
	private Double cuota;
	
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
	public Long getCantidadBoletosGratis() {
		return cantidadBoletosGratis;
	}
	public void setCantidadBoletosGratis(Long cantidadBoletosGratis) {
		this.cantidadBoletosGratis = cantidadBoletosGratis;
	}
	public Double getCuota() {
		return cuota;
	}
	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
}
