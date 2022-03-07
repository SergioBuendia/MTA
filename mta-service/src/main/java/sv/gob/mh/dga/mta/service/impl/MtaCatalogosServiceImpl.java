package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogos;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatalogosDao;
import sv.gob.mh.dga.mta.service.MtaCatalogosService;

@Service
public class MtaCatalogosServiceImpl implements MtaCatalogosService{

	@Autowired
	private MtaCatalogosDao catalogoDao;
	
	@Override
	public MtaCatalogos getCatalogoById(Integer idCatalogo) throws MtaGenericDAOException {
		return catalogoDao.getCatalogoById(idCatalogo);
	}

	@Override
	public List<MtaCatalogos> getAll() {
		return catalogoDao.getAll();
	}

	@Override
	public Row guardar(MtaCatalogos catalogo) throws MtaGenericDAOException, MtaServiceException {
		try{
			catalogo.setIdCatalogo(catalogoDao.obtenerSiguienteId());
			catalogo.setFechaCreacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catalogo.setUsuarioCreacion(1);
			Row row = catalogoDao.guardar(catalogo);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	public Row actualizar(MtaCatalogos catalogo) throws MtaGenericDAOException, MtaServiceException {
		try{
			catalogo.setFechaModificacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catalogo.setUsuarioModificacion(1);
			Row row = catalogoDao.actualizar(catalogo);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

}
