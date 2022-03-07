package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.DgaPojoFotoEmpleado;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpleadoDao;
import sv.gob.mh.dga.mta.service.DgaEmpleadoService;

@Service
public class DgaEmpleadoServiceImpl implements DgaEmpleadoService{

	@Autowired
	private DgaEmpleadoDao dgaEmpleadoDao;
	
	@Override
	public List<DgaEmpleado> getAll() {
		return dgaEmpleadoDao.getAll();
	}

	@Override
	public DgaEmpleado getEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException {
		return dgaEmpleadoDao.getEmpleadoById(idEmpleado);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpleado empleado) throws MtaGenericDAOException, MtaServiceException {
		try{
			empleado.setId_empleado(dgaEmpleadoDao.obtenerSiguienteId());
			empleado.setFechaCreacion(new Date());
			empleado.setUsuarioCreacion(empleado.getUsuarioCreacion());
			Row row = dgaEmpleadoDao.guardar(empleado);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario", e);
        }
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row actualizar(DgaEmpleado empleado) throws MtaGenericDAOException, MtaServiceException {
		try{
			empleado.setFechaModificacion(new Date());
			empleado.setUsuarioModificacion(empleado.getUsuarioModificacion());
			Row row = dgaEmpleadoDao.actualizar(empleado);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al actualizar el formulario", e);
        }
	}

	@Override
	public List<DgaEmpleado> getEmpleadosByCargoFuncional(String cargo) {
		return dgaEmpleadoDao.getEmpleadosByCargoFuncional(cargo);
	}

	@Override
	public DgaPojoFotoEmpleado getFotoEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException {
		return dgaEmpleadoDao.getFotoEmpleadoById(idEmpleado);
	}

}
