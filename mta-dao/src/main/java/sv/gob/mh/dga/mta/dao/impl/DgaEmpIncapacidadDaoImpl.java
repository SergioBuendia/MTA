package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpIncapacidadDao;

@Repository
public class DgaEmpIncapacidadDaoImpl extends GenericDAOImpl implements DgaEmpIncapacidadDao{

	public DgaEmpIncapacidadDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMP_INCAPACIDAD", Integer.class);
	}

	@Override
	public List<DgaEmpIncapacidad> getAllByEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_INCAPACIDAD,ID_EMPLEADO,DESCRIPCION,FECHA_INICIO,FECHA_FIN,FECHA_CREACION,FECHA_MODIFICACION ");
		sql.append(",USUARIO_CREACION ");
		sql.append(",USUARIO_MODIFICACION ");
		sql.append(",ID_ARCHIVO,MOTIVO_INCAPACIDAD,PERMANENTE,TIPO_INCAPACIDAD ");
		sql.append(",(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=incapacidad.TIPO_INCAPACIDAD) des_Tipo_Incapacidad, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=incapacidad.MOTIVO_INCAPACIDAD) des_Motivo_Incapacidad ");
		sql.append("FROM DGA_EMP_INCAPACIDADES incapacidad ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);
		sql.append(" ORDER BY ID_INCAPACIDAD ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpIncapacidad.class));
	}

	@Override
	public DgaEmpIncapacidad getIncapacidadById(Integer idIncapacidad) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_INCAPACIDAD,ID_EMPLEADO,DESCRIPCION,FECHA_INICIO,FECHA_FIN,FECHA_CREACION,FECHA_MODIFICACION ");
		sql.append("USUARIO_CREACION,USUARIO_MODIFICACION,TIPO_INCAPACIDAD,ID_ARCHIVO,MOTIVO_INCAPACIDAD,PERMANENTE, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=incapacidad.TIPO_INCAPACIDAD) des_Tipo_Incapacidad, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=incapacidad.MOTIVO_INCAPACIDAD) des_Motivo_Incapacidad ");
		sql.append("FROM DGA_EMP_INCAPACIDADES incapacidad ");
		sql.append("WHERE ID_INCAPACIDAD = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idIncapacidad}, DgaEmpIncapacidad.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMP_INCAPACIDADES (ID_INCAPACIDAD,ID_EMPLEADO,DESCRIPCION,FECHA_INICIO,FECHA_FIN,ID_ARCHIVO,TIPO_INCAPACIDAD,FECHA_CREACION,USUARIO_CREACION,MOTIVO_INCAPACIDAD,PERMANENTE) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		incapacidad.getIdIncapacidad(),
            		incapacidad.getIdEmpleado(),
            		incapacidad.getDescripcion(),
            		incapacidad.getFechaInicio(),
            		incapacidad.getFechaFin(),
            		incapacidad.getIdArchivo(),
            		incapacidad.getTipoIncapacidad(),
            		incapacidad.getFechaCreacion(),
            		incapacidad.getUsuarioCreacion(),
            		incapacidad.getMotivoIncapacidad(),
            		incapacidad.getPermanente());
            Row row = new Row();
            row.put("id", incapacidad.getIdIncapacidad());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar registro de incapacidad del empleado", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpIncapacidad incapacidad) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMP_INCAPACIDADES SET ID_EMPLEADO = ?, DESCRIPCION = ?, FECHA_INICIO = ?, FECHA_FIN = ?, ID_ARCHIVO = ?, TIPO_INCAPACIDAD = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? , MOTIVO_INCAPACIDAD = ? , PERMANENTE = ? ");
        sql.append(" WHERE ID_INCAPACIDAD = ? ");
        try {
        	jdbcTemplate.update(sql.toString(),
            		incapacidad.getIdEmpleado(),
            		incapacidad.getDescripcion(),
            		incapacidad.getFechaInicio(),
            		incapacidad.getFechaFin(),
            		incapacidad.getIdArchivo(),
            		incapacidad.getTipoIncapacidad(),
            		incapacidad.getFechaModificacion(),
            		incapacidad.getUsuarioModificacion(),
            		incapacidad.getMotivoIncapacidad(),
            		incapacidad.getPermanente(),
            		incapacidad.getIdIncapacidad());
            Row row = new Row();
            row.put("id", incapacidad.getIdIncapacidad());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar registro de incapacidad del empleado", e);
        }
	}

	@Override
	public Integer eliminar(Integer idIncapacidad) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM DGA_EMP_INCAPACIDADES ");
        sql.append(" WHERE ID_INCAPACIDAD =" + idIncapacidad);
        try {
            return jdbcTemplate.update(sql.toString());
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al eliminar en DGA_EMP_INCAPACIDADES", e);
        }
	}

}
