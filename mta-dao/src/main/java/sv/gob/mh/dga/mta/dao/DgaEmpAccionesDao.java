package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpAcciones;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpAccionesDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpAcciones> getAllByEmpleado(Integer idEmpleado);
    
    public DgaEmpAcciones getAccionoById(Integer idAccion) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpAcciones accion) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpAcciones accion) throws MtaGenericDAOException;
	
	Integer eliminar(Integer idAccion) throws MtaGenericDAOException;
    
}
