package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MtaUsuarios implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String usuario;
	private String rol;
	private Date fechaCreacion;

}
