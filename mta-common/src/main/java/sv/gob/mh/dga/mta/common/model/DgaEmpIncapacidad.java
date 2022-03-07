package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaEmpIncapacidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idIncapacidad;
	private Integer idEmpleado;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer idArchivo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;
	private Integer tipoIncapacidad;
	private Integer motivoIncapacidad;
	private Integer permanente;
	private String desTipoIncapacidad;
	private String desMotivoIncapacidad;

}
