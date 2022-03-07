package sv.gob.mh.dga.mta.dao;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaEstados;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaEstadosDao {
	
	public Integer obtenerSiguienteId() throws MtaQueryException;
	
	public MtaEstados getEstadoById(Integer idEstado) throws MtaGenericDAOException;
	
	public List<MtaEstados> getAll();
	
	Row guardar(MtaEstados estado) throws MtaGenericDAOException;
	
	Row actualizar(MtaEstados estado) throws MtaGenericDAOException;
}
