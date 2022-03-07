package sv.gob.mh.dga.mta.dao;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatSubDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatSubDetalleDao  {

	public Integer obtenerSiguienteId() throws MtaQueryException;
	
	public MtaCatSubDetalle getCatalogoDetalleById(Integer idCatSubDetalle) throws MtaGenericDAOException;
	
	public MtaCatSubDetalle getCatSubDetalleByDescripcion(String descripcion) throws MtaGenericDAOException;
	
	public List<MtaCatSubDetalle> getAllByCatalogoDetalle(Integer idCatDetalle);
	
	public List<MtaCatSubDetalle> getMunicipiosAll();
	
	Row guardar(MtaCatSubDetalle catalogoSubDetalle) throws MtaGenericDAOException;
	
	Row actualizar(MtaCatSubDetalle catalogoSubDetalle) throws MtaGenericDAOException;

}
