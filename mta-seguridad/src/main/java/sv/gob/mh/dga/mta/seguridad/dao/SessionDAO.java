package sv.gob.mh.dga.mta.seguridad.dao;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.seguridad.model.Session;
import sv.gob.mh.dga.mta.common.sql.Param;

public interface SessionDAO {

	 /**
     * Actualizar o grabar
     *
     * @param session
     * @return Id  ( si es nuevo)
     */
    Integer saveOrUpdate(Session session) throws MtaGenericDAOException;


    /**
     * Obtener session
     *
     * @param id_session Id
     */
    Session get(Integer id_session) throws MtaGenericDAOException;

    /**
     * Obtener session
     *
     * @param token
     */
    Session getByToken(String token) throws MtaGenericDAOException;

    Integer getIdUsuarioByToken(String token) throws MtaGenericDAOException;

    /**
     * Eliminar session
     *
     * @param id_session Id
     */
    void delete(Integer id_session) throws MtaGenericDAOException;

    /**
     * Obtiene session
     *
     * @param id_session Id
     * @param tablas     Tablas que se agrega con inner join
     * @return Session
     */
    Session getFull(Integer id_session, String[] tablas) throws MtaGenericDAOException;

    /**
     * Lista todos los session
     *
     * @return Lista de session
     */
    List<Session> list() throws MtaGenericDAOException;

    /**
     * Obtiene el primer session
     *
     * @param param
     * @return session
     */
    Session getByParams(Param param) throws MtaGenericDAOException;
    
}
