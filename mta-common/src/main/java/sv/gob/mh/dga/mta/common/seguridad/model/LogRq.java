package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;

public class LogRq implements Serializable {
	
	private Integer id_servicio;
	private Integer id_usuario;
	private String url;
	private String tipo;
	private String mensaje;
	private String descripcion;
	private String fecIni;
	private String fecFin;
	
	private Integer tam;
	private Integer pag;
 
	
	public Integer getId_servicio() {
		return id_servicio;
	}
	
	public void setId_servicio(Integer id_servicio) {
		this.id_servicio = id_servicio;
	}
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(Integer  id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getFecIni() {
		return fecIni;
	}
	
	public void setFecIni(String fecIni) {
		this.fecIni = fecIni;
	}
	
	public String getFecFin() {
		return fecFin;
	}
	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	public Integer getTam() {
		return tam;
	}

	public void setTam(Integer tam) {
		this.tam = tam;
	}

	public Integer getPag() {
		return pag;
	}

	public void setPag(Integer pag) {
		this.pag = pag;
	}

	 
}
