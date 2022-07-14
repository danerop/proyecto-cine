package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.unlam.tallerweb1.controladores.DatosCompraBoleto;

// Clase que modela el concepto de Usuario, la anotacion @Entity le avisa a hibernate que esta clase es persistible
// el paquete ar.edu.unlam.tallerweb1.modelo esta indicado en el archivo hibernateCOntext.xml para que hibernate
// busque entities en el
@Entity
public class Usuario {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// para el resto de los atributo no se usan anotaciones entonces se usa el default de hibernate: la columna se llama igual que
	// el atributo, la misma admite nulos, y el tipo de dato se deduce del tipo de dato de java.
	private String email;
	private String password;
	private String nombre;
	private Boolean activo = false;
	private String urlImagenUsuario;
	private String rol;
	@OneToOne
	private Boleto temp;
	
	@OneToOne
	@Cascade(CascadeType.PERSIST)
	private Suscripcion suscripcion;
	
	private Boolean modoautomatico=false;
	@OneToOne
	private Funcion funcionModoAutomatico;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getUrlImagenUsuario() {
		return urlImagenUsuario;
	}
	public void setUrlImagenUsuario(String urlImagenUsuario) {
		this.urlImagenUsuario = urlImagenUsuario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Suscripcion getSuscripcion() {
		return suscripcion;
	}
	public void setSuscripcion(Suscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}
	

	public Boleto getTemp() {
		return temp;
	}
	public void setTemp(Boleto temp) {
		this.temp = temp;
	}
	
	public Boolean getModoautomatico() {
		return modoautomatico;
	}
	public void setModoautomatico(Boolean modoautomatico) {
		this.modoautomatico = modoautomatico;
	}
	
	public Funcion getFuncionModoAutomatico() {
		return funcionModoAutomatico;
	}
	public void setFuncionModoAutomatico(Funcion funcionModoAutomatico) {
		this.funcionModoAutomatico = funcionModoAutomatico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		result = prime * result + ((urlImagenUsuario == null) ? 0 : urlImagenUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		if (suscripcion == null) {
			if (other.suscripcion != null)
				return false;
		} else if (!suscripcion.equals(other.suscripcion))
			return false;
		if (urlImagenUsuario == null) {
			if (other.urlImagenUsuario != null)
				return false;
		} else if (!urlImagenUsuario.equals(other.urlImagenUsuario))
			return false;
		return true;
	}
}
