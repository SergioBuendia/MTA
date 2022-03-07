package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaMunicipios;


public interface MtaMunicipiosDao {

	public MtaMunicipios getMunicipioByFilter(MtaMunicipios municipio);
	
	public List<MtaMunicipios> getAll(Integer idDepartamento);
}
