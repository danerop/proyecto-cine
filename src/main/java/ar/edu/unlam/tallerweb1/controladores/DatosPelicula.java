package ar.edu.unlam.tallerweb1.controladores;

public class DatosPelicula {

		private Long id;
		private String nombre;
		private Integer anio;
		private String descripcion;
		private Integer duracion;
		private String urlImagenPelicula;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Integer getAnio() {
			return anio;
		}
		public void setAnio(Integer anio) {
			this.anio = anio;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Integer getDuracion() {
			return duracion;
		}
		public void setDuracion(Integer duracion) {
			this.duracion = duracion;
		}
		public String getUrlImagenPelicula() {
			return urlImagenPelicula;
		}
		public void setUrlImagenPelicula(String urlImagenPelicula) {
			this.urlImagenPelicula = urlImagenPelicula;
		}
		
	}

