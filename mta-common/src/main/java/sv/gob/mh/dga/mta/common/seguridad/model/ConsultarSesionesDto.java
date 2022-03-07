package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultarSesionesDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id_session; 
	
	private String codigo_usuario;
	
	private String nombre_usuario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String fecha_registro;
	
	private String direccion_ip;
	
	private Integer NumeroFuncionalidades; 
	
	public ConsultarSesionesDto() {
		
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public String getCodigo_usuario() {
		return codigo_usuario;
	}

	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}

	public String getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public Integer getNumeroFuncionalidades() {
		return NumeroFuncionalidades;
	}

	public void setNumeroFuncionalidades(Integer numeroFuncionalidades) {
		NumeroFuncionalidades = numeroFuncionalidades;
	}

	public String getDireccion_ip() {
		return direccion_ip;
	}

	public void setDireccion_ip(String direccion_ip) {
		this.direccion_ip = direccion_ip;
	}

}
