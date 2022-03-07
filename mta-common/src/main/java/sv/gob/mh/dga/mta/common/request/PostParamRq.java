package sv.gob.mh.dga.mta.common.request;

import java.io.Serializable;

public class PostParamRq implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8297395333058035286L;

	private String nombre;
	private String valor;
	
	
	
	public PostParamRq(String nombre, String valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	 
	

}
