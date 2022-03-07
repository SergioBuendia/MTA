package sv.gob.mh.dga.mta.common.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class DgaEmpEmblact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idEmblact;
	private Integer idEmpleado;
	private String nombreHijo;
	private Date fechaNacimiento;
	private String sexo;
	private Integer idArchivo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;

}
