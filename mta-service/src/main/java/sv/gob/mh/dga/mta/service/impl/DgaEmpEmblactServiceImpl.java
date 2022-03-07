package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEmblact;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpEmblactDao;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.service.DgaEmpEmblactService;

@Service
public class DgaEmpEmblactServiceImpl implements DgaEmpEmblactService{
	
	@Autowired
	private DgaEmpEmblactDao empEmblactDao;
	
	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;

	@Override
	public List<DgaEmpEmblact> getAllByEmpleado(Integer idEmpleado) {
		return empEmblactDao.getAllByEmpleado(idEmpleado);
	}

	@Override
	public DgaEmpEmblact getEmblactById(Integer idEmblact) throws MtaGenericDAOException {
		return empEmblactDao.getEmpEmblactById(idEmblact);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpEmblact emblact) throws MtaGenericDAOException, MtaServiceException {
		try{
			emblact.setIdEmblact(empEmblactDao.obtenerSiguienteId());
			emblact.setFechaCreacion(new Date());
			emblact.setUsuarioCreacion(emblact.getUsuarioCreacion());
			Row row = empEmblactDao.guardar(emblact);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(emblact.getIdArchivo());
			if(archivo.getEstado()==0) {
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
	public Row actualizar(DgaEmpEmblact emblact) throws MtaGenericDAOException, MtaServiceException {
		try{
			emblact.setFechaModificacion(new Date());
			emblact.setUsuarioModificacion(emblact.getUsuarioModificacion());
			Row row = empEmblactDao.actualizar(emblact);
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(emblact.getIdArchivo());
			if(archivo.getEstado()==0) {
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
	public Row eliminar(Integer idEmblact) throws MtaGenericDAOException, MtaServiceException {
		Row row = new Row();
        Integer rows = empEmblactDao.eliminar(idEmblact);
        if (rows > 0)
            row.put("id", idEmblact);
        return row;
	}

}
