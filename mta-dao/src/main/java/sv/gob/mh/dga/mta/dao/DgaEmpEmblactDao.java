package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEmblact;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpEmblactDao {

	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpEmblact> getAllByEmpleado(Integer idEmpleado);
    
    public DgaEmpEmblact getEmpEmblactById(Integer idEmblact) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpEmblact empEmblact) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpEmblact empEmblact) throws MtaGenericDAOException;
	
	Integer eliminar(Integer idEmblact) throws MtaGenericDAOException;
    
}
