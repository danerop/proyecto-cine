package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Suscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long cantidadDeBoletosUsados = 0L;
	
	@ManyToOne
	private DetalleSuscripcion detalleSuscripcion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DetalleSuscripcion getDetalleSuscripcion() {
		return detalleSuscripcion;
	}
	public void setDetalleSuscripcion(DetalleSuscripcion detalleSuscripcion) {
		this.detalleSuscripcion = detalleSuscripcion;
	}
	public Long getCantidadDeBoletosUsados() {
		return cantidadDeBoletosUsados;
	}
	public void setCantidadDeBoletosUsados(Long cantidadDeBoletosUsados) {
		this.cantidadDeBoletosUsados = cantidadDeBoletosUsados;
	}
}
