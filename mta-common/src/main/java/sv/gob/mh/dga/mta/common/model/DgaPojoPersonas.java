package sv.gob.mh.dga.mta.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DgaPojoPersonas {

	private String nit;
	private String nombre;
	private String direccion;
	private String departamento;
	private String municipio;
	private String telefono;
	private String emailRegistroDgii;
	
}
