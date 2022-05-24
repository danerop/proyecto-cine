package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatosCompraBoleto {
	private Long idcine;
	private String fecha;
	private String hora;
	private Long idPelicula;
	private Long idUsuario;
	private Long idSala;
	
	
	public DatosCompraBoleto(Long idPelicula, Long idUsuario) {
		super();
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
	}
	public DatosCompraBoleto() {
		
	}
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
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	public Date getDateSql() throws ParseException {
		
		return Date.valueOf(fecha);

	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdSala() {
		return idSala;
	}
	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
	
}
