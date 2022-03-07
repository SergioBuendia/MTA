package sv.gob.mh.dga.mta.service;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaArchivosTmpService {

	public MtaArchivosTmp getMtaArchivosTmpById(Integer idArchivo) throws MtaGenericDAOException;
	
	Row guardar(MtaArchivosTmp archivo) throws MtaGenericDAOException, MtaServiceException;
	
	Row update(MtaArchivosTmp archivo) throws MtaGenericDAOException, MtaServiceException;
	
}
