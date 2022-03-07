package sv.gob.mh.dga.mta.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sv.gob.mh.dga.mta.common.constants.ErrorCodeConstant;
import sv.gob.mh.dga.mta.common.exception.MtaGenericDAOException;
import sv.gob.mh.dga.mta.common.exception.MtaQueryException;
import sv.gob.mh.dga.mta.common.model.DgaEmpleado;
import sv.gob.mh.dga.mta.common.model.DgaPojoFotoEmpleado;
import sv.gob.mh.dga.mta.common.spring.sql.SQLUtil;
import sv.gob.mh.dga.mta.common.sql.Row;
import sv.gob.mh.dga.mta.dao.DgaEmpleadoDao;

@Repository
public class DgaEmpleadoDaoImpl extends GenericDAOImpl implements DgaEmpleadoDao{

	public DgaEmpleadoDaoImpl(JdbcTemplate jdbcTemplate, SQLUtil sqlUtil) {
		super(jdbcTemplate, sqlUtil);
	}
	
	@Override
	public List<DgaEmpleado> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emp.id_empleado, emp.nombre, emp.id_estado, emp.nit, emp.id_departamento, emp.id_municipio, emp.direccion, emp.telefono, ");
		sql.append("emp.fecha_nacimiento,emp.sexo,emp.id_aduana,emp.tipo_empleado,emp.carnet,emp.fecha_ingreso,emp.correo_electronico,emp.id_cargo_nominal, ");
		sql.append("emp.id_cargo_funcional,emp.usuario_sw,emp.usuario_smm,emp.estudiante_activo,emp.emblact_activo,emp.fecha_creacion,emp.fecha_modificacion,emp.usuario_creacion, ");
		sql.append("emp.FECHA_FIN_INACTVO, ");
		sql.append("emp.usuario_modificacion,    cf.desCargoFuncional cargoFuncional,    aduana.cuo_nam aduana,estado.desEstado estado ");
		//sql.append(",emp.foto ");
		sql.append("FROM dga_empleados emp, ");
		sql.append("(select id_cat_detalle idCargoFuncional, descripcion desCargoFuncional from mta_catalogo_detalle where id_catalogo=2) cf, ");
		sql.append("awunadm.uncuotab aduana, ");
		sql.append("(select id_cat_detalle idEstado, descripcion desEstado from mta_catalogo_detalle where id_catalogo=1) estado ");
		sql.append("WHERE 1 = 1 AND emp.id_cargo_funcional = cf.idCargoFuncional AND emp.id_aduana = aduana.cuo_cod AND emp.id_estado = estado.idEstado ");
		sql.append("ORDER BY nombre ");

		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpleado.class));
	}

	@Override
	public Integer obtenerSiguienteId() throws MtaQueryException {
		return sqlUtil.getNextValue("SEQ_DGA_EMPLEADOS", Integer.class);
	}

	@Override
	public DgaEmpleado getEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emp.id_empleado, emp.nombre, emp.id_estado, emp.nit, emp.id_departamento, emp.id_municipio, emp.direccion, emp.telefono, ");
		sql.append("emp.fecha_nacimiento,emp.sexo,emp.id_aduana,emp.tipo_empleado,emp.carnet,emp.fecha_ingreso,emp.correo_electronico,emp.id_cargo_nominal, ");
		sql.append("emp.id_cargo_funcional,emp.usuario_sw,emp.usuario_smm,emp.estudiante_activo,emp.emblact_activo,emp.fecha_creacion,emp.fecha_modificacion,emp.usuario_creacion, ");
		sql.append("emp.FECHA_FIN_INACTVO, ");
		sql.append("emp.usuario_modificacion,    cf.desCargoFuncional cargoFuncional,    aduana.cuo_nam aduana,estado.desEstado estado ");
		sql.append("FROM dga_empleados emp, ");
		sql.append("(select id_cat_detalle idCargoFuncional, descripcion desCargoFuncional from mta_catalogo_detalle where id_catalogo=2) cf, ");
		sql.append("awunadm.uncuotab aduana, ");
		sql.append("(select id_cat_detalle idEstado, descripcion desEstado from mta_catalogo_detalle where id_catalogo=1) estado ");
		sql.append("WHERE 1 = 1 AND emp.id_cargo_funcional = cf.idCargoFuncional AND emp.id_aduana = aduana.cuo_cod AND emp.id_estado = estado.idEstado AND emp.id_empleado = ? ");
		sql.append("ORDER BY nombre ");
		

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idEmpleado}, DgaEmpleado.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public Row guardar(DgaEmpleado empleado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO DGA_EMPLEADOS (ID_EMPLEADO,NOMBRE,ID_ESTADO,NIT,ID_DEPARTAMENTO,ID_MUNICIPIO,DIRECCION,TELEFONO,FECHA_NACIMIENTO,SEXO,ID_ADUANA,FOTO, ");
        sql.append(" TIPO_EMPLEADO,CARNET,FECHA_INGRESO,CORREO_ELECTRONICO,ID_CARGO_NOMINAL,ID_CARGO_FUNCIONAL,USUARIO_SW,USUARIO_SMM,ESTUDIANTE_ACTIVO,");
        sql.append(" EMBLACT_ACTIVO,FECHA_CREACION,USUARIO_CREACION, FECHA_FIN_INACTVO ) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        try {
            jdbcTemplate.update(sql.toString(),
            		empleado.getId_empleado(),
            		empleado.getNombre(),
            		empleado.getIdEstado(),
            		empleado.getNit(),
            		empleado.getIdDepartamento(),
            		empleado.getIdMunicipio(),
            		empleado.getDireccion(),
            		empleado.getTelefono(),
            		empleado.getFechaNacimiento(),
            		empleado.getSexo(),
            		empleado.getIdAduana(),
            		empleado.getFoto(),
            		empleado.getTipoEmpleado(),
            		empleado.getCarnet(),
            		empleado.getFechaIngreso(),
            		empleado.getCorreoElectronico(),
            		empleado.getIdCargoNominal(),
            		empleado.getIdCargoFuncional(),
            		empleado.getUsuarioSw(),
            		empleado.getUsuarioSmm(),
            		empleado.getEstudianteActivo(),
            		empleado.getEmblactActivo(),
            		empleado.getFechaCreacion(),
            		empleado.getUsuarioCreacion(),
            		empleado.getFechaFinInactivo());
            Row row = new Row();
            row.put("id", empleado.getId_empleado());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al guardar Empleado", e);
        }
	}

	@Override
	public Row actualizar(DgaEmpleado empleado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE DGA_EMPLEADOS SET NOMBRE = ?, ID_ESTADO = ?, NIT = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, DIRECCION = ?, TELEFONO = ?, FECHA_NACIMIENTO = ?, SEXO = ?, ID_ADUANA = ?, FOTO = ?, ");
        sql.append(" TIPO_EMPLEADO = ?, CARNET = ?, FECHA_INGRESO = ?, CORREO_ELECTRONICO = ?, ID_CARGO_NOMINAL = ?, ID_CARGO_FUNCIONAL = ?, USUARIO_SW = ?, USUARIO_SMM = ?, ESTUDIANTE_ACTIVO = ?, ");
        sql.append(" EMBLACT_ACTIVO = ?, FECHA_MODIFICACION = ?, USUARIO_MODIFICACION = ? , FECHA_FIN_INACTVO = ? ");
        sql.append(" WHERE ID_EMPLEADO = ? ");
        try {
            jdbcTemplate.update(sql.toString(),
            		empleado.getNombre(),
            		empleado.getIdEstado(),
            		empleado.getNit(),
            		empleado.getIdDepartamento(),
            		empleado.getIdMunicipio(),
            		empleado.getDireccion(),
            		empleado.getTelefono(),
            		empleado.getFechaNacimiento(),
            		empleado.getSexo(),
            		empleado.getIdAduana(),
            		empleado.getFoto(),
            		empleado.getTipoEmpleado(),
            		empleado.getCarnet(),
            		empleado.getFechaIngreso(),
            		empleado.getCorreoElectronico(),
            		empleado.getIdCargoNominal(),
            		empleado.getIdCargoFuncional(),
            		empleado.getUsuarioSw(),
            		empleado.getUsuarioSmm(),
            		empleado.getEstudianteActivo(),
            		empleado.getEmblactActivo(),
            		empleado.getFechaModificacion(),
            		empleado.getUsuarioModificacion(),
            		empleado.getFechaFinInactivo(),
            		empleado.getId_empleado());
            Row row = new Row();
            row.put("id", empleado.getId_empleado());
            return row;
        } catch (DataAccessException e) {
            throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al actualizar Empleado", e);
        }
	}

	@Override
	public DgaEmpleado getEmpleadoByUsuario(String usuario) throws MtaGenericDAOException {
		usuario = usuario.concat("%");
		usuario = usuario.toUpperCase();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emp.id_empleado, emp.nombre, emp.id_estado, emp.nit, emp.id_departamento, emp.id_municipio, emp.direccion, emp.telefono, ");
		sql.append("emp.fecha_nacimiento,emp.sexo,emp.id_aduana,emp.tipo_empleado,emp.carnet,emp.fecha_ingreso,emp.correo_electronico,emp.id_cargo_nominal, ");
		sql.append("emp.id_cargo_funcional,emp.usuario_sw,emp.usuario_smm,emp.estudiante_activo,emp.emblact_activo,emp.fecha_creacion,emp.fecha_modificacion,emp.usuario_creacion, ");
		sql.append("emp.usuario_modificacion,    cf.desCargoFuncional cargoFuncional,    aduana.cuo_nam aduana,estado.desEstado estado ");
		sql.append("FROM dga_empleados emp, ");
		sql.append("(select id_cat_detalle idCargoFuncional, descripcion desCargoFuncional from mta_catalogo_detalle where id_catalogo=2) cf, ");
		sql.append("awunadm.uncuotab aduana, ");
		sql.append("(select id_cat_detalle idEstado, descripcion desEstado from mta_catalogo_detalle where id_catalogo=1) estado ");
		sql.append("WHERE 1 = 1 AND emp.id_cargo_funcional = cf.idCargoFuncional AND emp.id_aduana = aduana.cuo_cod AND emp.id_estado = estado.idEstado AND UPPER(emp.correo_electronico) like ? ");


		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{usuario}, DgaEmpleado.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

	@Override
	public List<DgaEmpleado> getEmpleadosByCargoFuncional(String cargo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emp.id_empleado, emp.nombre, cf.idCargoFuncional, cf.desCargoFuncional cargoFuncional ");
		sql.append("FROM dga_empleados emp, ");
		sql.append("(select id_cat_detalle idCargoFuncional, descripcion desCargoFuncional from mta_catalogo_detalle where id_catalogo=2) cf ");
		sql.append("WHERE 1 = 1 AND emp.id_cargo_funcional = cf.idCargoFuncional ");
		sql.append("AND UPPER(cf.desCargoFuncional) like '%"+cleanText(cargo)+"%' " );
		sql.append("ORDER BY nombre ");


		return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(DgaEmpleado.class));
	}
	
	public String cleanText(String text) {
        return text.toUpperCase().trim();
    }

	@Override
	public DgaPojoFotoEmpleado getFotoEmpleadoById(Integer idEmpleado) throws MtaGenericDAOException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emp.id_empleado, emp.foto ");
		sql.append("FROM dga_empleados emp ");
		sql.append("WHERE emp.id_empleado = ? ");

		try {
			return sqlUtil.queryObject(sql.toString(), new Object[]{idEmpleado}, DgaPojoFotoEmpleado.class);
		} catch (MtaQueryException e) {
			throw new MtaGenericDAOException(ErrorCodeConstant.DAO_00000, "Error al buscar en Empleado", e);
		}
	}

}
