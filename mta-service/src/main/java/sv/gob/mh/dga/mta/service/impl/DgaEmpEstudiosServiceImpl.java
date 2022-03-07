package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEstudios;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpEstudiosDao;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.service.DgaEmpEstudiosService;

@Service
public class DgaEmpEstudiosServiceImpl implements DgaEmpEstudiosService{
	
	@Autowired
	private DgaEmpEstudiosDao estudiosDao;
	
	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;

	@Override
	public List<DgaEmpEstudios> getAllByEmpleado(Integer idEmpleado) {
		return estudiosDao.getAllByEmpleado(idEmpleado);
	}

	@Override
	public DgaEmpEstudios getEstudioById(Integer idEstudio) throws MtaGenericDAOException {
		return estudiosDao.getEstudioById(idEstudio);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpEstudios estudio) throws MtaGenericDAOException, MtaServiceException {
		try{
			estudio.setIdEstudio(estudiosDao.obtenerSiguienteId());
			estudio.setFechaCreacion(new Date());
			estudio.setUsuarioCreacion(estudio.getUsuarioCreacion());
			Row row = estudiosDao.guardar(estudio);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(estudio.getIdArchivo());
			if(archivo!=null && archivo.getEstado()==0) {
				archivo.setEstado(1);
				mtaArchivosTmpDao.update(archivo);	
			}
			
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row actualizar(DgaEmpEstudios estudio) throws MtaGenericDAOException, MtaServiceException {
		try{
			estudio.setFechaModificacion(new Date());
			estudio.setUsuarioModificacion(estudio.getUsuarioModificacion());
			Row row = estudiosDao.actualizar(estudio);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(estudio.getIdArchivo());
			if(archivo!=null && archivo.getEstado()==0) {
				archivo.setEstado(1);
				mtaArchivosTmpDao.update(archivo);	
			}
			
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

	@Override
	public Row eliminar(Integer idEstudio) throws MtaGenericDAOException, MtaServiceException {
		Row row = new Row();
        Integer rows = estudiosDao.eliminar(idEstudio);
        if (rows > 0)
            row.put("id", idEstudio);
        return row;
	}

}
