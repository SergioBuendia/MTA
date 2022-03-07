package sv.gob.mh.dga.mta.common.exception;

public class MtaProcedureException extends BaseException {

	public MtaProcedureException() {
        super();
    }

    public MtaProcedureException(String errorCode) {
        super(errorCode);
    }
    
    public MtaProcedureException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public MtaProcedureException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public MtaProcedureException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MtaProcedureException(Throwable cause) {
        super(cause);
    }

}
