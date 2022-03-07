package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.model.MtaDepartamentos;
import sv.gob.mh.dga.mta.dao.MtaDepartamentosDao;

@Repository
public class MtaDepartamentosDaoImpl implements MtaDepartamentosDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MtaDepartamentos getDepartamentoByFilter(MtaDepartamentos departamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaDepartamentos> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_DEPARTAMENTOS ");
		sql.append("WHERE 1 = 1 ");
		sql.append(" ORDER BY DEPARTAMENTO ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaDepartamentos.class));
	}

}
