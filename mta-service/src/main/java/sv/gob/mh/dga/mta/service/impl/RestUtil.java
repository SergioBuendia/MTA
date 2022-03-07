package sv.gob.mh.dga.mta.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaCatalogoDetalle;
import sv.gob.mh.dga.mta.common.request.PostParamRq;
import sv.gob.mh.dga.mta.common.request.PostRq;
import sv.gob.mh.dga.mta.dao.MtaCatalogoDetalleDao;

@Component
public class RestUtil {

	Logger logger = LoggerFactory.getLogger(RestUtil.class);
	
	@Autowired
	private MtaCatalogoDetalleDao catalogoDetalle;
	
	public Object requestPOST(PostRq postRq) throws MtaServiceException {

		Object json = null;
		try {

			MtaCatalogoDetalle urlParam = catalogoDetalle.getCatalogoDetalleByDescripcion(6, "URL_LOGIN");
			if(urlParam!=null) {
				
			}else {
				throw new MtaServiceException("El servicin OID no pudo ser resuelto");
			}


			URL url = new URL(urlParam.getValor());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			conn.setDoOutput(true);
			conn.setDoInput(true);
			String requestProperty = "application/json; charset=UTF-8";
			if (requestProperty != null)
				conn.setRequestProperty("Accept", requestProperty);

			// parametros form
			if (postRq.getParams() != null) {
				OutputStream os = conn.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
				writer.write(getPostDataString(postRq.getParams()));

				writer.flush();
				writer.close();
				os.close();
			}

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			String rpta = "";
			while ((output = br.readLine()) != null) {

				if (output != null)
					rpta += output;
			}

			if (requestProperty != null)
				json = new JSONObject(rpta);

			/*
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader((conn.getInputStream())));
			 * 
			 * 
			 * JsonObject obj = new JsonParser().parse(br).getAsJsonObject();
			 */

			conn.disconnect();

		} catch (Exception e) {
			logger.error("error", e);
			throw new MtaServiceException(ErrorCodeConstant.ERROR_NET_REST,
					"No hemos podido comunicarnos con el servidor  intentalo nuevamete", e);
		}

		return json;

	}
	
	private String getPostDataString(PostParamRq[] params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (PostParamRq postParamRq : params) {
			if (postParamRq.getValor() != null) {

				if (first)
					first = false;
				else
					result.append("&");

				result.append(URLEncoder.encode(postParamRq.getNombre(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(postParamRq.getValor(), "UTF-8"));

			}
		}

		return result.toString();
	}
	
	
}
