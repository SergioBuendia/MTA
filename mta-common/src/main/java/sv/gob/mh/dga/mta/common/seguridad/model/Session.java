package sv.gob.mh.dga.mta.common.seguridad.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


public class Session extends EntidadBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String TABLA = "grta_session";

    /**
     * inicio - campos de la base de datos
     */
    private Integer id_session;
    private String codigo_usuario;
    private String direccion_ip;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fecha_registro;
    private String token;
    private Date fecha_finalizacion;
    /**
     * fin - campos de la base de datos
     */

    public Session() {
    }

    /**
     * Obtiene Id
     *
     * @return id_session
     */
    public Integer getId_session() {
        return id_session;
    }

    /**
     * Id
     *
     * @param id_session
     */
    public void setId_session(Integer id_session) {
        this.id_session = id_session;
    }
    
    /**
     * Obtiene Identificaci&oacute;n del Cod. de usuario.
     *
     * @return codigo_usuario
     */
    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    /**
     * Identificaci&oacute;n del Cod. de usuario.
     *
     * @param codigo_usuario
     */
    public void setCodigo_usuario(String codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }
    
    /**
     * Obtiene Direcci&oacute;n IP de la m&aacute;quina del usuario.
     *
     * @return direccion_ip
     */
    public String getDireccion_ip() {
        return direccion_ip;
    }

    /**
     * Direcci&oacute;n IP de la m&aacute;quina del usuario.
     *
     * @param direccion_ip
     */
    public void setDireccion_ip(String direccion_ip) {
        this.direccion_ip = direccion_ip;
    }
    
    /**
     * Obtiene Fecha de ingreso del usuario al MGR.
     *
     * @return fecha_registro
     */
    public Date getFecha_registro() {
        return fecha_registro;
    }

    /**
     * Fecha de ingreso del usuario al MGR.
     *
     * @param fecha_registro
     */
    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    /**
     * Obtiene token
     * 
     * @return token
     */
	public String getToken() {
		return token;
	}

	/**
	 * Setea token
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Session [id_session=" + id_session + ", codigo_usuario=" + codigo_usuario + ", direccion_ip="
				+ direccion_ip + ", fecha_registro=" + fecha_registro + ", token=" + token + "]";
	}

	/**
	 * Obtiene la fecha de finalizacion de la session
	 * @return Date
	 */
	public Date getFecha_finalizacion() {
		return fecha_finalizacion;
	}

	/**
	 * Setea Fecha_finalizacion
	 * @param fecha_finalizacion
	 */
	public void setFecha_finalizacion(Date fecha_finalizacion) {
		this.fecha_finalizacion = fecha_finalizacion;
	}

	
	
}