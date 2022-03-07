package sv.gob.mh.dga.mta.common.spring.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.apache.commons.collections.CollectionUtils;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.db.Sql;
import sv.gob.mh.dga.mta.common.exception.MtaProcedureException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.sql.Param;
import sv.gob.mh.dga.mta.common.sql.ParamOut;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.common.sql.RowOrder;
import sv.gob.mh.dga.mta.common.sql.SQLFrmkUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SQLUtilImpl implements SQLUtil {
	private final Logger LOGGER = LoggerFactory.getLogger(SQLUtilImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Long count(String sql) {
		String sqlCount = countData(sql);
		return queryForObject(sqlCount, Long.class);
	}

	@Override
	public Long count(String sql, Object... args) {
		String sqlCount = countData(sql);
		return queryForObject(sqlCount, Long.class, args);
	}

	@Override
	public List<Row> query(String sql) throws MtaQueryException {
		try {
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			return SQLFrmkUtil.listToRows(list);
		} catch (Exception e) {
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<Row> query(String sql, Object[] params) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);

			return SQLFrmkUtil.listToRows(list);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<RowOrder> queryOrderColumn(String sql, Object[] params, boolean lowerCase, boolean empty)
			throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			RowMapper<Map<String, Object>> rowMapper = new ColumnMapRowMapper() {

				protected Map<String, Object> createColumnMap(int columnCount) {
					return new LinkedHashMap<>(columnCount);
				}

			};

			List<Map<String, Object>> list = jdbcTemplate.query(sql, params, rowMapper);

			return SQLFrmkUtil.listToRows(list, lowerCase, empty);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<Row> query(String sql, Map<String, Object> params) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, params);

			return SQLFrmkUtil.listToRows(list);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public Row queryObject(String sql, Map<String, Object> params) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			Map<String, Object> mapObject = namedParameterJdbcTemplate.queryForMap(sql, params);

			return SQLFrmkUtil.objectToRow(mapObject);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public Row queryObject(String sql, Object[] params) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);

			return !list.isEmpty() ? SQLFrmkUtil.listToRows(list).get(0) : null;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<Integer> queryInteger(String sql, Object[] params) throws MtaQueryException {
		try {
			List<Integer> data = jdbcTemplate.query(sql, params, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
			return data;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<String> queryString(String sql, Object[] params) throws MtaQueryException {
		try {
			List<String> data = jdbcTemplate.query(sql, params, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			return data;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<String> queryString(String sql) throws MtaQueryException {
		try {
			List<String> data = jdbcTemplate.query(sql, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			return data;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T queryObject(String sql, Object[] params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			List<T> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));

			return !list.isEmpty() ? list.get(0) : null;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T queryObject(String sql, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			List<T> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));

			return !list.isEmpty() ? list.get(0) : null;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T querySingleObject(String sql, Object[] params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			return jdbcTemplate.queryForObject(sql, params, new SingleColumnRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T querySingleObject(String sql, Class<T> clazz) throws MtaQueryException {
		try {
			// LOGGER.debug("query:" + sql);

			return jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", sql + ":" +e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T querySingleObject(String sql, Map<String, Object> params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			return namedParameterJdbcTemplate.queryForObject(sql, params, new SingleColumnRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> List<T> query(String sql, Map<String, Object> params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			return namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> List<T> query(String sql, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);

			return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> List<T> queryMax(String sql, Class<T> clazz, int max) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			((JdbcTemplate) namedParameterJdbcTemplate.getJdbcOperations()).setMaxRows(max);

			return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> List<T> query(String sql, MapSqlParameterSource params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			List<T> list = namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));

			if (CollectionUtils.isNotEmpty(list)) {
				return list;
			}
			// Siempre debera devolver una lista vacia
			return new ArrayList<>();
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public List<Row> query(String sql, Param params) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			LOGGER.debug("params:" + params);

			List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, params);

			return SQLFrmkUtil.listToRows(list);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	public <T> List<T> query(String sql, Object[] params, Class<T> clazz) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			List<T> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));

			if (CollectionUtils.isNotEmpty(list)) {
				return list;
			}
			// Siempre debera devolver una lista vacia
			return new ArrayList<>();
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> List<T> queryMax(String sql, Object[] params, Class<T> clazz, int max) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			jdbcTemplate.setMaxRows(max);
			List<T> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<T>(clazz));

			if (CollectionUtils.isNotEmpty(list)) {
				return list;
			}
			// Siempre debera devolver una lista vacia
			return new ArrayList<>();
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public int update(String sql, Object[] params) throws MtaQueryException {
		try {
			LOGGER.debug("update:" + sql);

			return jdbcTemplate.update(sql, params);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public int update(String sql) throws MtaQueryException {
		try {
			LOGGER.debug("update:" + sql);

			return jdbcTemplate.update(sql);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public Integer update(String sql, MapSqlParameterSource params) throws MtaQueryException {
		LOGGER.debug("query:" + sql);
		try {
			Integer object = namedParameterJdbcTemplate.update(sql, params);

			return object;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public Integer update(String sql, Param params) throws MtaQueryException {
		LOGGER.debug("query:" + sql);
		try {
			Integer object = namedParameterJdbcTemplate.update(sql, params);
			return object;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}
	
	@Override
 	public Integer updateObjects(String sql, Object... params ) throws MtaQueryException {
		LOGGER.debug("query:" + sql);
		try {
			LOGGER.debug("update:" + sql);

			return jdbcTemplate.update(sql,params);
			
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}


	@Override
	public Row executePackage(String pakage, String procedure, Param paramsIn, ParamOut paramsOut)
			throws MtaProcedureException {
		try {
			Row result = new Row();

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(pakage)
					.withProcedureName(procedure);

			SqlParameterSource in = new MapSqlParameterSource(paramsIn);

			if (paramsOut != null)
				for (String key : paramsOut.keySet()) {
					simpleJdbcCall.addDeclaredParameter(new SqlOutParameter(key, paramsOut.get(key)));
				}

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

			for (String key : simpleJdbcCallResult.keySet()) {
				result.put(key.toLowerCase(), simpleJdbcCallResult.get(key));
			}

			return result;
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaProcedureException(ErrorCodeConstant.EPR_00000);
		}
	}

	@Override
	public void executePackage(String pakage, String procedure, Param paramsIn) throws MtaProcedureException {
		try {
  
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(pakage)
					.withProcedureName(procedure);

			SqlParameterSource in = new MapSqlParameterSource(paramsIn);

			//Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			simpleJdbcCall.execute(in);

 
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaProcedureException(ErrorCodeConstant.EPR_00000, e.getMessage(),e);
		}
	}

	@Override
	public List<Row> queryMax(String sql, Object[] params, int max) throws MtaQueryException {
		try {
			LOGGER.debug("query:" + sql);
			jdbcTemplate.setMaxRows(max);
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);

			return SQLFrmkUtil.listToRows(list);
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaQueryException(ErrorCodeConstant.ESQ_00000, e.getMessage(), e);
		}
	}

	@Override
	public <T> T queryForObject(String sql, Class<T> clazz, Object... args) {
		RowMapper<T> mapper = getSingleColumnRowMapper(clazz);
		List<T> results = null;
		if (args == null) {
			results=jdbcTemplate.query(sql, getSingleColumnRowMapper(clazz));
		} else {
			results=jdbcTemplate.query(sql, getSingleColumnRowMapper(clazz), args);
		}
		return DataAccessUtils.requiredSingleResult(results);
	}

	@Override
	public <T> T queryForObject(String sql, Class<T> clazz) {
		return queryForObject(sql, clazz, null);
	}

	protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
		return new SingleColumnRowMapper<T>(requiredType);
	}

	@Override
	public Long getSecuence(String sequence) {

		// JdbcTemplate temp = new JdbcTemplate(DBUtils.getPooledDataSource());
		return jdbcTemplate.query("select next value for " + sequence, new ResultSetExtractor<Long>() {

			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getLong(1);
			}
		});
	}

	@Override
	public Long getNextValue(String sequence) throws MtaQueryException {
		StringBuilder secuencia = new StringBuilder(Sql.getAutoincrement(sequence));
		String sql = null;

		if (Sql.BD.equals(Sql.MOTORES_BD.ORACLE.getNombre())) {
			sql = "SELECT " + secuencia + " FROM dual";
		} else {
			sql = "SELECT " + secuencia;
		}
		try {
			return querySingleObject(sql, Long.class);
		} catch (MtaQueryException e) {
			throw new MtaQueryException(ErrorCodeConstant.DAO_00001, "Error al obtener el valor de:" + sequence, e);
		}
	}

	@Override
	public String toFormatChar(String column, String pattern) {
		String sql;
		if (Sql.BD.equals(Sql.MOTORES_BD.ORACLE.getNombre())) {
			sql = String.format(" TO_CHAR(%s, '%s') ", column, pattern);
		} else {
			sql = String.format(" FORMAT(%s, '%s') ", column, pattern);
		}
		return sql;
	}

	@Override
	public String generateQueryForNull(Integer value) {
		String sql = value == null ? " IS NULL " : String.format(" = %d ", value);
		return sql;
	}

	@Override
	public String countData(String query) {
		return String.format("SELECT COUNT(*) FROM (%s)", query);
	}

	@Override
	public String getNextVal(String sequence) {
		String sql;
		if (Sql.BD.equals(Sql.MOTORES_BD.ORACLE.getNombre())) {
			sql = String.format("%s.NEXTVAL", sequence);
		} else {
			sql = String.format("NEXT VALUE FOR %s", sequence);
		}
		return sql;
	}

	@Override
	public <T> T getNextValue(String sequence, Class<T> clazz) throws MtaQueryException {
		StringBuilder secuencia = new StringBuilder(Sql.getAutoincrement(sequence));
		String sql = null;

		if (Sql.BD.equals(Sql.MOTORES_BD.ORACLE.getNombre())) {
			sql = "SELECT " + secuencia + " FROM dual";
		} else {
			sql = "SELECT " + secuencia;
		}
		try {
			return querySingleObject(sql, clazz);
		} catch (MtaQueryException e) {
			throw new MtaQueryException(ErrorCodeConstant.DAO_00001, "Error al obtener el valor de:" + sequence, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> executePackageList(String pakage, String procedure, Param paramsIn, String paramsOut,
			Class<T> clazz) throws MtaProcedureException {
		try {
  
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(pakage)
					.withProcedureName(procedure).returningResultSet(paramsOut, new BeanPropertyRowMapper<T>(clazz));

			SqlParameterSource in = new MapSqlParameterSource(paramsIn);

			return simpleJdbcCall.executeObject(List.class, in);

			// simpleJdbcCall.returningResultSet(paramsOut, new
			// BeanPropertyRowMapper<T>(clazz)) ;

			/*
			 * if(paramsOut!=null) for (String key : paramsOut.keySet()) {
			 * simpleJdbcCall.addDeclaredParameter(new SqlOutParameter(key,
			 * paramsOut.get(key))); }
			 * 
			 * Map<String, Object> simpleJdbcCallResult =
			 * simpleJdbcCall.execute(in);
			 * 
			 * 
			 * for (String key : simpleJdbcCallResult.keySet()) {
			 * result.put(key.toLowerCase(), simpleJdbcCallResult.get(key)); }
			 * 
			 * logger.debug("executePackage:" + simpleJdbcCallResult);
			 * logger.debug("paramsOut:" + paramsOut); logger.debug("result:" +
			 * result); return result;
			 */
		} catch (Exception e) {
			LOGGER.error("Error : {}", e.getMessage(), e);
			throw new MtaProcedureException(ErrorCodeConstant.EPR_00000);
		}
	}

	@Override
	public List<Map<String, Object>> queryLinked(String sql) throws MtaQueryException {
		RowMapper<Map<String, Object>> rowMapper = new ColumnMapRowMapper() {
			protected Map<String, Object> createColumnMap(int columnCount) {
				return new LinkedHashMap<>(columnCount);
			}
		};
		List<Map<String, Object>> results = jdbcTemplate.query(sql, rowMapper);
		return results;
	}
}
