package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpVacacionesDao;

@Repository
public class DgaEmpVacacionesDaoImpl extends GenericDAOImpl implements DgaEmpVacacionesDao {

	public DgaEmpVacacionesDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMP_VACACIONES", Integer.class);
	}

	@Override
	public List<DgaEmpVacaciones> getAllByEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_VACACION,ID_EMPLEADO,ANIO,FECHA_INICIO,FECHA_FIN,FECHA_CREACION,FECHA_MODIFICACION, ");
		sql.append("USUARIO_CREACION,USUARIO_MODIFICACION,ID_ARCHIVO,REPROGRAMACION,IDSOLICITANTE, ");
		sql.append("(select nombre from dga_empleados where id_empleado=vacacion.IDSOLICITANTE) name_Solicitante ");
		sql.append("FROM DGA_EMP_VACACIONES vacacion ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY ID_VACACION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpVacaciones.class));
	}

	@Override
	public DgaEmpVacaciones getVacacionById(Integer idVacacion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_VACACION,ID_EMPLEADO,ANIO,FECHA_INICIO,FECHA_FIN,FECHA_CREACION,FECHA_MODIFICACION, ");
		sql.append("USUARIO_CREACION,USUARIO_MODIFICACION,ID_ARCHIVO,REPROGRAMACION,IDSOLICITANTE, ");
		sql.append("(select nombre from dga_empleados where id_empleado=vacacion.IDSOLICITANTE) name_Solicitante ");
		sql.append("FROM DGA_EMP_VACACIONES vacacion ");
		sql.append("WHERE ID_VACACION = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idVacacion}, DgaEmpVacaciones.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMP_VACACIONES (ID_VACACION,ID_EMPLEADO,ANIO,FECHA_INICIO,FECHA_FIN,ID_ARCHIVO,FECHA_CREACION,USUARIO_CREACION,REPROGRAMACION,IDSOLICITANTE) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		vacacion.getIdVacacion(),
            		vacacion.getIdEmpleado(),
            		vacacion.getAnio(),
            		vacacion.getFechaInicio(),
            		vacacion.getFechaFin(),
            		vacacion.getIdArchivo(),
            		vacacion.getFechaCreacion(),
            		vacacion.getUsuarioCreacion(),
            		vacacion.getReprogramacion(),
            		vacacion.getIdSolicitante());
            Row row = new Row();
            row.put("id", vacacion.getIdVacacion());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar registro de vacación del empleado", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpVacaciones vacacion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMP_VACACIONES SET ID_EMPLEADO = ?, ANIO = ?, FECHA_INICIO = ?, FECHA_FIN = ?, ID_ARCHIVO = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? , REPROGRAMACION = ?, IDSOLICITANTE = ? ");
        sql.append(" WHERE ID_VACACION = ? ");
        try {
        	jdbcTemplate.update(sql.toString(),
            		vacacion.getIdEmpleado(),
            		vacacion.getAnio(),
            		vacacion.getFechaInicio(),
            		vacacion.getFechaFin(),
            		vacacion.getIdArchivo(),
            		vacacion.getFechaModificacion(),
            		vacacion.getUsuarioModificacion(),
            		vacacion.getReprogramacion(),
            		vacacion.getIdSolicitante(),
            		vacacion.getIdVacacion());
            Row row = new Row();
            row.put("id", vacacion.getIdVacacion());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar registro de vacación del empleado", e);
        }
	}

	@Override
	public Integer eliminar(Integer idVacacion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM DGA_EMP_VACACIONES ");
        sql.append(" WHERE ID_VACACION =" + idVacacion);
        try {
            return jdbcTemplate.update(sql.toString());
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al eliminar en DGA_EMP_VACACIONES", e);
        }
	}


}
