package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpAcciones;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpAccionesService {

	public List<DgaEmpAcciones> getAllByEmpleado(Integer idEmpleado);
	
	public DgaEmpAcciones getAccionById(Integer idAccion) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpAcciones accion) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpAcciones accion) throws MtaGenericDAOException, MtaServiceException;
	
	Row eliminar(Integer idAccion) throws MtaGenericDAOException, MtaServiceException;
}
