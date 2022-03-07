package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEstudios;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpEstudiosDao;

@Repository
public class DgaEmpEstudiosDaoImpl extends GenericDAOImpl implements DgaEmpEstudiosDao{

	public DgaEmpEstudiosDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMP_ESTUDIOS", Integer.class);
	}

	@Override
	public List<DgaEmpEstudios> getAllByEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		//sql.append("SELECT * FROM DGA_EMP_ESTUDIOS ");
		sql.append("SELECT ID_ESTUDIO, ID_EMPLEADO, TIPO_ESTUDIO, TITULO_ESTUDIO, INSTITUCION_ESTUDIO, ULTIMO_ESTUDIO, ");
		sql.append("FECHA_FINALIZACION, FECHA_CREACION,USUARIO_CREACION, FECHA_MODIFICACION, USUARIO_MODIFICACION, ID_ARCHIVO, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=estudio.tipo_estudio) desc_Tipo_Estudio, ");
		sql.append("(SELECT descripcion FROM mta_cat_subdetalle WHERE ID_CAT_SUBDETALLE=estudio.titulo_estudio) desc_Titulo_Estudio ");
		sql.append("FROM DGA_EMP_ESTUDIOS estudio ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND estudio.ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY estudio.ID_ESTUDIO ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpEstudios.class));
	}

	@Override
	public DgaEmpEstudios getEstudioById(Integer idEstudio) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_ESTUDIO, ID_EMPLEADO, TIPO_ESTUDIO, TITULO_ESTUDIO, INSTITUCION_ESTUDIO, ULTIMO_ESTUDIO, ");
		sql.append("FECHA_FINALIZACION, FECHA_CREACION,USUARIO_CREACION, FECHA_MODIFICACION, USUARIO_MODIFICACION, ID_ARCHIVO, ");
		sql.append("(SELECT descripcion FROM mta_catalogo_detalle WHERE id_cat_detalle=estudio.tipo_estudio) desc_Tipo_Estudio, ");
		sql.append("(SELECT descripcion FROM mta_cat_subdetalle WHERE ID_CAT_SUBDETALLE=estudio.titulo_estudio) desc_Titulo_Estudio ");
		sql.append("FROM DGA_EMP_ESTUDIOS estudio ");
		sql.append("WHERE ID_ESTUDIO = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idEstudio}, DgaEmpEstudios.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en estudio del empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpEstudios estudio) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMP_ESTUDIOS (ID_ESTUDIO,ID_EMPLEADO,TIPO_ESTUDIO,TITULO_ESTUDIO,INSTITUCION_ESTUDIO,ULTIMO_ESTUDIO,ID_ARCHIVO,FECHA_FINALIZACION,FECHA_CREACION,USUARIO_CREACION ) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		estudio.getIdEstudio(),
            		estudio.getIdEmpleado(),
            		estudio.getTipoEstudio(),
            		estudio.getTituloEstudio(),
            		estudio.getInstitucionEstudio(),
            		estudio.getUltimoEstudio(),
            		estudio.getIdArchivo(),
            		estudio.getFechaFinalizacion(),
            		estudio.getFechaCreacion(),
            		estudio.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", estudio.getIdEstudio());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar registro de estudio del empleado", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpEstudios estudio) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMP_ESTUDIOS SET ID_EMPLEADO = ?, TIPO_ESTUDIO = ?, TITULO_ESTUDIO = ?, INSTITUCION_ESTUDIO = ?, ULTIMO_ESTUDIO = ?, ID_ARCHIVO = ?, FECHA_FINALIZACION = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_ESTUDIO = ? ");
        try {
        	jdbcTemplate.update(sql.toString(),
            		estudio.getIdEmpleado(),
            		estudio.getTipoEstudio(),
            		estudio.getTituloEstudio(),
            		estudio.getInstitucionEstudio(),
            		estudio.getUltimoEstudio(),
            		estudio.getIdArchivo(),
            		estudio.getFechaFinalizacion(),
            		estudio.getFechaModificacion(),
            		estudio.getUsuarioModificacion(),
            		estudio.getIdEstudio());
            Row row = new Row();
            row.put("id", estudio.getIdEstudio());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar registro de estudio del empleado", e);
        }
	}

	@Override
	public Integer eliminar(Integer idEstudio) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM DGA_EMP_ESTUDIOS ");
        sql.append(" WHERE ID_ESTUDIO =" + idEstudio);
        try {
            return jdbcTemplate.update(sql.toString());
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al eliminar en DGA_EMP_ESTUDIOS", e);
        }
	}

	

}
