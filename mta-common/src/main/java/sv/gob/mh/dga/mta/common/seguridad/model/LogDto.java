package sv.gob.mh.dga.mta.common.seguridad.model;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LogDto implements Serializable {

	private Integer id_servicio;
	private Integer id_usuario;
	private Integer id_session;
	private String url;
	private String tipo;
	private String mensaje;
	private String descripcion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fechaRegistro;

	private List<LogParametroDto> parametros = new ArrayList<LogParametroDto>();

	public Integer getId_servicio() {
		return id_servicio;
	}

	public void setId_servicio(Integer id_servicio) {
		this.id_servicio = id_servicio;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDescripcion(Throwable throwable) {
		/*
		 * String desc = null;
		 * 
		 * if (throwable instanceof MgrServiceException) { String errorCode =
		 * ((MgrServiceException) throwable ).getErrorCode(); if
		 * (errorCode.equals(ErrorCodeConstant.SERV_PARAMETROS_INCORRECTOS) ||
		 * errorCode.equals(ErrorCodeConstant.SERV_VALIDACION) ||
		 * errorCode.equals(ErrorCodeConstant.ERROR_VALIDATION_SERVICIOS)) desc
		 * = ""; } if (desc == null) { Writer result = new StringWriter();
		 * 
		 * PrintWriter printWriter = new PrintWriter(result);
		 * throwable.printStackTrace(printWriter); desc = result.toString(); }
		 * 
		 * this.descripcion = desc;
		 */
		if (throwable!=null){
			try{
				Writer result = new StringWriter();
				PrintWriter printWriter = new PrintWriter(result);
				throwable.printStackTrace(printWriter);
				this.descripcion = result.toString();
			}catch (Throwable throwable1){
				this.descripcion = "Error to parse Throwable" + throwable.getMessage().toString();
			}
		}
	}

	public static String getDescripcion(Throwable throwable) {
		Writer result = new StringWriter();

		PrintWriter printWriter = new PrintWriter(result);

		throwable.printStackTrace(printWriter);

		return result.toString();
	}

	public List<LogParametroDto> getParametros() {
		return parametros;
	}

	public void setParametros(List<LogParametroDto> parametros) {
		this.parametros = parametros;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public void setParametroFromObject(Object o) {
		try {

			if (o == null) {
				this.getParametros().add(new LogParametroDto("parametro", "ES NULO"));

				return;
			}

			BeanWrapper wrapper = new BeanWrapperImpl(o);

			PropertyDescriptor[] pds = wrapper.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : pds) {
				String nombre = propertyDescriptor.getName();

				if (!nombre.equals("class")) {
					Object value = wrapper.getPropertyValue(nombre);
					this.getParametros().add(new LogParametroDto(nombre, value));
				}
			}
		} catch (Exception e) {

		}
	}
}