package sv.gob.mh.dga.mta.service;

import java.util.List;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogos;
import sv.gob.mh.dga.mta.common.sql.Row;

public interface MtaCatalogosService {

	public MtaCatalogos getCatalogoById(Integer idCatalogo) throws MtaGenericDAOException;
	
	public List<MtaCatalogos> getAll();
	
	Row guardar(MtaCatalogos catalogo) throws MtaGenericDAOException, MtaServiceException;
	
	Row actualizar(MtaCatalogos catalogo) throws MtaGenericDAOException, MtaServiceException;
}
