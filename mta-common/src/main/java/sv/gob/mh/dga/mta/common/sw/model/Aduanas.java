package sv.gob.mh.dga.mta.common.sw.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aduanas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cuoCod;

	private java.util.Date validFrom;

	//private String bnkCod;

	//private String braCod;

	//private String cuoAcc;

	private String cuoAd2;

	private String cuoAd3;

	private String cuoAd4;

	private String cuoAdr;

	//private String cuoBrd;

	//private String cuoCap;

	//private String cuoClr;

	//private String cuoFax;

	private String cuoNam;

	//private String cuoNam2;

	//private String cuoNam3;

	//private String cuoTel;

	//private String cuoTlx;

	private Timestamp validTo;
	
}
