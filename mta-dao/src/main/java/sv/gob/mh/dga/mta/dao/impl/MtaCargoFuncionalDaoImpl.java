package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.model.MtaCargoFuncional;
import sv.gob.mh.dga.mta.dao.MtaCargoFuncionalDao;

@Repository
public class MtaCargoFuncionalDaoImpl implements MtaCargoFuncionalDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MtaCargoFuncional getCargoFuncionalByFilter(MtaCargoFuncional cargoFuncional) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaCargoFuncional> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_CARGO_FUNCIONAL ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY DESCRIPCION ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaCargoFuncional.class));
	}

}
