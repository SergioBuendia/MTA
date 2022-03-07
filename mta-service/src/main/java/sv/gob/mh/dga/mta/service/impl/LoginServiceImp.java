package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.MtaUsuarios;
import sv.gob.mh.dga.mta.common.request.PostParamRq;
import sv.gob.mh.dga.mta.common.request.PostRq;
import sv.gob.mh.dga.mta.common.seguridad.model.MtaLoginBean;
import sv.gob.mh.dga.mta.dao.DgaEmpleadoDao;
import sv.gob.mh.dga.mta.dao.MtaUsuariosDao;
import sv.gob.mh.dga.mta.service.LoginService;

@Service
public class LoginServiceImp implements LoginService{
	
	
	@Autowired
	private MtaUsuariosDao usuarioDao;
	
	@Autowired
	private RestUtil restUtil;
	
	@Autowired
	private DgaEmpleadoDao dgaEmpleadoDao;
	
	Logger logger = LoggerFactory.getLogger(LoginServiceImp.class);

	@Override
	public MtaLoginBean login(String usuario, String clave, String token) throws MtaServiceException {
		MtaLoginBean ret = new MtaLoginBean();
		Object result = null;		
		try {
			
			PostRq paramPost = new PostRq();
			paramPost.setAlias("REST_OID");
			PostParamRq[] postParam = new PostParamRq[2];
			postParam[0] = new PostParamRq("user", usuario);
			postParam[1] = new PostParamRq("password", clave);
			
			paramPost.setParams(postParam);
			
			result = restUtil.requestPOST(paramPost);

			if (result == null)
				logger.error(usuario + " no existe en oid");

			if (result instanceof JSONObject) {
				JSONObject json = (JSONObject) result;
					String error = tryToGet(json, "error");
					String roles = tryTArray(json, "roles");

					if (error != null && !error.equals("null"))
						throw new MtaServiceException(ErrorCodeConstant.SERV_VALIDACION,
								"Error en la autenticacion:" + error);

					if (roles == null || roles.equals("") || roles.equals("null"))
						throw new MtaServiceException(ErrorCodeConstant.SERV_VALIDACION,
								"El usuario no tiene roles asignados");
					
					//Si el usuario es primera vez que se loguea
					MtaUsuarios usuarioDB = usuarioDao.getUsuarioByUser(usuario);
					if(usuarioDB==null) {
						usuarioDB = new MtaUsuarios();
						usuarioDB.setIdUsuario(usuarioDao.obtenerSiguienteId());
						usuarioDB.setUsuario(usuario);
						usuarioDB.setRol(roles);
						usuarioDB.setFechaCreacion(new Date());
						usuarioDao.guardar(usuarioDB);						
						//Agregar metodo para auditoria						
					}
					DgaEmpleado emp = dgaEmpleadoDao.getEmpleadoByUsuario(usuario);
					ret.setIntIdUsuario(usuarioDB.getIdUsuario());
					ret.setStrUsuario(usuario);
					ret.setToken(token);
					if(emp!=null) {
						ret.setCargoFuncional(emp.getCargoFuncional());
						ret.setCarnet(emp.getCarnet());
						ret.setNombre(emp.getNombre());
						ret.setRol(roles);
					}					
			}
			
		}catch (Exception e) {
			logger.error("error", e);
			throw new MtaServiceException(ErrorCodeConstant.ERROR_NET_REST,
					"No hemos podido comunicarnos con el servidor  intentalo nuevamete", e);
		}
		
		return ret;
	}
	
	public static String tryToGet(JSONObject jsonObj, String key) throws JSONException {
		try {

			return jsonObj.getString(key);
		} catch (JSONException e) {
			return null;
		}
	}
	
	public static String tryTArray(JSONObject jsonObj, String key) throws JSONException {
		try {

			return jsonObj.getJSONArray(key).toString();
		} catch (JSONException e) {
			return null;
		}
	}

}
