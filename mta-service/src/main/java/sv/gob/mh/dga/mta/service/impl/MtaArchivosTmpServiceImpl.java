package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.service.MtaArchivosTmpService;

@Service
public class MtaArchivosTmpServiceImpl implements MtaArchivosTmpService {

	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;
	
	@Override
	public MtaArchivosTmp getMtaArchivosTmpById(Integer idArchivo) throws MtaGenericDAOException {
		return mtaArchivosTmpDao.getMtaArchivosTmpById(idArchivo);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(MtaArchivosTmp archivo) throws MtaGenericDAOException, MtaServiceException {
		try{
			archivo.setIdArchivo(mtaArchivosTmpDao.obtenerSiguienteId());
			archivo.setFechaCreacion(new Date());
			archivo.setEstado(0);
			archivo.setUsuarioCreacion(archivo.getUsuarioCreacion());
			Row row = mtaArchivosTmpDao.guardar(archivo);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el archivo", e);
        }
	}

	@Override
	public Row update(MtaArchivosTmp archivo) throws MtaGenericDAOException, MtaServiceException {
		try{
			archivo.setFechaModificacion(new Date());
			archivo.setEstado(0);
			archivo.setUsuarioModificacion(archivo.getUsuarioModificacion());
			Row row = mtaArchivosTmpDao.update(archivo);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el archivo", e);
        }
	}

}
