package sv.gob.mh.dga.mta.service;

import java.util.List;

import sv.gob.mh.dga.mta.common.model.MtaCargoNominal;

public interface MtaCargoNominalService {

	public MtaCargoNominal getCargoNominalByFilter(MtaCargoNominal cargoNominal);
	
	public List<MtaCargoNominal> getAll();
}
