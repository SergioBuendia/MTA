package sv.gob.mh.dga.mta.common.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudDgaEmpEmblact extends DgaEmpEmblact {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	
	private Integer typeRev;

}
