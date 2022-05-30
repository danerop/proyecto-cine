package ar.edu.unlam.tallerweb1.controladores;

public class DatosLogin {
    private String email;
    private String password;
    private Long idSuscripcion;
    private Long idUsuario;

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

	public Long getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(Long idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
