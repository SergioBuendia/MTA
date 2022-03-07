package sv.gob.mh.dga.mta.seguridad.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.seguridad.model.Session;
import sv.gob.mh.dga.mta.common.sql.Param;

@Repository
public class SessionDAOImpl implements SessionDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public SessionDAOImpl(DataSource dataSource) throws MtaGenericDAOException {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Integer saveOrUpdate(Session session) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session get(Integer id_session) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getByToken(String token) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getIdUsuarioByToken(String token) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id_session) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Session getFull(Integer id_session, String[] tablas) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Session> list() throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Session getByParams(Param param) throws MtaGenericDAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
