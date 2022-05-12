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
	private Long nroBoleto;
	
	@ManyToOne
	private Usuario cliente;
	
	@ManyToOne
	private Funcion funcion;
	
	@ManyToOne
	private Butaca butaca;
	
	private Float precio;

	@ManyToOne
	private Sala sala;

	private Date fecha;
	
	
	public Boleto(Long nroBoleto, Usuario cliente, Funcion funcion, Float precio, Date fecha) {
		super();
		this.nroBoleto = nroBoleto;
		this.cliente = cliente;
		this.funcion = funcion;
		this.precio = precio;
		this.fecha=fecha;
	}
	public Boleto() {
		
	}
	public Boleto(Long nroBoleto, Float precio) {
		this.nroBoleto=nroBoleto;
		this.precio=precio;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNroBoleto() {
		return nroBoleto;
	}
	public void setNroBoleto(Long nroBoleto) {
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
