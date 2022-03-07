package sv.gob.mh.dga.mta.service;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatalogoDetalleService {

	public MtaCatalogoDetalle getCatalogoDetalleById(Integer idCatDetalle) throws MtaGenericDAOException;
	
	public MtaCatalogoDetalle getCatalogoDetalleByDescripcion(Integer idCatalogo, String descripcion) throws MtaGenericDAOException;
	
	public List<MtaCatalogoDetalle> getAllByCatalogo(Integer idCatalogo);
	
	Row guardar(MtaCatalogoDetalle catDetalle) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(MtaCatalogoDetalle catDetalle) throws MtaGenericDAOException, MtaServiceException;
}
