package sv.gob.mh.dga.mta.dao;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaArchivosTmpDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public MtaArchivosTmp getMtaArchivosTmpById(Integer idArchivo) throws MtaGenericDAOException;
	
	Row guardar(MtaArchivosTmp archivo) throws MtaGenericDAOException;
	
	Row update(MtaArchivosTmp archivo) throws MtaGenericDAOException;
	
}
