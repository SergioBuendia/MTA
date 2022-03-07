package sv.gob.mh.dga.mta.seguridad.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sv.gob.mh.dga.mta.common.seguridad.model.LogParametroDto;
import sv.gob.mh.dga.mta.seguridad.util.ControllerUtil;

@Component
public class LogHandler {

	Logger logger = LoggerFactory.getLogger(LogHandler.class);

	/*@Autowired
	private LogService logService;*/

	@Autowired
	ControllerUtil controllerUtil;

	public void grabar(final HttpServletRequest request, Throwable trowable) {
		String method = request.getMethod();
		String url = request.getRequestURI();
		String queryParams = request.getQueryString();

		logger.error("url:" + url);
		logger.error("method:" + method);

		if (queryParams != null && !queryParams.isEmpty())
			if (url != null && !url.isEmpty())
				url = url + "?" + queryParams;

		if (method != null && !method.equals("OPTION")) {
			String contenType = request.getHeader("content-type");
			try {
				Integer session = 1;
				try {
					session = controllerUtil.getSessionId();
				} catch (Exception e) {
					// TODO QUITARLO LUEGO DE LAS PRUEBAS
				}
				logger.error("contenType:" + contenType);

				if (contenType != null && contenType.indexOf("application/json") > -1) {
					String json = getJson(request);
					//logService.grabar(1, url, session, trowable, json);

				} else {
					List<LogParametroDto> params = getParams(request);
					//logService.grabar(1, url, session, trowable, params, method);
				}
			} catch (Throwable e) {
				logger.error("GrabarLOg", e);
			}
		}
	}

	private List<LogParametroDto> getParams(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		List<LogParametroDto> params = new ArrayList<LogParametroDto>();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			String values = "";
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				if (values.length() > 0)
					values += ",";
				values = values + paramValue;
			}
			LogParametroDto param = new LogParametroDto(paramName, values);
			params.add(param);
		}
		return params;
	}
	
	private String getJson(HttpServletRequest request) {
		String str = null;
		
		try {
			//BufferedReader reader = request.getReader();
			//String line = null;

			//while ((line = reader.readLine()) != null)
			//	str = str + line;
			InputStream inputStream = request.getInputStream();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (str == null)
					str = line;
				else
					str = str + line;
			}
		} catch (Exception e) {
			logger.error("errorjson",e);
		}
		
		return str;
	}

}
