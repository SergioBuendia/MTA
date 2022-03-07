package sv.gob.mh.dga.mta.service;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatSubDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatSubDetalleService {

	public MtaCatSubDetalle getCatalogoSubDetalleById(Integer idCatSubDetalle) throws MtaGenericDAOException;
	
	public MtaCatSubDetalle getCatalogoSubDetalleByDescripcion(String descripcion) throws MtaGenericDAOException;
	
	public List<MtaCatSubDetalle> getAllByDetalle(Integer idCatalogoDetalle);
	
	public List<MtaCatSubDetalle> getMunicipiosAll();
	
	Row guardar(MtaCatSubDetalle catSubDetalle) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(MtaCatSubDetalle catSubDetalle) throws MtaGenericDAOException, MtaServiceException;
}
