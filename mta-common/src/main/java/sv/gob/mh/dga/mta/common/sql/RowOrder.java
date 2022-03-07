package sv.gob.mh.dga.mta.common.sql;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import sv.gob.mh.dga.mta.common.util.DateUtil;

public class RowOrder extends LinkedHashMap<String, Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -552101504068031341L;

	public RowOrder() {
        super();
    }

    public RowOrder(HashMap<? extends String, ? extends Object> t) {
        super(t);
    }


    public RowOrder(String key, Object value) {
        this.put(key, value);
    }

    public Object get(String key) {
        return super.get(key);
    }

    public String getString(String key) {
       	Object valor = this.get(key);
       	if (valor==null)
       	return null;
       	if (valor instanceof Date){
       	return DateUtil.toString((Date)valor);
       	}else
       	return valor.toString();
    }
    
    public Date getDate(String key) {
       	if (this.get(key) != null) {
       	return (Date)(this.get(key));
       	} else {
       	return null;
       	}
    }
    
    public Integer getInteger(String key) {
    	if (this.get(key) != null) {
    		return Integer.parseInt(this.get(key).toString());
    	} else {
    		return null;
    	}
    }
    
    public byte[] getArrayByte(String key) {
        return (byte[])(this.get(key)) ;
    }
    
    public boolean getBoolean(String key) {
    	int valor = Integer.parseInt(this.get(key).toString());
    	
        return valor == 1;
    }
    
}
