package sv.gob.mh.dga.mta.service;

import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.MtaLoginBean;

public interface LoginService {

	MtaLoginBean login(String usuario, String clave, String token) throws MtaServiceException;
}
