package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpIncapacidadService {

	public List<DgaEmpIncapacidad> getAllByEmpleado(Integer idEmpleado);
	
	public DgaEmpIncapacidad getIncapacidadById(Integer idIncapacidad) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException, MtaServiceException;
	
	Row eliminar(Integer idIncapacidad) throws MtaGenericDAOException, MtaServiceException;
	
}
