package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaDepartamentos;

public interface MtaDepartamentosService {

	public MtaDepartamentos getDepartamentoByFilter(MtaDepartamentos departamento);
	
	public List<MtaDepartamentos> getAll();
}
