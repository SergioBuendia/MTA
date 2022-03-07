package sv.gob.mh.dga.mta.common.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MtaCatalogoDetalle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idCatDetalle;
	private Integer idCatalogo;
	private String descripcion;
	private String valor;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private Integer usuarioCreacion;
	private Integer usuarioModificacion;

}
