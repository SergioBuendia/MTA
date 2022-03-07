package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpVacacionesService {

	public List<DgaEmpVacaciones> getAllByEmpleado(Integer idEmpleado);
	
	public DgaEmpVacaciones getVacacionById(Integer idVacacion) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException, MtaServiceException;
	
	Row eliminar(Integer idVacacion) throws MtaGenericDAOException, MtaServiceException;
	
}
