package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEstudios;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpEstudiosDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpEstudios> getAllByEmpleado(Integer idEstudio);
    
    public DgaEmpEstudios getEstudioById(Integer idEstudio) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpEstudios estudio) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpEstudios estudio) throws MtaGenericDAOException;
	
	Integer eliminar(Integer idEstudio) throws MtaGenericDAOException;
    
}
