package sv.gob.mh.dga.mta.seguridad.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.Session;
import sv.gob.mh.dga.mta.seguridad.model.JwtUserDetails;
import sv.gob.mh.dga.mta.seguridad.service.SessionService;

@Component
public class ControllerUtil {
	
	Logger logger = LoggerFactory.getLogger(ControllerUtil.class);
	
	/*@Autowired
	private SessionService sessionService;*/
	
	private Gson gson = new Gson();

	/**
	 * Obtener Session Id
	 * 
	 * @return int
	 */
	public int getSessionId() throws MtaServiceException {
		Object principal = null;
		JwtUserDetails forTest = null;
		
		if(SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			forTest = new JwtUserDetails("10626482", 32, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDYyNjQ4MiIsInVzZXJJZCI6Ii0yMTQ3NDgzNjQ4Iiwicm9sZSI6IlVTRVIiLCJleHAiOjE1NzkwMzQxNTl9.7G9EHij09thFYtKv1vFRWyYe_vTOyEy5J-ALMxlCfeogEzx5uwAARTmWYSkWmciIheNwMEbSAuK5bYeQi0NziQ", null);
			principal = forTest;
		}else {			
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
    	
    	JwtUserDetails userDetails = null;
    	
    	if (principal instanceof JwtUserDetails) {
    	  userDetails = (JwtUserDetails) principal;
    	}

		Session session = null;//sessionService.getSessionPorToken(userDetails.getToken());
		
		if (session != null) {
			return session.getId_session();
		} else {
			throw new MtaServiceException(ErrorCodeConstant.ESQ_00001, "Ninguna sesion existente");
		}
	}

	/**
	 * Obtener Id Usuario
	 *
	 * @return int
	 */
	public Integer getIdUsuario() throws MtaServiceException {
		Object principal = null;
		JwtUserDetails forTest = null;

		if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			forTest = new JwtUserDetails("10626482", 32, "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDYyNjQ4MiIsInVzZXJJZCI6Ii0yMTQ3NDgzNjQ4Iiwicm9sZSI6IlVTRVIiLCJleHAiOjE1NzkwMzQxNTl9.7G9EHij09thFYtKv1vFRWyYe_vTOyEy5J-ALMxlCfeogEzx5uwAARTmWYSkWmciIheNwMEbSAuK5bYeQi0NziQ", null);
			principal = forTest;
		} else {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}

		JwtUserDetails userDetails = null;

		if (principal instanceof JwtUserDetails) {
			userDetails = (JwtUserDetails) principal;
		}

		Integer id_usuario = null; //sessionService.getIdUsuarioPorToken(userDetails.getToken());

		if (id_usuario != null) {
			return id_usuario;
		} else {
			throw new MtaServiceException(ErrorCodeConstant.ESQ_00001, "Token no válido");
		}
	}
	
	/**
	 * Obtener Session
	 * 
	 * @return Session
	 */
	public Session getSession() throws MtaServiceException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	JwtUserDetails userDetails = null;
    	
    	if (principal instanceof JwtUserDetails) {
    	  userDetails = (JwtUserDetails) principal;
    	}
    	
		Session session = null;//sessionService.getSessionPorToken(userDetails.getToken());
		
		if (session != null) {
			return session;
		} else {
			throw new MtaServiceException(ErrorCodeConstant.ESQ_00001, "Ninguna sesion existente");
		}
	}

	/**
	 * @return the gson
	 */
	public Gson getGson() {
		return gson;
	}

	/**
	 * @param gson the gson to set
	 */
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
}
