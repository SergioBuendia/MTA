package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpVacacionesDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpVacaciones> getAllByEmpleado(Integer idEmpleado);
    
    public DgaEmpVacaciones getVacacionById(Integer idVacacion) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException;
	
	Integer eliminar(Integer idVacacion) throws MtaGenericDAOException;
	
}
