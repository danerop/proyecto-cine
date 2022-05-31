package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;





@Entity
public class Boleto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="FUNCION_ID")
	private Funcion funcion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="BUTACA_ID")
	private Butaca butaca;
	
	private Float precio;



	
	
	public Boleto(Long nroBoleto, Usuario cliente, Funcion funcion, Float precio, Date fecha, Sala sala, Butaca butaca) {
		super();
		this.cliente = cliente;
		this.funcion = funcion;
		this.precio = precio;
		this.butaca=butaca;
	}
	public Boleto() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
}
