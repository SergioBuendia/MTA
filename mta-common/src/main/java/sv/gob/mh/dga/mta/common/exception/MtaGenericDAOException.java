package sv.gob.mh.dga.mta.common.exception;

public class MtaGenericDAOException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8382303650698667443L;

	public MtaGenericDAOException() {
        super();
    }

    public MtaGenericDAOException(String errorCode) {
        super(errorCode);
    }
    
    public MtaGenericDAOException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public MtaGenericDAOException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public MtaGenericDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MtaGenericDAOException(Throwable cause) {
        super(cause);
    }

}
