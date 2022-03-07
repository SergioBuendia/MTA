package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaEstados;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaEstadosDao;

@Repository
public class MtaEstadosDaoImpl extends GenericDAOImpl implements MtaEstadosDao{

	public MtaEstadosDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	
	@Override
	public MtaEstados getEstadoById(Integer idEstado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_ESTADOS ");
		sql.append("WHERE ID_ESTADO = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idEstado}, MtaEstados.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en USUARIO", e);
		}
	}

	@Override
	public List<MtaEstados> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_ESTADOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY ESTADO ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaEstados.class));
	}


	@Override
	public Row guardar(MtaEstados estado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_ESTADOS (ID_ESTADO,ESTADO,DESCRIPCION,FECHA_CREACION,USUARIO_CREACION)");
        sql.append(" VALUES (?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		estado.getIdEstado(),
            		estado.getEstado(),
            		estado.getDescripcion(),
            		estado.getFechaCreacion(),
            		estado.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", estado.getIdEstado());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Estado", e);
        }
	}


	@Override
	public Row actualizar(MtaEstados estado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE MTA_ESTADOS  SET ESTADO = ?, DESCRIPCION = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_ESTADO = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		estado.getEstado(),
            		estado.getDescripcion(),
            		estado.getFechaModificacion(),
            		estado.getUsuarioModificacion(),
            		estado.getIdEstado());
            Row row = new Row();
            row.put("id", estado.getIdEstado());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar Estado", e);
        }
	}


	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_MTA_ESTADOS", Integer.class);
	}

}
