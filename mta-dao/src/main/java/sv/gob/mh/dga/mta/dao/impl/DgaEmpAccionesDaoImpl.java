package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpAcciones;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpAccionesDao;

@Repository
public class DgaEmpAccionesDaoImpl extends GenericDAOImpl implements DgaEmpAccionesDao{

	public DgaEmpAccionesDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMP_ACCIONES", Integer.class);
	}

	@Override
	public List<DgaEmpAcciones> getAllByEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_ACCION,ID_EMPLEADO,ANIO,ID_ADUANA,DESCRIPCION,FECHA_CREACION, ");
		sql.append("FECHA_MODIFICACION,USUARIO_CREACION,USUARIO_MODIFICACION,ID_ARCHIVO,MOTIVO_ACCION, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=accion.MOTIVO_ACCION) des_Motivo_Accion ");
		sql.append(",aduana.cuo_nam aduana ");
		sql.append("FROM DGA_EMP_ACCIONES accion ");
		sql.append(",awunadm.uncuotab aduana ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);
		sql.append(" AND accion.id_aduana = aduana.cuo_cod ");
		sql.append("ORDER BY ID_ACCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpAcciones.class));
	}

	@Override
	public DgaEmpAcciones getAccionoById(Integer idAccion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_ACCION,ID_EMPLEADO,ANIO,ID_ADUANA,DESCRIPCION,FECHA_CREACION, ");
		sql.append("FECHA_MODIFICACION,USUARIO_CREACION,USUARIO_MODIFICACION,ID_ARCHIVO,MOTIVO_ACCION, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=accion.MOTIVO_ACCION) des_Motivo_Accion ");
		sql.append("FROM DGA_EMP_ACCIONES accion ");
		sql.append("WHERE ID_ACCION = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idAccion}, DgaEmpAcciones.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpAcciones accion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMP_ACCIONES (ID_ACCION,ID_EMPLEADO,ANIO,ID_ADUANA,DESCRIPCION,ID_ARCHIVO,FECHA_CREACION,USUARIO_CREACION, MOTIVO_ACCION ) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		accion.getIdAccion(),
            		accion.getIdEmpleado(),
            		accion.getAnio(),
            		accion.getIdAduana(),
            		accion.getDescripcion(),
            		accion.getIdArchivo(),
            		accion.getFechaCreacion(),
            		accion.getUsuarioCreacion(),
            		accion.getMotivoAccion());
            Row row = new Row();
            row.put("id", accion.getIdAccion());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar acción de personal", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpAcciones accion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMP_ACCIONES SET ID_EMPLEADO = ?, ANIO = ?, ID_ADUANA = ?, DESCRIPCION = ?, ID_ARCHIVO = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? , MOTIVO_ACCION = ? ");
        sql.append(" WHERE ID_ACCION = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		accion.getIdEmpleado(),
            		accion.getAnio(),
            		accion.getIdAduana(),
            		accion.getDescripcion(),
            		accion.getIdArchivo(),
            		accion.getFechaModificacion(),
            		accion.getUsuarioModificacion(),
            		accion.getMotivoAccion(),
            		accion.getIdAccion());
            Row row = new Row();
            row.put("id", accion.getIdAccion());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar acción de personal", e);
        }
	}

	@Override
	public Integer eliminar(Integer idAccion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM DGA_EMP_ACCIONES ");
        sql.append(" WHERE ID_ACCION =" + idAccion);
        try {
            return jdbcTemplate.update(sql.toString());
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al eliminar en DGA_EMP_ACCIONES", e);
        }
	}

}
