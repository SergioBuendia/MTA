package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpEmblact;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpEmblactDao;

@Repository
public class DgaEmpEmblactDaoImpl extends GenericDAOImpl implements DgaEmpEmblactDao {

	public DgaEmpEmblactDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMP_EMBLACT", Integer.class);
	}

	@Override
	public List<DgaEmpEmblact> getAllByEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM DGA_EMP_EMBLACT ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY ID_EMBLACT ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpEmblact.class));
	}

	@Override
	public DgaEmpEmblact getEmpEmblactById(Integer idEmblact) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM DGA_EMP_EMBLACT ");
		sql.append("WHERE ID_EMBLACT = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idEmblact}, DgaEmpEmblact.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpEmblact empEmblact) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMP_EMBLACT (ID_EMBLACT,ID_EMPLEADO,NOMBRE_HIJO,FECHA_NACIMIENTO,SEXO,ID_ARCHIVO,FECHA_CREACION,USUARIO_CREACION ) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            jdbcTemplate.update(sql.toString(),
            		empEmblact.getIdEmblact(),
            		empEmblact.getIdEmpleado(),
            		empEmblact.getNombreHijo(),
            		empEmblact.getFechaNacimiento(),
            		empEmblact.getSexo(),
            		empEmblact.getIdArchivo(),
            		empEmblact.getFechaCreacion(),
            		empEmblact.getUsuarioCreacion());
            Row row = new Row();
            row.put("id", empEmblact.getIdEmblact());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar registro de embarazo", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpEmblact empEmblact) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMP_EMBLACT SET ID_EMPLEADO = ?, NOMBRE_HIJO = ?, FECHA_NACIMIENTO = ?, SEXO = ?, ID_ARCHIVO = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? ");
        sql.append(" WHERE ID_EMBLACT = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		empEmblact.getIdEmpleado(),
            		empEmblact.getNombreHijo(),
            		empEmblact.getFechaNacimiento(),
            		empEmblact.getSexo(),
            		empEmblact.getIdArchivo(),
            		empEmblact.getFechaModificacion(),
            		empEmblact.getUsuarioModificacion(),
            		empEmblact.getIdEmblact());
            Row row = new Row();
            row.put("id", empEmblact.getIdEmblact());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar registro de embarazo", e);
        }
	}

	@Override
	public Integer eliminar(Integer idEmblact) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM DGA_EMP_EMBLACT ");
        sql.append(" WHERE ID_EMBLACT =" + idEmblact);
        try {
            return jdbcTemplate.update(sql.toString());
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al eliminar en DGA_EMP_EMBLACT", e);
        }
	}

	
}
