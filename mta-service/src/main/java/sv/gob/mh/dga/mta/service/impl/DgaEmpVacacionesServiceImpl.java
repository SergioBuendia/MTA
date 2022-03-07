package sv.gob.mh.dga.mta.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpVacacionesDao;
import sv.gob.mh.dga.mta.dao.DgaEmpleadoDao;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.dao.MtaCatalogoDetalleDao;
import sv.gob.mh.dga.mta.service.DgaEmpVacacionesService;

@Service
public class DgaEmpVacacionesServiceImpl implements DgaEmpVacacionesService{

	@Autowired
	private DgaEmpVacacionesDao vacacionesDao;
	
	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;
	
	@Autowired
	private DgaEmpleadoDao empleadoDao;
	
	@Autowired
	private MtaCatalogoDetalleDao catalogoDetalle;
	
	@Override
	public List<DgaEmpVacaciones>getAllByEmpleado(Integer idEmpleado) {
		return vacacionesDao.getAllByEmpleado(idEmpleado);
	}

	@Override
	public DgaEmpVacaciones getVacacionById(Integer idVacacion) throws MtaGenericDAOException {
		return vacacionesDao.getVacacionById(idVacacion);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException, MtaServiceException {
		try{
			vacacion.setIdVacacion(vacacionesDao.obtenerSiguienteId());
			vacacion.setFechaCreacion(new Date());
			vacacion.setUsuarioCreacion(vacacion.getUsuarioCreacion());
			Row row = vacacionesDao.guardar(vacacion);
			
			DgaEmpleado emp = empleadoDao.getEmpleadoById(vacacion.getIdEmpleado());
			if(emp!=null && "ACTIVO".equals(emp.getEstado())) {
				Date hoy = new Date();
				hoy = DateUtils.truncate(hoy, Calendar.DATE);
				Date inicio = DateUtils.truncate(vacacion.getFechaInicio(), Calendar.DATE);
				Date fin = DateUtils.truncate(vacacion.getFechaFin(), Calendar.DATE);
				 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
			        	//TODO cambiar estado de empleado a incapacitado   
					 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "INACTIVO");
					 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
					 	empleadoDao.actualizar(emp);					 	
			        }				
			}
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(vacacion.getIdArchivo());
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
	public Row actualizar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException, MtaServiceException {
		try{
			vacacion.setFechaModificacion(new Date());
			vacacion.setUsuarioModificacion(vacacion.getUsuarioModificacion());
			Row row = vacacionesDao.actualizar(vacacion);
			
			DgaEmpleado emp = empleadoDao.getEmpleadoById(vacacion.getIdEmpleado());
			Date hoy = new Date();
			hoy = DateUtils.truncate(hoy, Calendar.DATE);
			Date inicio = DateUtils.truncate(vacacion.getFechaInicio(), Calendar.DATE);
			Date fin = DateUtils.truncate(vacacion.getFechaFin(), Calendar.DATE);
			
			if(emp!=null && "ACTIVO".equals(emp.getEstado())) {				
				 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
					 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "INACTIVO");
					 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
					 	empleadoDao.actualizar(emp);	 	
			        }				
			}else if(emp!=null && "INACTIVO".equals(emp.getEstado())) {
				if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
				 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "ACTIVO");
				 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
				 	empleadoDao.actualizar(emp);	 	
		        }				
			}
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(vacacion.getIdArchivo());
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
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row eliminar(Integer idVacacion) throws MtaGenericDAOException, MtaServiceException {
		Row row = new Row();
		
		DgaEmpVacaciones vacacion = vacacionesDao.getVacacionById(idVacacion);
		
        Integer rows = vacacionesDao.eliminar(idVacacion);
        
        DgaEmpleado emp = empleadoDao.getEmpleadoById(vacacion.getIdEmpleado());
		if(emp!=null && "INACTIVO".equals(emp.getEstado())) {
			Date hoy = new Date();
			hoy = DateUtils.truncate(hoy, Calendar.DATE);
			Date inicio = DateUtils.truncate(vacacion.getFechaInicio(), Calendar.DATE);
			Date fin = DateUtils.truncate(vacacion.getFechaFin(), Calendar.DATE);
			 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
				 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "ACTIVO");
				 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
				 	empleadoDao.actualizar(emp);					 	
		        }				
		}
		
        if (rows > 0)
            row.put("id", idVacacion);
        return row;
	}

}
