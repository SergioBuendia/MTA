package sv.gob.mh.dga.mta.seguridad.service;

import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.Session;

public interface SessionService {

    Integer create(Session session) throws MtaServiceException;

    Session getSession(Integer id_session) throws MtaServiceException;

    Session getSessionPorToken(String token) throws MtaServiceException;

    Integer getIdUsuarioPorToken(String token) throws MtaServiceException;

    Integer update(Integer id_session) throws MtaServiceException;

    Session getSessionMgr3(Integer id_session) throws MtaServiceException;
}