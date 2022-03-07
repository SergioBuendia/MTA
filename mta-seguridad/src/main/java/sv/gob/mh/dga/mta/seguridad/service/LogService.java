package sv.gob.mh.dga.mta.seguridad.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.LogDto;
import sv.gob.mh.dga.mta.common.seguridad.model.LogParametroDto;
import sv.gob.mh.dga.mta.common.seguridad.model.LogRq;
import sv.gob.mh.dga.mta.common.seguridad.model.QueryDto;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface LogService {

    /**
	 * Consultar por Usuario
	 * 
	 * @param id_usuario
	 * @return List<Row>
	 * @throws MtaServiceException
	 */
	List<Row> consultarPorUsuario(Integer id_usuario) throws MtaServiceException;
   
	/**
	 * Consultar
	 * 
	 * @param dto
	 * @return List<Row>
	 * @throws MtaServiceException
	 */
	QueryDto consultar(LogRq dto) throws MtaServiceException;
	
	/**
	 * Consultar por Id
	 * 
	 * @param id_log
	 * @return List<Row>
	 * @throws MtaServiceException
	 */
    List<Row> consultarPorIdLog(Integer id_log) throws MtaServiceException;

    /**
     * Guardar Log
     * 
     * @param dto
     */
    Integer grabar(LogDto dto) ;

    /**
     * Guardar Log
     * 
     * @param id_servicio
     * @param url
     * @param session
     * @param throwable
     * @param parametros
     */
    void grabar(int id_servicio, String url, int session, Throwable throwable, List<LogParametroDto> parametros, String method);
    
    /**
     * Guardar Log
     * 
     * @param id_servicio
     * @param url
     * @param session
     * @param e
     */
   	void grabar(int id_servicio, String url, int session, Throwable e);

   	/**
   	 * Guardar Log
   	 * 
   	 * @param id_servicio
   	 * @param url
   	 * @param session
   	 * @param e
   	 * @param o
   	 */
   	void grabar(int id_servicio, String url, int session, Throwable e, Object o);

	/**
	 * Guardar Log
	 *
	 * @param id_servicio
	 * @param url
	 * @param session
	 * @param e
	 * @param param
	 */
	void grabar(int id_servicio, String url, int session, Throwable e, String param);

	/**
	 * Guardar Log
	 *
	 * @param id_servicio
	 * @param url
	 * @param session
	 * @param descripcion
	 * @param mensaje
	 */
	void grabar(int id_servicio, String url, int session, String descripcion, String mensaje, String valor);

	List<Row> getLogByFechas(LogRq log) throws MtaServiceException;
   	
   	List<Row> getSesion(Integer session) throws MtaServiceException;
   	
   	List<Row> getFuncionalidad(Integer funcionalidad, Integer sesion) throws MtaServiceException;

   	
}
