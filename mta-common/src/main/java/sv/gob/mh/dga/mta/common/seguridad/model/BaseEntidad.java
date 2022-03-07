package sv.gob.mh.dga.mta.common.seguridad.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseEntidad {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
