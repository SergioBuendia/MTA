package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaEstados;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaEstadosDao;
import sv.gob.mh.dga.mta.service.MtaEstadosService;

@Service
public class MtaEstadosServiceImpl implements MtaEstadosService{

	@Autowired
	private MtaEstadosDao estadosDao;
		
	@Override
	public MtaEstados getEstadoById(Integer idEstado) throws MtaGenericDAOException{
		return estadosDao.getEstadoById(idEstado);
	}

	@Override
	public List<MtaEstados> getAll() {
		return estadosDao.getAll();
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(MtaEstados estado) throws MtaGenericDAOException, MtaServiceException {
		try{
			estado.setIdEstado(estadosDao.obtenerSiguienteId());
			estado.setFechaCreacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			estado.setUsuarioCreacion(1);
			Row row = estadosDao.guardar(estado);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row actualizar(MtaEstados estado) throws MtaGenericDAOException, MtaServiceException {
		try{
			estado.setFechaModificacion(new Date());
			//TODO: AGREGAR METODO PARA OBTENER ID DE USUARIO
			estado.setUsuarioModificacion(1);
			Row row = estadosDao.actualizar(estado);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

}
