package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatSubDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatSubDetalleDao;
import sv.gob.mh.dga.mta.service.MtaCatSubDetalleService;

@Service
public class MtaCatSubDetalleServiceImpl implements MtaCatSubDetalleService {

	@Autowired
	private MtaCatSubDetalleDao subDetalleDao;
	
	@Override
	public MtaCatSubDetalle getCatalogoSubDetalleById(Integer idCatSubDetalle) throws MtaGenericDAOException {
		return subDetalleDao.getCatalogoDetalleById(idCatSubDetalle);
	}

	@Override
	public MtaCatSubDetalle getCatalogoSubDetalleByDescripcion(String descripcion) throws MtaGenericDAOException {
		return subDetalleDao.getCatSubDetalleByDescripcion(descripcion);
	}

	@Override
	public List<MtaCatSubDetalle> getAllByDetalle(Integer idCatalogoDetalle) {
		return subDetalleDao.getAllByCatalogoDetalle(idCatalogoDetalle);
	}

	@Override
	public Row guardar(MtaCatSubDetalle catSubDetalle) throws MtaGenericDAOException, MtaServiceException {
		try{
			catSubDetalle.setIdCatSubdetalle(subDetalleDao.obtenerSiguienteId());
			catSubDetalle.setFechaCreacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catSubDetalle.setUsuarioCreacion(1);
			Row row = subDetalleDao.guardar(catSubDetalle);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	public Row actualizar(MtaCatSubDetalle catSubDetalle) throws MtaGenericDAOException, MtaServiceException {
		try{
			catSubDetalle.setFechaModificacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			catSubDetalle.setUsuarioModificacion(1);
			Row row = subDetalleDao.actualizar(catSubDetalle);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

	@Override
	public List<MtaCatSubDetalle> getMunicipiosAll() {
		return subDetalleDao.getMunicipiosAll();
	}

	

}
