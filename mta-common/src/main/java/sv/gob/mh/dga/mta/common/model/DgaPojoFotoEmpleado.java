package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DgaPojoFotoEmpleado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id_empleado;
	private byte[] foto;

}
