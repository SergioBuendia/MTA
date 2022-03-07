package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaUsuarios;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaUsuariosDao {

	public MtaUsuarios getUsuarioByUser(String usuario) throws MtaGenericDAOException;;
	
	public List<MtaUsuarios> getAll();
	
	public Integer obtenerSiguienteId() throws MtaQueryException;
	
	Row guardar(MtaUsuarios usuario) throws MtaGenericDAOException;
	
}
