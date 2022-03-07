package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpAcciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEmblact;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEstudios;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpleado;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.dao.HistoricoDao;

@Repository
public class HistoricoDaoImpl extends GenericDAOImpl implements HistoricoDao{

	public HistoricoDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public List<AudDgaEmpleado> getAllByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMPLEADOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpleado.class));
	}

	@Override
	public List<AudDgaEmpAcciones> getAudEmpAccionByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMP_ACCIONES ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpAcciones.class));
	}

	@Override
	public List<AudDgaEmpEmblact> getAudEmpEmbalactByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMP_EMBLACT ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpEmblact.class));
	}

	@Override
	public List<AudDgaEmpEstudios> getAudEmpEstudioByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMP_ESTUDIOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpEstudios.class));
	}

	@Override
	public List<AudDgaEmpIncapacidad> getAudEmpIncapacidadByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMP_INCAPACIDADES ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpIncapacidad.class));
	}

	@Override
	public List<AudDgaEmpVacaciones> getAudEmpVacacionByIdEmpleado(Integer idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM AUD_DGA_EMP_VACACIONES ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ID_EMPLEADO = "+ idEmpleado);		
		sql.append("ORDER BY FECHA ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(AudDgaEmpVacaciones.class));
	}

}
