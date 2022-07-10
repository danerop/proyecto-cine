package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

public class DatosSala {
	private Long id;
	private Integer tipo;
	private Long idCine;
	private List<Integer> butacas;
	
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
	public List<Integer> getButacas() {
		return butacas;
	}
	public void setButacas(List<Integer> butacas) {
		this.butacas = butacas;
	}

}
