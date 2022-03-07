package sv.gob.mh.dga.mta.common.exception;


import org.springframework.dao.DataIntegrityViolationException;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.util.CaracteresUtil;

public class BaseException extends Exception {
    private String errorCode;
    private String errorMessage;

    public BaseException() {
        super();
    }

    /**
     * Constructor
     *
     * @param errorCode, Codigo de error especificado en ErrorCodeConstante
     */
    public BaseException(String errorCode) {
        super(errorCode);

        this.errorCode = errorCode;
    }

    /**
     * Constructor
     *
     * @param errorCode,    Codigo de error especificado en ErrorCodeConstante
     * @param errorMessage, Mensaje de error
     */

    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);

        if (cause instanceof MtaQueryException) {
            System.err.println("*MgrQueryException*");

            Throwable t = cause.getCause();

            if (t instanceof java.sql.SQLSyntaxErrorException) {
                this.errorCode = ErrorCodeConstant.DAO_SINTAXIS_ERROR;
                this.errorMessage = "Error de Sintaxis:" + CaracteresUtil.reemplazaSaltosLinea(t.getMessage()).replace("\"", "'");
            } else if (t instanceof java.sql.SQLIntegrityConstraintViolationException) {
                this.errorCode = ErrorCodeConstant.DAO_INTEGRIDAD_VIOLADA;
                this.errorMessage = "Error de integridad:" + CaracteresUtil.reemplazaSaltosLinea(t.getMessage()).replace("\"", "'");
            } else if (t instanceof org.springframework.jdbc.BadSqlGrammarException) {
                this.errorCode = ErrorCodeConstant.DAO_SINTAXIS_ERROR;
                if (t.getCause() instanceof java.sql.SQLSyntaxErrorException)
                    this.errorMessage = "Sintaxis:" + CaracteresUtil.reemplazaSaltosLinea((t.getCause()).getMessage()).replace("\"", "'");
            } else if (t instanceof DataIntegrityViolationException) {
                this.errorCode = ErrorCodeConstant.DAO_INTEGRIDAD_VIOLADA;
                if (t.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException)
                    this.errorMessage = "Error de integridad!:" + CaracteresUtil.reemplazaSaltosLinea((t.getCause()).getMessage()).replace("\"", "'");
            }
        } else {
            Throwable t = cause;
            if (t instanceof java.sql.SQLSyntaxErrorException) {
                System.err.println("*Error de Sintaxis:*");

                this.errorCode = ErrorCodeConstant.DAO_SINTAXIS_ERROR;
                this.errorMessage = "Error de Sintaxis:" + CaracteresUtil.reemplazaSaltosLinea(t.getMessage()).replace("\"", "'");
            } else if (t instanceof java.sql.SQLIntegrityConstraintViolationException) {
                this.errorCode = ErrorCodeConstant.DAO_INTEGRIDAD_VIOLADA;
                this.errorMessage = "Error de integridad:" + CaracteresUtil.reemplazaSaltosLinea(t.getMessage()).replace("\"", "'");
            } else if (t instanceof org.springframework.jdbc.BadSqlGrammarException) {
                this.errorCode = ErrorCodeConstant.DAO_SINTAXIS_ERROR;
                if (t.getCause() instanceof java.sql.SQLSyntaxErrorException)
                    this.errorMessage = "Error de Sintaxis(" + CaracteresUtil.removerSaltosLinea(t.getCause().getMessage()) + "):" + CaracteresUtil.reemplazaSaltosLinea(((org.springframework.jdbc.BadSqlGrammarException) t).getSql()).replace("\"", "'");
            } else if (t instanceof DataIntegrityViolationException) {
                this.errorCode = ErrorCodeConstant.DAO_INTEGRIDAD_VIOLADA;
                if (t.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException)
                    this.errorMessage = "Error de integridad:" + CaracteresUtil.reemplazaSaltosLinea((t.getCause()).getMessage()).replace("\"", "'");
            } else if (t instanceof MtaServiceException) {
                if (t.getCause() != null)
                    this.errorMessage = CaracteresUtil.reemplazaSaltosLinea((t.getCause()).getMessage()).replace("\"", "'");
                else
                    this.errorMessage = CaracteresUtil.reemplazaSaltosLinea(cause.getMessage()).replace("\"", "'");
            } else if (t instanceof NullPointerException) {
                this.errorMessage = CaracteresUtil.reemplazaSaltosLinea("Error al procesar por valor vacio o nulo");
            } else {
                this.errorMessage = CaracteresUtil.reemplazaSaltosLinea(cause.getMessage()).replace("\"", "'");
            }
        }
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);

        this.errorMessage = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}