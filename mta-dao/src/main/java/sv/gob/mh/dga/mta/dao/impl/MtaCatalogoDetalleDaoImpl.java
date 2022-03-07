package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaCatalogoDetalleDao;

@Repository
public class MtaCatalogoDetalleDaoImpl extends GenericDAOImpl implements MtaCatalogoDetalleDao{

	public MtaCatalogoDetalleDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_CATALOGO_DETALLE", Integer.class);
	}

	@Override
	public MtaCatalogoDetalle getCatalogoDetalleById(Integer idCatalogoDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CATALOGO_DETALLE ");
		sql.append("WHERE ID_CAT_DETALLE = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idCatalogoDetalle}, MtaCatalogoDetalle.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Catalogo Detalle", e);
		}
	}

	@Override
	public List<MtaCatalogoDetalle> getAllByCatalogo(Integer idCatalogo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CATALOGO_DETALLE ");
		sql.append("WHERE 1 = 1 ");
		
		if(idCatalogo!=null && idCatalogo!=0) {
			sql.append(" AND ID_CATALOGO = "+idCatalogo);	
		}
		
		sql.append("ORDER BY DESCRIPCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCatalogoDetalle.class));
	}

	@Override
	public Row guardar(MtaCatalogoDetalle catalogoDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_CATALOGO_DETALLE (ID_CAT_DETALLE, ID_CATALOGO, DESCRIPCION, VALOR, FECHA_CREACION, USUARIO_CREACION) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogoDetalle.getIdCatDetalle(),
            		catalogoDetalle.getIdCatalogo(),
            		catalogoDetalle.getDescripcion(),
            		catalogoDetalle.getValor(),
            		catalogoDetalle.getFechaCreacion(),
            		catalogoDetalle.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", catalogoDetalle.getIdCatDetalle());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Catalogo Detalle", e);
        }
	}

	@Override
	public Row actualizar(MtaCatalogoDetalle catalogoDetalle) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE MTA_CATALOGO_DETALLE SET ID_CATALOGO = ?, DESCRIPCION = ?, VALOR = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_CAT_DETALLE = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		catalogoDetalle.getIdCatalogo(),
            		catalogoDetalle.getDescripcion(),
            		catalogoDetalle.getValor(),
            		catalogoDetalle.getFechaModificacion(),
            		catalogoDetalle.getUsuarioModificacion(),
            		catalogoDetalle.getIdCatDetalle());
            Row row = new Row();
            row.put("id", catalogoDetalle.getIdCatDetalle());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar Catalogo Detalle", e);
        }
	}

	@Override
	public MtaCatalogoDetalle getCatalogoDetalleByDescripcion(Integer idCatalogo, String descripcion) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CATALOGO_DETALLE ");
		sql.append("WHERE ID_CATALOGO = ? AND DESCRIPCION = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idCatalogo, descripcion}, MtaCatalogoDetalle.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Catalogo Detalle", e);
		}
	}

	

}
