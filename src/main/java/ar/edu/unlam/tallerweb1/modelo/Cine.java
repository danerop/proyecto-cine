package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreLocal;
	private String direccion;
	private String telefono;
	private String email;
	private String urlImagenCine;
	
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreLocal() {
		return nombreLocal;
	}
	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrlImagenCine() {
		return urlImagenCine;
	}
	public void setUrlImagenCine (String urlImagenCine) {
		this.urlImagenCine = urlImagenCine;
	}
	
}
