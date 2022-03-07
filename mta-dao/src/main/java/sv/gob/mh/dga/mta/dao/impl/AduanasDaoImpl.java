package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sw.model.Aduanas;
import sv.gob.mh.dga.mta.dao.AduanasDao;

@Repository
public class AduanasDaoImpl extends GenericDAOImpl implements AduanasDao{

	public AduanasDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}

	@Override
	public List<Aduanas> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cuo_cod, cuo_nam, cuo_adr, cuo_ad2, cuo_ad3, cuo_ad4, valid_from, valid_to FROM AWUNADM.UNCUOTAB ");
		sql.append("WHERE  valid_to is null ");	
		sql.append("ORDER BY cuo_cod asc ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Aduanas.class));
	}

}
