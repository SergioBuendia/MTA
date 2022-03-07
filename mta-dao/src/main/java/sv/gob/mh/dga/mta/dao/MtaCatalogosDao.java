package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogos;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatalogosDao {

public Integer obtenerSiguienteId() throws MtaQueryException;
	
	public MtaCatalogos getCatalogoById(Integer idCatalogo) throws MtaGenericDAOException;
	
	public List<MtaCatalogos> getAll();
	
	Row guardar(MtaCatalogos catalogo) throws MtaGenericDAOException;
	
	Row actualizar(MtaCatalogos catalogo) throws MtaGenericDAOException;
}
