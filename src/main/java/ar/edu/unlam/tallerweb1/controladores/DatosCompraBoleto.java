package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;

public class DatosCompraBoleto {
	private Long idcine;
	private String fecha;
	private Integer hora;
	
	public Long getIdcine() {
		return idcine;
	}
	public void setIdcine(Long idcine) {
		this.idcine = idcine;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
}
