package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.DgaPojoFotoEmpleado;
import sv.gob.mh.dga.mta.common.sql.Row;


public interface DgaEmpleadoDao {
	
	public Integer obtenerSiguienteId() throws MtaQueryException;
    
    public List<DgaEmpleado> getAll();
    
    public DgaEmpleado getEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException;
	
	Row guardar(DgaEmpleado empleado) throws MtaGenericDAOException;
	
	Row actualizar(DgaEmpleado empleado) throws MtaGenericDAOException;	
	
	public DgaEmpleado getEmpleadoByUsuario(String usuario) throws MtaGenericDAOException;
	
	public List<DgaEmpleado> getEmpleadosByCargoFuncional(String cargo);
	
	public DgaPojoFotoEmpleado getFotoEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException;
    
}
