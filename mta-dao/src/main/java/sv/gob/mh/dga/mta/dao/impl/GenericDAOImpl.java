package sv.gob.mh.dga.mta.dao.impl;


import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.util.Date;

public class GenericDAOImpl {

    private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);

    public final SQLUtil sqlUtil;
    public final JdbcTemplate jdbcTemplate;

    public GenericDAOImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlUtil = sqlUtil;
    }

    public Date extractDate(String fecha) {
        Date date = null;
        if (fecha != null && !fecha.isEmpty()) {
            try {
                date = DateUtil.getFecha(fecha);
            } catch (ParseException e) {
                logger.info("Error al extraer date del string", e);
            }
        }
        return date;
    }

    public Date extractDateTimesTamp(String fecha) {
        Date date = null;
        if (fecha != null && !fecha.isEmpty()) {
            try {
                date = DateUtil.getFechaHora(fecha);
            } catch (ParseException e) {
                logger.info("Error al extraer date del string", e);
            }
        }
        return date;
    }

    public String cleanText(String text) {
        return text.toUpperCase().trim();
    }
}
