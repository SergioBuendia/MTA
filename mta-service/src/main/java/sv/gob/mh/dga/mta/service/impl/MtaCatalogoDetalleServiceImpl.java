package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatalogoDetalleDao;
import sv.gob.mh.dga.mta.service.MtaCatalogoDetalleService;

@Service
public class MtaCatalogoDetalleServiceImpl implements MtaCatalogoDetalleService {

	@Autowired
	private MtaCatalogoDetalleDao catalogoDetalleDao;
	
	@Override
	public MtaCatalogoDetalle getCatalogoDetalleById(Integer idCatDetalle) throws MtaGenericDAOException {
		return catalogoDetalleDao.getCatalogoDetalleById(idCatDetalle);
	}

	@Override
	public List<MtaCatalogoDetalle> getAllByCatalogo(Integer idCatalogo) {
		return catalogoDetalleDao.getAllByCatalogo(idCatalogo);
	}

	@Override
	public Row guardar(MtaCatalogoDetalle catDetalle) throws MtaGenericDAOException, MtaServiceException {
		try{
			catDetalle.setIdCatDetalle(catalogoDetalleDao.obtenerSiguienteId());
			catDetalle.setFechaCreacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catDetalle.setUsuarioCreacion(1);
			Row row = catalogoDetalleDao.guardar(catDetalle);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	public Row actualizar(MtaCatalogoDetalle catDetalle) throws MtaGenericDAOException, MtaServiceException {
		try{
			catDetalle.setFechaModificacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catDetalle.setUsuarioModificacion(1);
			Row row = catalogoDetalleDao.actualizar(catDetalle);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

	@Override
	public MtaCatalogoDetalle getCatalogoDetalleByDescripcion(Integer idCatalogo, String descripcion) throws MtaGenericDAOException {
		return catalogoDetalleDao.getCatalogoDetalleByDescripcion(idCatalogo, descripcion);
	}

}
