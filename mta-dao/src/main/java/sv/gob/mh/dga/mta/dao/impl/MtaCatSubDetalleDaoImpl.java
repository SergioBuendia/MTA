package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatSubDetalle;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.model.MtaCatalogos;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatSubDetalleDao;

@Repository
public class MtaCatSubDetalleDaoImpl extends GenericDAOImpl implements MtaCatSubDetalleDao{

	public MtaCatSubDetalleDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_CAT_SUBDETALLE", Integer.class);
	}

	@Override
	public MtaCatSubDetalle getCatalogoDetalleById(Integer idCatSubDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CAT_SUBDETALLE ");
		sql.append("WHERE ID_CAT_SUBDETALLE = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idCatSubDetalle}, MtaCatSubDetalle.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Catalogo SubDetalle", e);
		}
	}

	@Override
	public MtaCatSubDetalle getCatSubDetalleByDescripcion(String descripcion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CAT_SUBDETALLE ");
		sql.append("WHERE DESCRIPCION = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{descripcion}, MtaCatSubDetalle.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Catalogo SubDetalle", e);
		}
	}

	@Override
	public List<MtaCatSubDetalle> getAllByCatalogoDetalle(Integer idCatDetalle) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CAT_SUBDETALLE ");
		sql.append("WHERE 1 = 1 ");
		
		if(idCatDetalle!=null && idCatDetalle!=0) {
			sql.append(" AND ID_CAT_DETALLE = "+idCatDetalle);	
		}
		
		sql.append("ORDER BY DESCRIPCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCatSubDetalle.class));
	}

	@Override
	public Row guardar(MtaCatSubDetalle catalogoSubDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_CAT_SUBDETALLE (ID_CAT_SUBDETALLE, ID_CAT_DETALLE, DESCRIPCION, VALOR, FECHA_CREACION, USUARIO_CREACION) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogoSubDetalle.getIdCatSubdetalle(),
            		catalogoSubDetalle.getIdCatDetalle(),
            		catalogoSubDetalle.getDescripcion(),
            		catalogoSubDetalle.getValor(),
            		catalogoSubDetalle.getFechaCreacion(),
            		catalogoSubDetalle.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", catalogoSubDetalle.getIdCatSubdetalle());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Catalogo SubDetalle", e);
        }
	}

	@Override
	public Row actualizar(MtaCatSubDetalle catalogoSubDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE MTA_CAT_SUBDETALLE SET ID_CAT_DETALLE = ?, DESCRIPCION = ?, VALOR = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_CAT_SUBDETALLE = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogoSubDetalle.getIdCatDetalle(),
            		catalogoSubDetalle.getDescripcion(),
            		catalogoSubDetalle.getValor(),
            		catalogoSubDetalle.getFechaModificacion(),
            		catalogoSubDetalle.getUsuarioModificacion(),
            		catalogoSubDetalle.getIdCatSubdetalle());
            Row row = new Row();
            row.put("id", catalogoSubDetalle.getIdCatSubdetalle());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar Catalogo SubDetalle", e);
        }
	}

	@Override
	public List<MtaCatSubDetalle> getMunicipiosAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CAT_SUBDETALLE ");
		sql.append("WHERE id_cat_detalle in (SELECT id_cat_detalle FROM mta_catalogo_detalle where id_catalogo=4) order by descripcion ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCatSubDetalle.class));
	}

}
