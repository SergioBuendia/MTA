package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface DgaEmpIncapacidadDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpIncapacidad> getAllByEmpleado(Integer idEmpleado);
    
    public DgaEmpIncapacidad getIncapacidadById(Integer idIncapacidad) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException;
	
	Integer eliminar(Integer idIncapacidad) throws MtaGenericDAOException;
	
}
