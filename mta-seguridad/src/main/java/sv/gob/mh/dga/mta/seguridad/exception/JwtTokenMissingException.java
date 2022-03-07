package sv.gob.mh.dga.mta.seguridad.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {

	public JwtTokenMissingException(String msg) {
        super(msg);
    }

}
