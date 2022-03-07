package sv.gob.mh.dga.mta.common.seguridad.model;

import java.util.Date;

public class AuditoriaConfig extends EntidadBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id_auditoria_config;
	Integer id_funcionalidad;
	String url;
	String accion;
	String method;
	Integer session_registro;
	Date fecha_registro;
	public Integer getId_auditoria_config() {
		return id_auditoria_config;
	}
	public void setId_auditoria_config(Integer id_auditoria_config) {
		this.id_auditoria_config = id_auditoria_config;
	}
	public Integer getId_funcionalidad() {
		return id_funcionalidad;
	}
	public void setId_funcionalidad(Integer id_funcionalidad) {
		this.id_funcionalidad = id_funcionalidad;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getSession_registro() {
		return session_registro;
	}
	public void setSession_registro(Integer session_registro) {
		this.session_registro = session_registro;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	 
}