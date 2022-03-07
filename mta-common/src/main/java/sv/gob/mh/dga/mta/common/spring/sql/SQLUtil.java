package sv.gob.mh.dga.mta.common.spring.sql;

import sv.gob.mh.dga.mta.common.exception.MtaProcedureException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.sql.Param;
import sv.gob.mh.dga.mta.common.sql.ParamOut;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.common.sql.RowOrder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import java.util.List;
import java.util.Map;

public interface SQLUtil {

    Long count(String sql);

    Long count(String sql, Object... args);

    List<Row> query(String sql) throws MtaQueryException;

    List<Row> query(String sql, Object[] params) throws MtaQueryException;

    List<RowOrder> queryOrderColumn(String sql, Object[] params, boolean lowerCase, boolean empty) throws MtaQueryException;

    List<Row> query(String sql, Map<String, Object> params) throws MtaQueryException;

    Row queryObject(String sql, Map<String, Object> params) throws MtaQueryException;

    Row queryObject(String sql, Object[] params) throws MtaQueryException;

    List<Integer> queryInteger(String sql, Object[] params) throws MtaQueryException;

    List<String> queryString(String sql, Object[] params) throws MtaQueryException;

    List<String> queryString(String sql) throws MtaQueryException;

    <T> T queryObject(String sql, Object[] params, Class<T> clazz) throws MtaQueryException;

    <T> T queryObject(String sql, Class<T> clazz) throws MtaQueryException;

    <T> T querySingleObject(String sql, Object[] params, Class<T> clazz) throws MtaQueryException;

    <T> T querySingleObject(String sql, Class<T> clazz) throws MtaQueryException;

    <T> T querySingleObject(String sql, Map<String, Object> params, Class<T> clazz) throws MtaQueryException;

    <T> List<T> query(String sql, Map<String, Object> params, Class<T> clazz) throws MtaQueryException;

    <T> List<T> query(String sql, Class<T> clazz) throws MtaQueryException;

    <T> List<T> queryMax(String sql, Class<T> clazz, int max) throws MtaQueryException;

    <T> List<T> query(String sql, MapSqlParameterSource params, Class<T> clazz) throws MtaQueryException;

    List<Row> query(String sql, Param params) throws MtaQueryException;

    <T> List<T> query(String sql, Object[] params, Class<T> clazz) throws MtaQueryException;

    <T> List<T> queryMax(String sql, Object[] params, Class<T> clazz, int max) throws MtaQueryException;

    int update(String sql, Object[] params) throws MtaQueryException;

    int update(String sql) throws MtaQueryException;

    Integer update(String sql, MapSqlParameterSource params) throws MtaQueryException;

    Integer update(String sql, Param params) throws MtaQueryException;

    Integer updateObjects(String sql, Object... params) throws MtaQueryException;

    Row executePackage(String pakage, String procedure, Param paramsIn, ParamOut paramsOut) throws MtaProcedureException;

    void executePackage(String pakage, String procedure, Param paramsIn) throws MtaProcedureException;

    <T> List<T> executePackageList(String pakage, String procedure, Param paramsIn, String paramsOut, Class<T> clazz) throws MtaProcedureException;

    List<Row> queryMax(String sql, Object[] params, int max) throws MtaQueryException;

    Long getSecuence(String sequence);

    Long getNextValue(String sequence) throws MtaQueryException;

    String getNextVal(String sequence);

    <T> T getNextValue(String sequence, Class<T> clazz) throws MtaQueryException;

    <T> T queryForObject(String sql, Class<T> clazz);

    <T> T queryForObject(String sql, Class<T> clazz, Object... args);

    List<Map<String, Object>> queryLinked(String sql) throws MtaQueryException;

    String toFormatChar(String column, String pattern);

    String generateQueryForNull(Integer value);

    String countData(String query);
}