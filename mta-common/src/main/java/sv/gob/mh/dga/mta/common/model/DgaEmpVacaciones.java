package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaEmpVacaciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idVacacion;
	private Integer idEmpleado;
	private Integer anio;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer idArchivo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;
	private Date reprogramacion;
	private Integer idSolicitante;
	private String nameSolicitante;

}
