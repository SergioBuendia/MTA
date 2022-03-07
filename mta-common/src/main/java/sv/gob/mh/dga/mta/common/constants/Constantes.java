package sv.gob.mh.dga.mta.common.constants;

import java.util.List;

public class Constantes {
	
	public static List<String> tokensExpirados;

	public static Integer TIPO_MEDIDA_MODELO_PROBABILISTICO = 18;
	public static Integer TIPO_MEDIDA_RED_NEURONAL = 6;
	public static Integer TIPO_MEDIDA_CRITERIO_EXPERTO = 17;
	public static Integer TIPO_MEDIDA_METODO_EXCEPCION= 19;
  
	public static String MEDIDA_FUNCIONALIDAD_EVALUACION= "mgrProcesoEvaluacion";
	public static String MEDIDA_FUNCIONALIDAD_FISCALIZACION= "mgrProgramaFizcalizacion";
	public static String MEDIDA_FUNCIONALIDAD_PROC_SELECCION_CASOS= "mgrProcesoSeleccionCasos";
	public static String MEDIDA_FUNCIONALIDAD_BENFORD= "mgrBenford";
	
	//medidas - CONDICIONES ACCIONES
	public static Integer MEDIDA_TIPO_RESPUESTA_SELECTIVIDAD_REVISION = 27;
	public static Integer MEDIDA_TIPO_RESPUESTA_MENSAJE_VALIDACION  = 28;
	public static Integer MEDIDA_TIPO_RESPUESTA_VALOR= 29;
	public static Integer MEDIDA_TIPO_RESPUESTA_LISTA_VALORES = 572;
	public static Integer MEDIDA_TIPO_RESPUESTA_FORMULA= 573;
	public static Integer MEDIDA_TIPO_RESPUESTA_MEDICION= 574;
	public static Integer MEDIDA_TIPO_RESPUESTA_REGLA_NEGOCIO = 575;
	
	//Opciones de Administracion - Variables
	public static String TIPO_TABLA_TRANSACCIONAL = "T";
	public static String TIPO_TABLA_CONSOLIDADA = "C";
	
	public static Integer CLASE_PARAMETRO_ID_SUJETO_RIESGO = 64;
	
	public static String MENSAJE_RESPUESTA_POR_DEFECTO = "Ejecutado";
		
	public static String FORMATO_FECHA_HORA="MM/DD/YYYY HH24:MI";
	
	public final static long EXPIRATION_TIME = (1*60)*60000*10; // 10 DIAS
//	public final static long EXPIRATION_TIME = 10*6000; // 1minuto
	
	public final static String BASE_HOST_TENSORFLOW = "http://54.83.128.77:8088/";
	
	public final static String APP_TENSORFLOW = "api/models/";
		
	public final static String SECRET_AUDITORIA = "lUnth3r0Wee3!=20";
	 
	public static String APP_MGR_ONLINE = null;

	/**
	 * CONSTANTES PARA MODELOS
	 */
	public static String PROP_TABLE = "tabla";
	public static String PROP_PRIMARY_KEY = "primary_key";
	public static String PROP_PARAM = "?";

	/**
	 * OPERADORES
	 */

	public static final String STR_LIKE = "LIKE";
	public static final String STR_NOT_LIKE = "NOT LIKE";
	public  static final String STR_OR = "OR";

	/**
	 * CARACTERES
	 */

	public static final String STR_COMILLLAS_SIMPLES = "'";
	public static final String STR_COMILLLAS_DOBLES = "\"";
	public static final String STR_COMA = ",";
	public static final String STR_PORCENTAJE = "%";
	public static final String STR_ESPACIO = " ";
	public static final String STR_VACIO = "";
	public static final String STR_ABRIR_PARENTESIS = "(";
	public static final String STR_CERRAR_PARENTESIS = ")";


	/**
	 * CRON
	 */
	public static final String CRON_FREC_SECONDS = "FREC=SEG;";

	/**
	 * PROPIEDADES REPORTES
	 */
	public static final String DEFAULT_FILENAME_REPORT = "reporte.%s";
}
