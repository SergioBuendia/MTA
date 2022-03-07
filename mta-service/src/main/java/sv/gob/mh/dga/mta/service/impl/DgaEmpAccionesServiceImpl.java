package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpAcciones;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpAccionesDao;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.service.DgaEmpAccionesService;

@Service
public class DgaEmpAccionesServiceImpl implements DgaEmpAccionesService{

	@Autowired
	private DgaEmpAccionesDao accionesDao;
	
	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;
	
	@Override
	public List<DgaEmpAcciones> getAllByEmpleado(Integer idEmpleado) {
		return accionesDao.getAllByEmpleado(idEmpleado);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public DgaEmpAcciones getAccionById(Integer idAccion) throws MtaGenericDAOException {
		return accionesDao.getAccionoById(idAccion);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpAcciones accion) throws MtaGenericDAOException, MtaServiceException {
		try{
			accion.setIdAccion(accionesDao.obtenerSiguienteId());
			accion.setFechaCreacion(new Date());
			accion.setUsuarioCreacion(accion.getUsuarioCreacion());
			Row row = accionesDao.guardar(accion);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(accion.getIdArchivo());
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
	public Row actualizar(DgaEmpAcciones accion) throws MtaGenericDAOException, MtaServiceException {
		try{
			accion.setFechaModificacion(new Date());
			accion.setUsuarioModificacion(accion.getUsuarioModificacion());
			Row row = accionesDao.actualizar(accion);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(accion.getIdArchivo());
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
	public Row eliminar(Integer idAccion) throws MtaGenericDAOException, MtaServiceException {
		Row row = new Row();
        Integer rows = accionesDao.eliminar(idAccion);
        if (rows > 0)
            row.put("id", idAccion);
        return row;
	}

}
