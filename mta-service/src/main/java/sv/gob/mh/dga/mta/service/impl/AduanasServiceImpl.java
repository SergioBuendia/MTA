package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.gob.mh.dga.mta.common.sw.model.Aduanas;
import sv.gob.mh.dga.mta.dao.AduanasDao;
import sv.gob.mh.dga.mta.service.AduanasService;

@Service
public class AduanasServiceImpl implements AduanasService{

	@Autowired
	private AduanasDao aduanasDao;
	
	@Override
	public List<Aduanas> getAll() {
		return aduanasDao.getAll();
	}

}
