package sv.gob.mh.dga.mta.dao;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaCargoNominal;

public interface MtaCargoNominalDao {

	public MtaCargoNominal getCargoNominalByFilter(MtaCargoNominal cargoNominal);
	
	public List<MtaCargoNominal> getAll();
	
}
