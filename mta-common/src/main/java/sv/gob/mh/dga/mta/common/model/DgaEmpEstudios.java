package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaEmpEstudios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idEstudio;
	private Integer idEmpleado;
	private Integer tipoEstudio;
	private Integer tituloEstudio;
	private String institucionEstudio;
	private Integer ultimoEstudio;
	private Integer idArchivo;
	private Date fechaFinalizacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;
	private String descTipoEstudio;
	private String descTituloEstudio;

}
