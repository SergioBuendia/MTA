package sv.gob.mh.dga.mta.dao;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatalogoDetalleDao  {

	public Integer obtenerSiguienteId() throws MtaQueryException;
	
	public MtaCatalogoDetalle getCatalogoDetalleById(Integer idCatalogoDetalle) throws MtaGenericDAOException;
	
	public MtaCatalogoDetalle getCatalogoDetalleByDescripcion(Integer idCatalogo, String descripcion) throws MtaGenericDAOException;
	
	public List<MtaCatalogoDetalle> getAllByCatalogo(Integer idCatalogo);
	
	Row guardar(MtaCatalogoDetalle catalogoDetalle) throws MtaGenericDAOException;
	
	Row actualizar(MtaCatalogoDetalle catalogoDetalle) throws MtaGenericDAOException;

}
