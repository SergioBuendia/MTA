package sv.gob.mh.dga.mta.common.seguridad.model;

import java.util.Date;

public class Auditoria extends EntidadBase {

	Integer id;
	Integer idSession;
	Integer idFuncionalidad;
	Integer idAuditoriaConfig;
	
	String contenido;
	String accion;
	Date fechaRegistro;
	String tipoDato;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdSession() {
		return idSession;
	}
	public void setIdSession(Integer idSession) {
		this.idSession = idSession;
	}
	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}
	public void setIdFuncionalidad(Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Integer getIdAuditoriaConfig() {
		return idAuditoriaConfig;
	}
	public void setIdAuditoriaConfig(Integer idAuditoriaConfig) {
		this.idAuditoriaConfig = idAuditoriaConfig;
	}
	public String getTipoDato() {
		return tipoDato;
	}
	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}
	
	

}