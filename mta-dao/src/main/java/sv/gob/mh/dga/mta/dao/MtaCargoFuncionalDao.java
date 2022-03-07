package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaCargoFuncional;


public interface MtaCargoFuncionalDao {

	public MtaCargoFuncional getCargoFuncionalByFilter(MtaCargoFuncional cargoFuncional);
	
	public List<MtaCargoFuncional> getAll();
		
}
