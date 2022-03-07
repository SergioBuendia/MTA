package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogos;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatalogosDao;

@Repository
public class MtaCatalogosDaoImpl extends GenericDAOImpl implements MtaCatalogosDao{

	public MtaCatalogosDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_CATALOGOS", Integer.class);
	}

	@Override
	public MtaCatalogos getCatalogoById(Integer idCatalogo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CATALOGOS ");
		sql.append("WHERE ID_CATALOGO = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idCatalogo}, MtaCatalogos.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en CATALOGO", e);
		}
	}

	@Override
	public List<MtaCatalogos> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CATALOGOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY DESCRIPCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCatalogos.class));
	}

	@Override
	public Row guardar(MtaCatalogos catalogo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_CATALOGOS (ID_CATALOGO, DESCRIPCION, FECHA_CREACION, USUARIO_CREACION) ");
        sql.append(" VALUES (?, ?, ?, ? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogo.getIdCatalogo(),
            		catalogo.getDescripcion(),
            		catalogo.getFechaCreacion(),
            		catalogo.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", catalogo.getIdCatalogo());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Catalogo", e);
        }
	}

	@Override
	public Row actualizar(MtaCatalogos catalogo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE MTA_CATALOGOS SET DESCRIPCION = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_CATALOGO = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogo.getDescripcion(),
            		catalogo.getFechaModificacion(),
            		catalogo.getUsuarioModificacion(),
            		catalogo.getIdCatalogo());
            Row row = new Row();
            row.put("id", catalogo.getIdCatalogo());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar Catalogo", e);
        }
	}

}
