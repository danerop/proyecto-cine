package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	private Boolean usado=false;
	
	private Date fechaComprado;
	
	private Boolean fueAdquiridoConEntradaGratis=false;

	
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
	public Date getFechaComprado() {
		return fechaComprado;
	}
	public void setFechaComprado(Date fechaComprado) {
		this.fechaComprado = fechaComprado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((butaca == null) ? 0 : butaca.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((funcion == null) ? 0 : funcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
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
		Boleto other = (Boleto) obj;
		if (butaca == null) {
			if (other.butaca != null)
				return false;
		} else if (!butaca.equals(other.butaca))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (funcion == null) {
			if (other.funcion != null)
				return false;
		} else if (!funcion.equals(other.funcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Boolean getUsado() {
		return usado;
	}
	public void setUsado(Boolean usado) {
		this.usado = usado;
	}
	public Boolean getFueAdquiridoConEntradaGratis() {
		return fueAdquiridoConEntradaGratis;
	}
	public void setFueAdquiridoConEntradaGratis(Boolean fueAdquiridoConEntradaGratis) {
		this.fueAdquiridoConEntradaGratis = fueAdquiridoConEntradaGratis;
	}
	
}
