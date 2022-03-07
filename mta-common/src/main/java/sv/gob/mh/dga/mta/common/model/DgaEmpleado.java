package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaEmpleado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id_empleado;
	private String nombre;
	private Integer IdEstado;
	private String nit;
	private Integer idDepartamento;
	private Integer idMunicipio;
	private String direccion;
	private String telefono;
	private Date fechaNacimiento;
	private String sexo;
	private String idAduana;
	private byte[] foto;
	private Integer tipoEmpleado;
	private String carnet;
	private Date fechaIngreso;
	private String correoElectronico;
	private Integer idCargoNominal;
	private Integer idCargoFuncional;
	private String usuarioSw;
	private String usuarioSmm;
	private Integer estudianteActivo;
	private Integer emblactActivo;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;
	//---------------------------------
	private String aduana;
	private String estado;
	private String cargoFuncional;
	private Date fechaFinInactivo;

}
