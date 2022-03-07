package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.model.MtaMunicipios;
import sv.gob.mh.dga.mta.dao.MtaMunicipiosDao;

@Repository
public class MtaMunicipiosDaoImpl implements MtaMunicipiosDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public MtaMunicipios getMunicipioByFilter(MtaMunicipios municipio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaMunicipios> getAll(Integer idDepartamento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MTA_MUNICIPIOS ");
		sql.append("WHERE 1 = 1 ");
		
		if(idDepartamento!=null && idDepartamento!=0) {
			sql.append(" AND ID_DEPARTAMENTO = "+idDepartamento);	
		}
		
		sql.append(" ORDER BY MUNICIPIO ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(MtaMunicipios.class));
	}

}
