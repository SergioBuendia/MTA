package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.MtaUsuarios;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaUsuariosDao;

@Repository
public class MtaUsuariosDaoImpl extends GenericDAOImpl implements MtaUsuariosDao{

	public MtaUsuariosDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}
	
	@Override
	public MtaUsuarios getUsuarioByUser(String usuario) throws MtaGenericDAOException  {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_USUARIOS ");
		sql.append("WHERE USUARIO = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{usuario}, MtaUsuarios.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en USUARIO", e);
		}
	}

	@Override
	public List<MtaUsuarios> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_USUARIOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY USUARIO ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaUsuarios.class));
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_MTA_USUARIOS", Integer.class);
	}
	
	@Override
	public Row guardar(MtaUsuarios usuario) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO MTA_USUARIOS(ID_USUARIO, USUARIO, ROL, FECHA_CREACION)");
        sql.append(" VALUES (?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		usuario.getIdUsuario(),
            		usuario.getUsuario(),
            		usuario.getRol(),
            		usuario.getFechaCreacion());
            Row row = new Row();
            row.put("id", usuario.getIdUsuario());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Usuario", e);
        }
	}

	

}
