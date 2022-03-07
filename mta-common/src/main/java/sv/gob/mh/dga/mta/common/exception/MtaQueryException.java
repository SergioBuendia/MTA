package sv.gob.mh.dga.mta.common.exception;

public class MtaQueryException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5874580979873394905L;

	public MtaQueryException() {
        super();
    }

    public MtaQueryException(String errorCode) {
        super(errorCode);
    }
    
    public MtaQueryException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public MtaQueryException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public MtaQueryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MtaQueryException(Throwable cause) {
        super(cause);
    }
    
}