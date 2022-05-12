package ar.edu.unlam.tallerweb1.controladores;

public class DatosSala {
	private Long id;
	private Integer tipo;
	private Long idCine;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Long getIdCine() {
		return idCine;
	}
	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}
}
