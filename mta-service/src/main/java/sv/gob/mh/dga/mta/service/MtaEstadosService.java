package sv.gob.mh.dga.mta.service;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaEstados;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaEstadosService {

	public MtaEstados getEstadoById(Integer idEstado) throws MtaGenericDAOException;
	
	public List<MtaEstados> getAll();
	
	Row guardar(MtaEstados estado) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(MtaEstados estado) throws MtaGenericDAOException, MtaServiceException;
}
