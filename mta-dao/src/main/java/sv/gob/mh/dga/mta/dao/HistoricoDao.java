package sv.gob.mh.dga.mta.dao;

import java.util.List;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpAcciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEmblact;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpEstudios;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpIncapacidad;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpVacaciones;
import sv.gob.mh.dga.mta.common.model.AudDgaEmpleado;


public interface HistoricoDao {

	public List<AudDgaEmpleado> getAllByIdEmpleado(Integer idEmpleado);
	
	public List<AudDgaEmpAcciones> getAudEmpAccionByIdEmpleado(Integer idEmpleado);
	
	public List<AudDgaEmpEmblact> getAudEmpEmbalactByIdEmpleado(Integer idEmpleado);
	
	public List<AudDgaEmpEstudios> getAudEmpEstudioByIdEmpleado(Integer idEmpleado);
	
	public List<AudDgaEmpIncapacidad> getAudEmpIncapacidadByIdEmpleado(Integer idEmpleado);
	
	public List<AudDgaEmpVacaciones> getAudEmpVacacionByIdEmpleado(Integer idEmpleado);
	
}
