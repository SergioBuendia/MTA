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
import sv.gob.mh.dga.mta.common.model.DgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpIncapacidadDao;
import sv.gob.mh.dga.mta.dao.DgaEmpleadoDao;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;
import sv.gob.mh.dga.mta.dao.MtaCatalogoDetalleDao;
import sv.gob.mh.dga.mta.service.DgaEmpIncapacidadService;

@Service
public class DgaEmpIncapacidadServiceImpl implements DgaEmpIncapacidadService{

	@Autowired
	private DgaEmpIncapacidadDao incapacidadesDao;
	
	@Autowired
	private MtaArchivosTmpDao mtaArchivosTmpDao;
	
	@Autowired
	private DgaEmpleadoDao empleadoDao;
	
	@Autowired
	private MtaCatalogoDetalleDao catalogoDetalle;
	
	@Override
	public List<DgaEmpIncapacidad> getAllByEmpleado(Integer idEmpleado) {
		return incapacidadesDao.getAllByEmpleado(idEmpleado);
	}

	@Override
	public DgaEmpIncapacidad getIncapacidadById(Integer idIncapacidad) throws MtaGenericDAOException {
		return incapacidadesDao.getIncapacidadById(idIncapacidad);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException, MtaServiceException {
		try{
			incapacidad.setIdIncapacidad(incapacidadesDao.obtenerSiguienteId());
			incapacidad.setFechaCreacion(new Date());
			incapacidad.setUsuarioCreacion(incapacidad.getUsuarioCreacion());
			Row row = incapacidadesDao.guardar(incapacidad);
			
			DgaEmpleado emp = empleadoDao.getEmpleadoById(incapacidad.getIdEmpleado());
			Date hoy = new Date();
			hoy = DateUtils.truncate(hoy, Calendar.DATE);
			Date inicio = DateUtils.truncate(incapacidad.getFechaInicio(), Calendar.DATE);
			Date fin = DateUtils.truncate(incapacidad.getFechaFin(), Calendar.DATE);
			
			
			if(emp!=null && "ACTIVO".equals(emp.getEstado())) {
				 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
					 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "INACTIVO");
					 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
					 	empleadoDao.actualizar(emp);					 	
			        }				
			}else if(emp!=null && "INACTIVO".equals(emp.getEstado())) {
				if(emp.getFechaFinInactivo()!=null) {
			 		Date finInactivo = DateUtils.truncate(emp.getFechaFinInactivo(), Calendar.DATE);
			 		if(fin.compareTo(finInactivo) > 0) {
			 			emp.setFechaFinInactivo(fin);
			 		}
			 	}					 	
			 	empleadoDao.actualizar(emp);
			}
			
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(incapacidad.getIdArchivo());
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
	public Row actualizar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException, MtaServiceException {
		try{
			incapacidad.setFechaModificacion(new Date());
			incapacidad.setUsuarioModificacion(incapacidad.getUsuarioModificacion());
			Row row = incapacidadesDao.actualizar(incapacidad);
			
			DgaEmpleado emp = empleadoDao.getEmpleadoById(incapacidad.getIdEmpleado());
			Date hoy = new Date();
			hoy = DateUtils.truncate(hoy, Calendar.DATE);
			Date inicio = DateUtils.truncate(incapacidad.getFechaInicio(), Calendar.DATE);
			Date fin = DateUtils.truncate(incapacidad.getFechaFin(), Calendar.DATE);
			
			Date finInactivo = null;
			if(emp.getFechaFinInactivo()!=null) {
				finInactivo = DateUtils.truncate(emp.getFechaFinInactivo(), Calendar.DATE);
			}
			
			if(emp!=null && "ACTIVO".equals(emp.getEstado())) {				
				 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
					 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "INACTIVO");
					 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
					 	empleadoDao.actualizar(emp);					 	
			        }
			}else if(emp!=null && "INACTIVO".equals(emp.getEstado())) {	
				if(finInactivo!=null  && fin.compareTo(finInactivo) > 0) {
				 	emp.setFechaFinInactivo(fin);
				 	empleadoDao.actualizar(emp);
				}else if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
				 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "ACTIVO");
				 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
				 	empleadoDao.actualizar(emp);					 	
		        }
			}
			
			MtaArchivosTmp archivo = mtaArchivosTmpDao.getMtaArchivosTmpById(incapacidad.getIdArchivo());
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
	public Row eliminar(Integer idIncapacidad) throws MtaGenericDAOException, MtaServiceException {
		Row row = new Row();
		
		DgaEmpIncapacidad incapacidad = incapacidadesDao.getIncapacidadById(idIncapacidad);
		
        Integer rows = incapacidadesDao.eliminar(idIncapacidad);
        
        DgaEmpleado emp = empleadoDao.getEmpleadoById(incapacidad.getIdEmpleado());
		if(emp!=null && "INACTIVO".equals(emp.getEstado())) {
			Date hoy = new Date();
			hoy = DateUtils.truncate(hoy, Calendar.DATE);
			Date inicio = DateUtils.truncate(incapacidad.getFechaInicio(), Calendar.DATE);
			Date fin = DateUtils.truncate(incapacidad.getFechaFin(), Calendar.DATE);
			 if(hoy.compareTo(inicio)>=0 && hoy.compareTo(fin)<=0) {
				 	MtaCatalogoDetalle estadoIncapacitado = catalogoDetalle.getCatalogoDetalleByDescripcion(1, "ACTIVO");
				 	emp.setIdEstado(estadoIncapacitado.getIdCatDetalle());
				 	empleadoDao.actualizar(emp);					 	
		        }				
		}
		
        if (rows > 0)
            row.put("id", idIncapacidad);
        return row;
	}

}
