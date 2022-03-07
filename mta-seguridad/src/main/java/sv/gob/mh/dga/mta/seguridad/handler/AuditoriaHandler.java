package sv.gob.mh.dga.mta.seguridad.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sv.gob.mh.dga.mta.common.constants.Constantes;
import sv.gob.mh.dga.mta.common.seguridad.model.AuditoriaConfig;
import sv.gob.mh.dga.mta.common.util.AES;
import sv.gob.mh.dga.mta.seguridad.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuditoriaHandler {

	Logger logger = LoggerFactory.getLogger(AuditoriaHandler.class);

	/*@Autowired
	AuditoriaService auditoriaService;*/

	@Autowired
	ControllerUtil controllerUtil;


	public void auditar(final HttpServletRequest request) throws IOException {
		// Thread t = new Thread(new Runnable() { public void run() { }});

		Object auditado = request.getAttribute("_AUDITADO");

		//printClientInfo(request);

		logger.debug("auditado:" + auditado);

		if (auditado != null)
			return;
		else
			request.setAttribute("_AUDITADO", "true");

		String method = request.getMethod();
		String params = null;
		String url = request.getRequestURI();
		String accion = "";

		logger.debug("url:" + url);
		logger.debug("method:" + method);

		try {
			if (method != null && !method.equals("OPTION")) {
				String contenType = request.getHeader("content-type");
				String sIdFuncionalidad = request.getHeader("Random");
				String tipoDato = null;
				Integer id_funcionalidad = 0;
				AuditoriaConfig auditoriaConfig = null;
				if (sIdFuncionalidad != null && !sIdFuncionalidad.equals("")) {
					try {
						id_funcionalidad = Integer.parseInt(sIdFuncionalidad);
					} catch (Throwable e) {
						logger.error("Audioria Handler: Error id_funcionalidad {}", e.getMessage());
					}
					//auditoriaConfig = auditoriaService.auditoriaConfigByUrl(id_funcionalidad, url, method);
				}

				//No guardar GET de COMMONS y COMPENDIOS
				if (url.contains("common") || url.contains("compendioGeneral") ||
						url.contains("compendioDetalle") || url.contains("compendioDetalleCampoReferencia") || url.contains("sujetoriesgo/porSession") ||
						url.contains("ayuda/combo") || url.contains("tarea/estadoinicial") || url.contains("filtrosMaestro") || url.contains("mgr-cron")) {
					return;
				}

				logger.debug("auditoriaConfig:" + auditoriaConfig);

				if (contenType != null && contenType.indexOf("application/json") > -1) {
					tipoDato = "J";
					params = getJson(request);
				} else {
					params = getParams(request);
					tipoDato = "P";
				}

				logger.debug("params:" + params);

				//En caso que no tenga auditoriaConfig, se arma la accion en base a la url
				String[] args = url.split("/");
				String ultimaAccion = "";
				for (String ar : args) {
					ultimaAccion = ar;
				}
				accion = getAccion(method) + args[3] + " " + ultimaAccion + "(" + url + ")";

				//if (params != null)
					//params = AES.encrypt(params, Constantes.SECRET_AUDITORIA);

				/*try {
					//En caso no tenga un auditoriaConfig se colocará el de acciones de usuario
					if (id_funcionalidad == 0) {
						id_funcionalidad = auditoriaService.getFuncionalidadByName("%Acciones del Usuario%");
					}

					auditoriaService.grabar(controllerUtil.getSessionId(),
							auditoriaConfig != null ? auditoriaConfig.getId_auditoria_config() : null,
							params,
							auditoriaConfig != null ? null : accion,
							tipoDato,
							auditoriaConfig != null ? auditoriaConfig.getId_funcionalidad() : id_funcionalidad);

				} catch (MtaServiceException e) {
					logger.error("GrabarAuditoria", e);
				}*/
			}
		} catch (Throwable tx) {
			logger.error("Error en AuditoriaHandler", tx);
		}

	}

	private String getAccion(String method) {
		switch (method){
			case "GET":
				return "Consulta de ";
			case "POST":
				return "Ejecutar/Crear un/a ";
			case "PUT":
				return "Actualizar un/a ";
			case "DELETE":
				return "Eliminar un/a ";
			default:
				return "Consulta de ";
		}
	}

	private String getParams(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();

		String str = null;

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();

			if (str == null)
				str = "{\"" + paramName + "\":";
			else
				str = str + ",{\"" + paramName + "\":";

			String[] paramValues = request.getParameterValues(paramName);

			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];

				str = str + "\"" + paramValue + "\"";
			}

			str = str + "}";
		}

		if(str==null)
			return null;
		else
			return "[" + str + "]";
	}

	private String getJson(HttpServletRequest request) {
		String str = null;

		try {
			// BufferedReader reader = request.getReader();
			// String line = null;

			// while ((line = reader.readLine()) != null)
			// str = str + line;
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
			logger.error("errorjson", e);
		}

		return str;
	}

	


	public String getFullURL(HttpServletRequest request) {
		final StringBuffer requestURL = request.getRequestURL();
		final String queryString = request.getQueryString();

		final String result = queryString == null ? requestURL.toString()
				: requestURL.append('?').append(queryString).toString();

		return result;
	}

	
}
