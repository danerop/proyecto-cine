package ar.edu.unlam.tallerweb1.controladores;

public class DatosParam {
	private Long idUsuario;
	private Long idPelicula;
	
	
	
	public DatosParam(Long idUsuario, Long idPelicula) {
		super();
		this.idUsuario = idUsuario;
		this.idPelicula = idPelicula;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	
}

