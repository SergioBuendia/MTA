package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaEmpAcciones implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idAccion;
	private Integer idEmpleado;
	private Date anio;
	private String idAduana;
	private String descripcion;
	private Integer idArchivo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;
	private Integer motivoAccion;
	private String desMotivoAccion;
	//---------------------------------
	private String aduana;

}
