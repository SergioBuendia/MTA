package sv.gob.mh.dga.mta.common.constants;

public class ErrorCodeConstant {

    /**
     * Errores que pueden ocurrir al ejecutar querys
     */
    public final static String ESQ_00000 = "ESQ0000";//Error generico que ocurre al ejecutar el service
    public final static String ESQ_00001 = "ESQ0001";//EL query no trajo ningun elemento, cuando deberia traer almenos uno
    public final static String ESQ_00002 = "ESQ0002";//Se eperaba que el query trajera 1 elemento, pero trajo mas de uno
    public final static String ESQ_00003 = "ESQ0003";//El query no actualizó la cantidad de registros esperado.
    public final static String ESQ_QUERY_ERROR = "ESQ0003";// El query no actualizó la cantidad de registros esperado.

    public final static String DAO_00000 = "DAO0000";//Error cuando ocurre un error en el DAO.
    public final static String DAO_00001 = "DAO0001";//Error cuando el DAO generar un error en el query de consulta.
    public final static String DAO_INTEGRIDAD_VIOLADA = "DAO0002";//Error cuando ocurre un error en el DAO.
    public final static String DAO_SINTAXIS_ERROR = "DAO0003";//Error cuando ocurre un error en el DAO.
    public final static String DAO_ERROR_QUERY = "DAO_ERROR_QUERY";//Error cuando el DAO generar un error en el query de consulta.
    public final static String DAO_VACIO = "DAO_ERROR_VACIO";//Error cuando no devuelve nada.

    /**
     * Error de validaciones
     */
    public final static String ESQ_ERROR_VALIDACION = "ESQ_ERROR_VALIDACION"; //Error de validacion de campos 


    /**
     * Error en Servidor
     */

    public final static String SE00001 = "SE00001"; // No existe el directorio en el servidor

    /**
     * Errores que pueden ocurrir al ejecutar un procedure
     */
    public final static String EPR_00000 = "EPR0000";//Error generico que ocurre al ejecutar un procedure

    /**
     * Error de validacion de informacion de objetos recibidos en el request
     */
    public final static String ERO_00000 = "ERO0000";//Error generico que ocurre al ejecutar la validacion de objetos en el request
    public final static String ERO_00001 = "ERO0001";//Se esperaba que la lista tenga almenos un elemento

    /**
     * Errores que pueden ocurrir en el acceso a datos
     */
    public final static String EAD_00000 = "EPR0000";//Error generico en el acceso a datos

    /**
     * Este valor devuelve el procedure cuando no se ejecuta correctamente
     */
    public final static String ERROR_PROCEDURE_OUTPUT = "error";

    public final static String ERROR_VALIDATION_GENERICO = "ESQ0000";

    public final static String ERROR_VALIDATION_SERVICIOS = "ESV0000";


    public final static String ERROR_NET_REST = "NET001";


    /**
     * Errores que pueden ocurrir al ejecutar un servicio
     */
    public final static String SERV_PARAMETROS_INCORRECTOS = "SERV0001";//Error cuando el servicio recibe parametros incorrectos
    public final static String SERV_VALIDACION = "SERV0002";//Error cuando hay un  error de validacion
    public final static String SERV_ERROR = "SERV0003";//Error cuando no se ejecuto la transaccion correctamente en el sevicio

    // ERROR AL ENVIAR CORREO
    public final static String SEND_EMAIL_FAILED = "FAILED SEND EMAIL";

    // ERROR AES
    public final static String ERROR_AES = "ERROR_AES";
}
