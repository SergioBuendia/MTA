package sv.gob.mh.dga.mta.seguridad.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.Auditoria;
import sv.gob.mh.dga.mta.common.seguridad.model.AuditoriaConfig;
import sv.gob.mh.dga.mta.common.seguridad.model.ConsultarSesionesDto;

public interface AuditoriaService {

    

    /**
     * Guardar auditoria con objeto de parametros
     * 
     * @param dto
     */
    void grabar(Integer idSession, Integer id_auditoria_config, String dto, String accion, String tipoDato, Integer idFuncionalidad) ;
 

    AuditoriaConfig auditoriaConfigByUrl(Integer id_funcionalidad, String url, String method);

    List<ConsultarSesionesDto> consultar(String menu, String parametros) throws MtaServiceException, MtaQueryException;

    List<Auditoria> listarPorEncriptar(Integer id)throws MtaServiceException;
    
    Integer actualizarContenido(Integer id_auditoria, String contenido);

    Integer getFuncionalidadByName(String acciones_de_usuario);
}