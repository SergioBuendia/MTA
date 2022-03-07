package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEmblact;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpEmblactService {

	public List<DgaEmpEmblact> getAllByEmpleado(Integer idEmpleado);
	
	public DgaEmpEmblact getEmblactById(Integer idEmblact) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpEmblact emblact) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpEmblact emblact) throws MtaGenericDAOException, MtaServiceException;
	
	Row eliminar(Integer idEmblact) throws MtaGenericDAOException, MtaServiceException;
	
}
