package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatosCompraBoleto {
	private Long idCine;
	private String fecha;
	private String hora;
	private Long idPelicula;
	private Long idUsuario;
	private Long idSala;
	private Long idButaca;
	private Float precio;

	
	
	public DatosCompraBoleto(Long idCine, String fecha, String hora, Long idPelicula, Long idUsuario, Long idSala,
			Long idButaca, Float  precio) {
		super();
		this.idCine = idCine;
		this.fecha = fecha;
		this.hora = hora;
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
		this.idSala = idSala;
		this.idButaca = idButaca;
		this.precio=precio;
	}
	public DatosCompraBoleto() {
		
	}
	public Long getIdcine() {
		return idCine;
	}
	public void setIdcine(Long idcine) {
		this.idCine = idcine;
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
	public Date getDateSql() {
		
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
	public Long getIdButaca() {
		return idButaca;
	}
	public void setIdButaca(Long idButaca) {
		this.idButaca = idButaca;
	}
	public Long getIdCine() {
		return idCine;
	}
	public void setIdCine(Long idCine) {
		this.idCine = idCine;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
}
