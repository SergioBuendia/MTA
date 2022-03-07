package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.model.MtaCargoNominal;
import sv.gob.mh.dga.mta.dao.MtaCargoNominalDao;
import sv.gob.mh.dga.mta.service.MtaCargoNominalService;

@Service
public class MtaCargoNominalServiceImpl implements MtaCargoNominalService{

	@Autowired
	private MtaCargoNominalDao cargoNominalDao;
	
	@Override
	public MtaCargoNominal getCargoNominalByFilter(MtaCargoNominal cargoNominal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaCargoNominal> getAll() {
		return cargoNominalDao.getAll();
	}

}
