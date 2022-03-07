package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

	private String usuario;
    private String clave;
}
