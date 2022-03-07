package sv.gob.mh.dga.mta.common.request;

import java.io.Serializable;

public class PostRq implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8297395333058035286L;

	private String alias; 
	private String authorization;
	private PostParamRq[] params;
	
	 

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	
	public PostParamRq[] getParams() {
		return params;
	}

	public void setParams(PostParamRq[] params) {
		this.params = params;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	

}
