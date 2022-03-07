package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.model.MtaCargoNominal;
import sv.gob.mh.dga.mta.dao.MtaCargoNominalDao;

@Repository
public class MtaCargoNominalDaoImpl implements MtaCargoNominalDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MtaCargoNominal getCargoNominalByFilter(MtaCargoNominal cargoNominal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaCargoNominal> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CARGO_NOMINAL ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY DESCRIPCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCargoNominal.class));
	}

}
