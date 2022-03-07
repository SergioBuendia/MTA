package sv.gob.mh.dga.mta.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaArchivosTmp;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaArchivosTmpDao;

@Repository
public class MtaArchivosTmpDaoImpl extends GenericDAOImpl implements MtaArchivosTmpDao{

	public MtaArchivosTmpDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_MTA_ARCHIVOS_ADJ", Integer.class);
	}

	@Override
	public MtaArchivosTmp getMtaArchivosTmpById(Integer idArchivo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_ARCHIVOS_ADJ ");
		sql.append("WHERE ID_ARCHIVO = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idArchivo}, MtaArchivosTmp.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Archivo", e);
		}
	}

	@Override
	public Row guardar(MtaArchivosTmp archivo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_ARCHIVOS_ADJ (ID_ARCHIVO, NOMBRE, CONTENIDO, PESO, FECHA_CREACION, ESTADO, USUARIO_CREACION) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		archivo.getIdArchivo(),
            		archivo.getNombre(),
            		archivo.getContenido(),
            		archivo.getPeso(),
            		archivo.getFechaCreacion(),
            		archivo.getEstado(),
            		archivo.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", archivo.getIdArchivo());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar archivo temporal", e);
        }
	}

	@Override
	public Row update(MtaArchivosTmp archivo) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE MTA_ARCHIVOS_ADJ SET NOMBRE = ?, CONTENIDO = ?, PESO = ?, ESTADO = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_ARCHIVO = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		archivo.getNombre(),
            		archivo.getContenido(),
            		archivo.getPeso(),
            		archivo.getEstado(),
            		archivo.getFechaModificacion(),
            		archivo.getUsuarioModificacion(),
            		archivo.getIdArchivo());
            Row row = new Row();
            row.put("id", archivo.getIdArchivo());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar acción de personal", e);
        }
	}

}
