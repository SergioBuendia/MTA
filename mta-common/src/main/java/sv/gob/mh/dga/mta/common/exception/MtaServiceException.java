package sv.gob.mh.dga.mta.common.exception;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;

public class MtaServiceException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MtaServiceException() {
        super();
    }

    public MtaServiceException(String errorMessage) {
        super(ErrorCodeConstant.SERV_ERROR, errorMessage);
    }
    
    public MtaServiceException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public MtaServiceException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public MtaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MtaServiceException(Throwable cause) {
        super(cause);
    }
}
