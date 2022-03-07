package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaCargoFuncional;

public interface MtaCargoFuncionalService {

public MtaCargoFuncional getCargoFuncionalByFilter(MtaCargoFuncional cargoFuncional);
	
	public List<MtaCargoFuncional> getAll();
}
