package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.model.MtaMunicipios;
import sv.gob.mh.dga.mta.dao.MtaMunicipiosDao;
import sv.gob.mh.dga.mta.service.MtaMunicipiosService;

@Service
public class MtaMunicipiosServiceImpl implements MtaMunicipiosService{

	@Autowired
	private MtaMunicipiosDao municipiosDao;
	
	@Override
	public MtaMunicipios getMunicipioByFilter(MtaMunicipios municipio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaMunicipios> getAll(Integer idDepartamento) {
		return municipiosDao.getAll(idDepartamento);
	}

}
