package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Suscripcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private DetalleSuscripcion detalleSuscripcion;
	
	private Long cantidadDeBoletosGratisRestantes = 0l;
	
	
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
	public Long getCantidadDeBoletosGratisRestante() {
		return cantidadDeBoletosGratisRestantes;
	}
	public void setCantidadDeBoletosGratisRestantes(Long cantidadDeBoletosUsados) {
		this.cantidadDeBoletosGratisRestantes = cantidadDeBoletosUsados;
	}
}
