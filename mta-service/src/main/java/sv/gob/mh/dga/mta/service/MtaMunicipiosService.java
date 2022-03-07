package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaMunicipios;

public interface MtaMunicipiosService {

	public MtaMunicipios getMunicipioByFilter(MtaMunicipios municipio);
	
	public List<MtaMunicipios> getAll(Integer idDepartamento);
}
