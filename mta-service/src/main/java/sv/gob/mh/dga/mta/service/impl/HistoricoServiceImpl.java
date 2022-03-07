package sv.gob.mh.dga.mta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.mh.dga.mta.common.model.AudDgaEmpAcciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEmblact;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEstudios;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpleado;
import sv.gob.mh.dga.mta.dao.HistoricoDao;
import sv.gob.mh.dga.mta.service.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService{
	
	@Autowired
	private HistoricoDao historicoDao;

	@Override
	public List<AudDgaEmpleado> getAllByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAllByIdEmpleado(idEmpleado);
	}

	@Override
	public List<AudDgaEmpAcciones> getAudEmpAccionByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAudEmpAccionByIdEmpleado(idEmpleado);
	}

	@Override
	public List<AudDgaEmpEmblact> getAudEmpEmbalactByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAudEmpEmbalactByIdEmpleado(idEmpleado);
	}

	@Override
	public List<AudDgaEmpEstudios> getAudEmpEstudioByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAudEmpEstudioByIdEmpleado(idEmpleado);
	}

	@Override
	public List<AudDgaEmpIncapacidad> getAudEmpIncapacidadByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAudEmpIncapacidadByIdEmpleado(idEmpleado);
	}

	@Override
	public List<AudDgaEmpVacaciones> getAudEmpVacacionByIdEmpleado(Integer idEmpleado) {
		return historicoDao.getAudEmpVacacionByIdEmpleado(idEmpleado);
	}

}
