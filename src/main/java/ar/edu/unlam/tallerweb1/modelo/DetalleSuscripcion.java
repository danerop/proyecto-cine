package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetalleSuscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo;
	private Float descuentoEnBoletos;
	private Long cantidadBoletosGratis;
	private Float cuota;
	
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
	public Float getDescuentoEnBoletos() {
		return descuentoEnBoletos;
	}
	public void setDescuentoEnBoletos(Float descuentoEnBoletos) {
		this.descuentoEnBoletos = descuentoEnBoletos;
	}
	public Long getCantidadBoletosGratis() {
		return cantidadBoletosGratis;
	}
	public void setCantidadBoletosGratis(Long cantidadBoletosGratis) {
		this.cantidadBoletosGratis = cantidadBoletosGratis;
	}
	public Float getCuota() {
		return cuota;
	}
	public void setCuota(Float cuota) {
		this.cuota = cuota;
	}
}
