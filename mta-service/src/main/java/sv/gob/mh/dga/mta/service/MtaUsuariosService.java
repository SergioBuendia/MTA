package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaUsuarios;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaUsuariosService {
	
	Row guardar(MtaUsuarios usuario) throws MtaGenericDAOException, MtaServiceException;
	
	public MtaUsuarios getUsuarioByUser(String usuario) throws MtaGenericDAOException;
	
	public List<MtaUsuarios> getAll();
	
	
}
