package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.model.MtaDepartamentos;
import sv.gob.mh.dga.mta.dao.MtaDepartamentosDao;
import sv.gob.mh.dga.mta.service.MtaDepartamentosService;

@Service
public class MtaDepartamentosServiceImpl implements MtaDepartamentosService{

	@Autowired
	private MtaDepartamentosDao departamentoDao;
	
	@Override
	public MtaDepartamentos getDepartamentoByFilter(MtaDepartamentos departamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MtaDepartamentos> getAll() {
		return departamentoDao.getAll();
	}

}
