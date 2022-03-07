package sv.gob.mh.dga.mta.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaServiceException;
import sv.gob.mh.dga.mta.common.model.MtaUsuarios;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.MtaUsuariosDao;
import sv.gob.mh.dga.mta.service.MtaUsuariosService;

@Service
public class MtaUsuariosServiceImpl implements MtaUsuariosService{

	@Autowired
	private MtaUsuariosDao usuarioDao;
	
	@Override
	@Transactional(rollbackOn = {Exception.class, RuntimeException.class})
	public Row guardar(MtaUsuarios usuario) throws MtaGenericDAOException, MtaServiceException {
		try{
			usuario.setIdUsuario(usuarioDao.obtenerSiguienteId());
			usuario.setFechaCreacion(new Date());
			Row row = usuarioDao.guardar(usuario);
			return row;
		}catch (MtaGenericDAOException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, e.getMessage(), e);
        } catch (Exception e) {
            throw new MtaServiceException(ErrorCodeConstant.SERV_ERROR, "Error al guardar el formulario y sus vigencias", e);
        }		
	}
		
	@Override
	public MtaUsuarios getUsuarioByUser(String usuario) throws MtaGenericDAOException {
		return usuarioDao.getUsuarioByUser(usuario);
	}

	@Override
	public List<MtaUsuarios> getAll() {
		return usuarioDao.getAll();
	}

}
