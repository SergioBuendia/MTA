package sv.gob.mh.dga.mta.service;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.DgaPojoFotoEmpleado;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpleadoService {
	
	public List<DgaEmpleado> getAll();
	
	public DgaEmpleado getEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException;
		
	Row guardar(DgaEmpleado empleado) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(DgaEmpleado empleado) throws MtaGenericDAOException, MtaServiceException;	
	
	public List<DgaEmpleado> getEmpleadosByCargoFuncional(String cargo);	
	
	public DgaPojoFotoEmpleado getFotoEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException;

}
