package sv.gob.mh.dga.mta.common.util;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import sv.gob.mh.dga.mta.common.constants.Constantes;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtil {

	private static final Logger logger = LoggerFactory.getLogger(GeneralUtil.class);

	/**
	 *
	 * @param data
	 * @return boolean
	 */
	public static boolean isEmptyOrNull(String data) {
		return data == null || data.isEmpty();
	}

	/**
	 *
	 * @param text
	 * @return Integer
	 */
	public static Integer stringToInteger(String text) {
		Integer nro = null;
		try {
			nro = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			logger.info("No se pudo convertir a número", e.getMessage());
		}
		return nro;
	}

	public static void guardarBase64Disco(byte[] bytes, String rutaDisc) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String name = formatter.format(date);

		FileOutputStream os = new FileOutputStream(rutaDisc + "/img" + name);
		os.write(bytes);
		os.close();
	}

	public static String arrayConcat(String... texts) {
		StringBuilder data = new StringBuilder();
		for (String text : texts) data.append(text);
		return data.toString();
	}

	public static String limpiarNombre(String filename) {
		String[] data = filename.split("-|_");
		String newFilename = "";
		for (String file: data){
			newFilename += file.trim() + " ";
		}
		newFilename = newFilename.toLowerCase();
		newFilename = newFilename.substring(0, 1).toUpperCase() + newFilename.substring(1);
		newFilename = newFilename.trim();
		return newFilename;
	}

	/*public static String obtenerDuracion(String filename) {
		IContainer container = IContainer.make();
		int result = container.open(filename, IContainer.Type.READ, null);
		long ms = container.getDuration();
		return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(ms),
				TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
				TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
	}*/

	public static String generarQuery(String columna, String operador, List<String> valores, boolean esCuantitativo) {
		operador = limpiarCadena(operador);
		String EXTREMOS = esCuantitativo ? Constantes.STR_ESPACIO : Constantes.STR_COMILLLAS_SIMPLES;
		operador = operador.equals("<>") ? "NOT IN" : operador;
		String query;
		if (Constantes.STR_LIKE.equals(operador) || Constantes.STR_NOT_LIKE.equals(operador)) {
			query = generarSentenciaLikeOrNotLike(columna, operador, valores, EXTREMOS);
		} else {
			query = generarSentencia(columna, operador, valores, EXTREMOS);
		}
		return query;
	}

	public static String limpiarCadena(String columna) {
		String[] data = columna.toUpperCase().split(" +");
		StringBuilder newColumna = new StringBuilder();
		for (String column : data) {
			newColumna.append(column)
					.append(Constantes.STR_ESPACIO);
		}
		String result = newColumna.toString().trim();
		return result;
	}

	private static String generarSentenciaLikeOrNotLike(String columna, String operador, List<String> valores, String EXTREMOS) {
		StringBuilder sentencia = new StringBuilder();
		sentencia.append(Constantes.STR_ABRIR_PARENTESIS);
		for (String valor : valores) {
			sentencia.append(String.format("%s %s %s%s%s %s ", "UPPER(" + columna + ")", operador, EXTREMOS + "%", valor.toUpperCase(), "%" + EXTREMOS, Constantes.STR_OR));
		}
		sentencia = new StringBuilder(sentencia.toString().substring(0, sentencia.length() - 3));
		sentencia.append(Constantes.STR_CERRAR_PARENTESIS);
		return sentencia.toString();
	}

	public static String generarSentencia(String columna, String operador, List<String> valores, String EXTREMOS) {
		StringBuilder sentencia = new StringBuilder();
		if(operador.equals("="))
			operador = " in ";
		
		if(operador.equals("<>"))
			operador = " not in ";
		
		sentencia.append(String.format("%s %s ", columna, operador));
		sentencia.append(Constantes.STR_ABRIR_PARENTESIS);
		for (String valor : valores) {
			sentencia.append(String.format("%s%s%s%s ", EXTREMOS, valor, EXTREMOS, Constantes.STR_COMA));
		}
		sentencia = new StringBuilder(sentencia.toString().substring(0, sentencia.length() - 2));
		sentencia.append(Constantes.STR_CERRAR_PARENTESIS);
		return sentencia.toString();
	}

	/**
	 * Reportes
	 */
	public static String limpiarTextoCabecera(String text) {
		return text.replace("_", " ").replace("-", " ").trim().toUpperCase();
	}

	// JEXCEL API
	public static void generarCabecera(WritableSheet sheet, List<String> cabecera, Integer row) throws WriteException {
		// Cabecera
		for (int col = 0; col < cabecera.size(); col++) {
			Label label = new Label(col, row, limpiarTextoCabecera(cabecera.get(col)));
			sheet.addCell(label);
		}
	}

	/**
	 * MANEJO Y FORMATO DE FECHAS
	 */
	public static String toStringHH24MM(Date date) {
		String fecha = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			fecha = sdf.format(date);
		}
		return fecha;
	}

	public static String encodeURL(String value) {
		String newValue = null;
		try {
			newValue = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			logger.error("Error al codificar URL");
		}
		return newValue;
	}

	public static String decodeURL(String value) {
		String newValue = null;
		try {
			newValue = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			logger.error("Error al decodificar URL");
		}
		return newValue;
	}

	public static boolean empty(final String s) {
		return s == null || s.trim().isEmpty();
	}

	public static boolean empty(Object s) {
		return s == null || s.toString().trim().isEmpty();
	}

	public static boolean empty(Collection coll) {
		return coll == null || coll.isEmpty();
	}

	public static String transformCodes(String codes, String descriptions) {
		String[] codigos = !empty(codes) ? codes.split(";") : new String[]{};
		String[] descripciones = !empty(descriptions) ? descriptions.split(";") : new String[]{};
		StringBuilder detalle = new StringBuilder();
		if (codigos.length == descripciones.length) {
			for (int i = 0; i < codigos.length; i++) {
				detalle.append((descripciones[i].equals("SIN DESCRIPCIÓN") || empty(descripciones[i])) ?
						codigos[i] : (codigos[i] + " - " + descripciones[i]).length() > 70 ?
						(codigos[i] + " - " + descripciones[i]).substring(0, 70).concat("...") + "; " :
						(codigos[i] + " - " + descripciones[i]) + "; ");
			}
		} else {
			return StringUtils.join(codigos, " - ");
		}
		return !empty(detalle.toString()) && detalle.toString().endsWith("; ") ?
				detalle.substring(0, detalle.length() - 2) : detalle.toString();
	}

	public static Map<String, Object> introspect(Object obj) throws Exception {
		Map<String, Object> result = new HashMap<>();
		BeanInfo info = Introspector.getBeanInfo(obj.getClass());
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			Method reader = pd.getReadMethod();
			String key = pd.getName();
			if (reader != null && !key.equals("class"))
				result.put(pd.getName(), reader.invoke(obj));
		}
		return result;
	}

	public static String toEmpty(Object data) {
		if (!empty(data))
			return data.toString().trim();
		return "";
	}

	public static String limpiandoComillas(Object data) {
		return toEmpty(data).replace("\"", "'").replace("\t", " ");
	}

	public static String limitText(String text, Integer limit) {
		String newText = toEmpty(text);
		if (newText.length() > limit)
			return newText.substring(0, limit - 1).concat("...");
		return newText;
	}

	public static List<String> extractQuerys(String text) {
		List<String> querys = new ArrayList<>();
		String regex = "^((?i)BEGIN|(?i)CREATE|(?i)DROP|(?i)INSERT|(?i)UPDATE|(?i)SELECT|(?i)WITH|(?i)DELETE)(?:[^;']|(?:'[^']+'))+;\\s*$";
		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(text);

		// Check all occurrences
		while (matcher.find()) {
			String query = matcher.group().replaceAll("\n", " ").trim();
			if (isQuerySafe(query))
				querys.add(cleanQuery(query));
		}
		return querys;
	}

	public static boolean isQuerySafe(String query) {
		query = query.trim().toLowerCase();
		if (query.startsWith("drop"))
			return !(query.contains("table") || query.contains("database"));
		return true;
	}

	public static String cleanQuery(String query) {
		query = query.trim();
		if (query.endsWith(";"))
			query = query.substring(0, query.length() - 1);
		query = query.replaceAll("\\$\\{separator\\}", ";");
		return query;
	}

	/*public static void main(String[] args) {
		String query = "begin\n" +
				"  as_sftp.open_connection( i_host => '10.100.109.140', i_trust_server => true )${separator}\n" +
				"  as_sftp.close_connection${separator}\n" +
				"end;";
		for (String newQuery : extractQuerys(query))
			System.out.println(newQuery);
	}*/
}