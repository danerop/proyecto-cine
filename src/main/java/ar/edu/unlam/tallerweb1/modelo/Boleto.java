package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer nroBoleto;
	private Date fecha;
	private Float precio;
	
	
	@ManyToOne
	private Usuario cliente;
	
	@ManyToOne
	private Funcion funcion;
	
	@ManyToOne
	private Butaca butaca;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNroBoleto() {
		return nroBoleto;
	}
	public void setNroBoleto(Integer nroBoleto) {
		this.nroBoleto = nroBoleto;
	}
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public Funcion getFuncion() {
		return funcion;
	}
	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}
	public Butaca getButaca() {
		return butaca;
	}
	public void setButaca(Butaca butaca) {
		this.butaca = butaca;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
}
