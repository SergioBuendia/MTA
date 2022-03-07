package sv.gob.mh.dga.mta.seguridad.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import sv.gob.mh.dga.mta.common.constants.Constantes;
import sv.gob.mh.dga.mta.common.exception.BaseException;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.seguridad.model.LogDto;
import sv.gob.mh.dga.mta.common.util.CaracteresUtil;
import sv.gob.mh.dga.mta.common.util.GeneralUtil;
import sv.gob.mh.dga.mta.common.wrapper.ResponseWrapper;
import sv.gob.mh.dga.mta.seguridad.exception.JwtTokenMalformedException;
import sv.gob.mh.dga.mta.seguridad.exception.JwtTokenMissingException;
import sv.gob.mh.dga.mta.seguridad.exception.MtaControllerException;
import sv.gob.mh.dga.mta.seguridad.handler.LogHandler;
import sv.gob.mh.dga.mta.seguridad.model.JwtAuthenticationToken;
import sv.gob.mh.dga.mta.seguridad.util.ControllerUtil;
import sv.gob.mh.dga.mta.seguridad.util.MyHttpServletRequestWrapper;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Autowired
	private Environment env;

	/*@Autowired
	private AuditoriaHandler auditoriaHandler;*/

	@Autowired
	private LogHandler logHandler;

	/*@Autowired
	private LogService logService;*/

	@Autowired
	private ControllerUtil controllerUtil;

	public JwtAuthenticationTokenFilter() {
		super("/rest/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
		
		logger.debug("inicio - attemptAuthentication");
		
		String header = httpServletRequest.getHeader("Authorization");
		String method = httpServletRequest.getMethod();

		String clienteExterno = env.getProperty("cliente.externo");
		String multiple = env.getProperty("autenticacion.multiple");

		String remoteAddr = "";
		if (httpServletRequest != null) {
			remoteAddr = httpServletRequest.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = httpServletRequest.getRemoteAddr();
			}
		}

		String ip = remoteAddr;
		String uri = httpServletRequest.getRequestURI();
		boolean externo = false;
		if (clienteExterno != null) {
			String[] arr = clienteExterno.split("@");
			String[] ipExternos = arr[0].split(",");
			String url = arr[1];

			for (int i = 0; i < ipExternos.length; i++) {
				if (ipExternos[i].equals(ip) && uri.indexOf(url) > 0)
					externo = true;
			}
		}

		if (!externo)
			if ((header == null || !header.startsWith("Bearer ")) && !method.equals("OPTIONS")) {
				throw new JwtTokenMissingException("No JWT token found in request headers");
			}

		JwtAuthenticationToken authRequest;

		if (externo || method.equals("OPTIONS")) {
			authRequest = new JwtAuthenticationToken(
					"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTQwODYwNyIsInVzZXJJZCI6Ii0yMTQ3NDgzNjQ4Iiwicm9sZSI6IlVTRVIifQ.WXpEJ-jypV2TQa1VWP-qiajfmjXfMh9w_z6JZI6Xdth7aZxlJ5sgWRKXyB8Mh3cYoCtZBCujSsWuRTg6OO2ecA");
		} else {

			String authenticationToken = header.substring(7);

			boolean permitido = true;
			if (Constantes.tokensExpirados == null)
				Constantes.tokensExpirados = new ArrayList<String>();

			for (String token : Constantes.tokensExpirados) {
				if (authenticationToken.equals(token))
					permitido = false;
			}

			if (!"true".equals(multiple) && !permitido) {
				throw new JwtTokenMalformedException("Token ya no es valido");
			}

			authRequest = new JwtAuthenticationToken(authenticationToken);
		}
		
		logger.debug("fin - attemptAuthentication");

		return getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		HttpServletRequest requestWrapper = new MyHttpServletRequestWrapper(request);
		logger.debug("antes de auditar");
		//auditoriaHandler.auditar(requestWrapper);
		logger.debug("despues de auditar");
		try {
			Date dateIni = new Date();
			chain.doFilter(requestWrapper, response);
			Date dateFin = new Date();
			long differenceInMillis =  dateFin.getTime()- dateIni.getTime();
//			long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);
			if (differenceInMillis>= 500) {
				LogDto log = new LogDto();
				log.setId_servicio(1);//TODO: Cambiar lo del compendio
				try {
					log.setId_session(controllerUtil.getSessionId());
				} catch (MtaServiceException e1) {
				}
				log.setMensaje(String.format("Tiempo Excesivo: %d", differenceInMillis));
				log.setDescripcion(String.format("Fecha Inicio: %s - Fecha Fin: %s", dateIni, dateFin));
				log.setUrl(requestWrapper.getRequestURI());
				log.setTipo("I");
				//logService.grabar(log);
			}
		} catch (Throwable e) {
			logger.error("ERROR!!", e);
			/*if (e.getCause() instanceof MtaControllerException) {
				logHandler.grabar(requestWrapper, e);
				Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("501");
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas(ex.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\""
						+ responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			} else if (e.getCause() instanceof MtaServiceException) {
				logHandler.grabar(requestWrapper, e);
				Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("500");
				MtaServiceException be = ((MtaServiceException) ex);
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas(be.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\""
						+ responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			} else if (e.getCause() instanceof MtaGenericDAOException) {
				logHandler.grabar(requestWrapper, e);
				Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("500");
				MtaGenericDAOException be = ((MtaGenericDAOException) ex);
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas(be.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\""
						+ responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			} else if (e.getCause() instanceof MtaQueryException) {
				logHandler.grabar(requestWrapper, e);
				Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("500");
				MtaQueryException be = ((MtaQueryException) ex);
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas(be.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\""
						+ responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			}  else if (e.getCause() instanceof BadSqlGrammarException) {
				logHandler.grabar(requestWrapper, e);
				Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("500");
				//MtaQueryException be = ((MtaQueryException) ex);
				responseWrapper.setErrorMessage("Sintaxis:" + CaracteresUtil.reemplazaSaltosLinea((e.getCause()).getMessage()).replace("\"", "'"));

				//responseWrapper.setErrorMessage(GeneralUtil.extractQuerys(text)(be.getMessage()));
				
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\""
						+ responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			} else if (e.getCause() instanceof BaseException) {
				logHandler.grabar(requestWrapper, e);
				// Throwable ex = e.getCause();
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("501");
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas(e.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\"" + responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			} else {
				logHandler.grabar(requestWrapper, e);
				ResponseWrapper<String> responseWrapper = new ResponseWrapper<String>();
				responseWrapper.setErrorCode("502");
				responseWrapper.setErrorMessage(GeneralUtil.limpiandoComillas("Error al procesar:" + e.getMessage()));
				response.setContentType("application/json");
				String respuesta = "{\"errorCode\":\"" + responseWrapper.getErrorCode() + "\",\"errorMessage\":\"" + responseWrapper.getErrorMessage() + "\"}";
				response.getWriter().write(respuesta);
			}*/
		}
	}
}
