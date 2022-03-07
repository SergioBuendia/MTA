package sv.gob.mh.dga.mta.seguridad.exception;

import sv.gob.mh.dga.mta.common.exception.BaseException;

public class MtaControllerException extends BaseException {
    
	public MtaControllerException() {
        super();
    }

    public MtaControllerException(String errorCode) {
        super(errorCode);
    }
    
    public MtaControllerException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    
    public MtaControllerException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public MtaControllerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MtaControllerException(Throwable cause) {
        super(cause);
    }
    
}