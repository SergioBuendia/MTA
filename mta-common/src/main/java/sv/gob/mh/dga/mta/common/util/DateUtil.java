package sv.gob.mh.dga.mta.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final Logger  logger = LoggerFactory.getLogger(DateUtil.class);

    private final static SimpleDateFormat formatterFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final static SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
    private final static SimpleDateFormat formatterDay = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Devuelve un string en formato fecha para el STORE
     *
     * @param date
     * @return
     */
    public static String toDBString(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static boolean esHoy(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String parametro = sdf.format(date);
        String hoy = sdf.format(new Date());
        return parametro.equals(hoy);
    }

    public static Timestamp getFechaActual() {
        return new Timestamp(new Date().getTime());
    }

    public static Date getFechaActualTruncada() {
        Date today = new Date();
        try {
            return formatterDay.parse(formatterDay.format(today));
        } catch (ParseException e) {
            return null;
        }
    }

    public static String toString(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(date);
        return fecha;
    }

    public static String toStringHH24MM(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha = sdf.format(date);
        return fecha;
    }

    public static String toStringHH24MMSS(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = sdf.format(date);
        return fecha;
    }

    public static String toStringDMYHH24MM(Date date) {
        if (date == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha = sdf.format(date);
        return fecha;
    }

    /**
     * Retorna la fecha de inicio de vigencia
     *
     * @param date
     * @return
     */
    public static Timestamp getFechaInicioVigencia(Date date) {
        if (esHoy(date))
            return new Timestamp(new Date().getTime());
        else
            return new Timestamp(date.getTime());
    }

    /**
     * Retorna la fecha de inicio de vigencia
     *
     * @param date
     * @return
     */
    public static Timestamp getFechaFinVigencia(Date date) {
        if (date == null)
            return null;
        else {
            if (esHoy(date))
                return new Timestamp(new Date().getTime());
            else {
                String format = "dd/MM/yyyy HH:mm:ss";
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                String fecha = toDBString(date) + " 23:59:59";

                try {
                    return new Timestamp(formatter.parse(fecha).getTime());
                } catch (ParseException e) {
                    return null;
                }
            }
        }
    }

    public static Date getFecha(String fecha) throws ParseException {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(fecha);
    }

    public static Date getFechaHora(String fecha) throws ParseException {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(fecha);
    }


    /**
     * Devuelve en formato dd/MM/yyyy HH:mm:ss
     *
     * @param fecha
     * @return
     */
    public static String formatFull(Date fecha) {
        return formatterFull.format(fecha);
    }


    public static String formatTime(Date fecha) {
        return formatterTime.format(fecha);
    }

    public static String formatDay(Date fecha) {
        return formatterDay.format(fecha);
    }

    public static int getDia(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getAnio(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.YEAR);
    }


    /**
     * De 1 a 12
     *
     * @param fecha
     * @return
     */
    public static int getMes(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * De 1 a 12
     *
     * @param fecha
     * @return
     */
    public static int getSemana(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }


    public static Date addAnio(Date fecha, int anios) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.YEAR, anios);
        return cal.getTime();
    }

    public static Date addMes(Date fecha, int anios) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, anios);
        return cal.getTime();
    }

    public static Date addDia(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, dias);
        return cal.getTime();
    }

    public static Date subtractDays(Date fecha, int dias) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        cal.add(Calendar.DATE, -dias);
        return cal.getTime();
    }

    public static Date addHour(Date fecha, int horas) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.HOUR_OF_DAY, horas);
        return cal.getTime();
    }

    public static Date addMinute(Date fecha, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date primerDiaMes(Date date) {
        Calendar cal_ini = Calendar.getInstance();
        cal_ini.setTime(date);
        cal_ini.set(Calendar.DAY_OF_MONTH, 1);
        return cal_ini.getTime();
    }

    public static Date trunc(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date truncHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date ultimoDiaMes(Date date) {
        Calendar cal_ini = Calendar.getInstance();
        cal_ini.setTime(date);
        cal_ini.set(Calendar.DAY_OF_MONTH, 1);
        cal_ini.set(Calendar.DATE, cal_ini.getActualMaximum(Calendar.DATE));
        return cal_ini.getTime();
    }

    public static int dias(Date fec_ini, Date fec_fin) {
        Calendar cal_ini = Calendar.getInstance();
        cal_ini.setTime(fec_ini);

        Calendar cal_fin = Calendar.getInstance();
        cal_fin.setTime(fec_fin);

        long diff = fec_fin.getTime() - fec_ini.getTime();
        long dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return (int) dias;
    }

    public static int horas(Date fec_ini, Date fec_fin) {
        Calendar cal_ini = Calendar.getInstance();
        cal_ini.setTime(fec_ini);

        Calendar cal_fin = Calendar.getInstance();
        cal_fin.setTime(fec_fin);

        long diff = fec_fin.getTime() - fec_ini.getTime();
        long dias = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
        return (int) dias;
    }


    /**
     * Meses anteriores hasta la fecha actual
     *
     * @param fechaInicioVigencia
     * @return
     */
    public static List<Date> mesesAnteriores(Date fechaInicioVigencia) {
        Calendar calendarioEjecucion = Calendar.getInstance();
        calendarioEjecucion.setTime(fechaInicioVigencia);

        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(new Date());
        //fechaActual.add(Calendar.MONTH, -1);
        //ejecutar el mes anterior de todas maneras
        GregorianCalendar mesAnterior = new GregorianCalendar();
        mesAnterior.setTime(fechaInicioVigencia);//INICIO CON FECHA ACTUAL

        List<Date> fechas = new ArrayList<>();

        while (Integer.parseInt(mesAnterior.get(Calendar.YEAR) + String.format("%02d", mesAnterior.get(Calendar.MONTH))) <=
                Integer.parseInt(fechaActual.get(Calendar.YEAR) + String.format("%02d", fechaActual.get(Calendar.MONTH)))) {

            fechas.add(mesAnterior.getTime());
            mesAnterior.add(Calendar.MONTH, 1);
        }
        return fechas;
    }

    public static Date getUTCtoDate(Date date) {
        date = DateUtil.addHour(date, -6); // PARAMETRIZAR EL UTC
        return date;
    }

    public static String formatStringDate(String date, String format, String newFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        SimpleDateFormat simpleDateNewFormat = new SimpleDateFormat(newFormat);
        String customFormatDate = "";
        try {
            customFormatDate = simpleDateNewFormat.format(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            logger.info("Error al trannsformar la fecha a un nuevo formato", e);
        }
        return customFormatDate;
    }
}