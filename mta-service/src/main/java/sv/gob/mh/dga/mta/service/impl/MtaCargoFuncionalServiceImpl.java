package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.model.MtaCargoFuncional;
import sv.gob.mh.dga.mta.dao.MtaCargoFuncionalDao;
import sv.gob.mh.dga.mta.service.MtaCargoFuncionalService;

@Service
public class MtaCargoFuncionalServiceImpl implements MtaCargoFuncionalService{

	@Autowired
	private MtaCargoFuncionalDao cargoFuncionalDao;
	
	@Override
	public MtaCargoFuncional getCargoFuncionalByFilter(MtaCargoFuncional cargoFuncional) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaCargoFuncional> getAll() {
		return cargoFuncionalDao.getAll();
	}

}
