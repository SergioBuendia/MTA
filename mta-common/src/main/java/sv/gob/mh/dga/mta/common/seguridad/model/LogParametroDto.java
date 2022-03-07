package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;

public class LogParametroDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_log;
	private String nombre;
	private Object valor;
	
	public LogParametroDto(String nombre, Object valor){
		this.nombre = nombre;
		this.valor= valor;
	}
	
	public Integer getId_log() {
		return id_log;
	}
	
	public void setId_log(Integer id_log) {
		this.id_log = id_log;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Object getValor() {
		return valor;
	}
	
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
}
