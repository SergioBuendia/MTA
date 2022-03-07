package sv.gob.mh.dga.mta.common.wrapper;

import java.io.Serializable;

public class ResponseWrapper<T> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2098676356942446701L;
	private String errorCode;
    private String errorMessage;
    private T response;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
    
}