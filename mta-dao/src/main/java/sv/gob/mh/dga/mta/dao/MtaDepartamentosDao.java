package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaDepartamentos;


public interface MtaDepartamentosDao {

	public MtaDepartamentos getDepartamentoByFilter(MtaDepartamentos departamento);
	
	public List<MtaDepartamentos> getAll();
}
