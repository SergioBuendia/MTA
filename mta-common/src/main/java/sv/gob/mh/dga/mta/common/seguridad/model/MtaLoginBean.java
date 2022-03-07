package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MtaLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer IntIdUsuario;
    private String StrUsuario;
    private String StrContrasenna;
    private String token;
    private String nombre;
    private String rol;
    private String cargoFuncional;
    private String carnet;
}
