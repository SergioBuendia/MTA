package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEstudios;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpEstudiosService {

	public List<DgaEmpEstudios> getAllByEmpleado(Integer idEmpleado);
	
	public DgaEmpEstudios getEstudioById(Integer idEstudio) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpEstudios estudio) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpEstudios estudio) throws MtaGenericDAOException, MtaServiceException;
	
	Row eliminar(Integer idEstudio) throws MtaGenericDAOException, MtaServiceException;
	
}
