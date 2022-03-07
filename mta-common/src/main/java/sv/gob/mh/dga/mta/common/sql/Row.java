package sv.gob.mh.dga.mta.common.sql;

import java.util.Date;
import java.util.HashMap;

import sv.gob.mh.dga.mta.common.util.DateUtil;

public class Row extends HashMap<String, Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Row() {
        super();
    }

    public Row(HashMap<? extends String, ? extends Object> t) {
        super(t);
    }

    public Row(String key, Object value) {
        this.put(key, value);
    }

    public Object get(String key) {
        return super.get(key);
    }

    public String getString(String key) {
        Object valor = this.get(key);
        if (valor == null)
            return null;
        if (valor instanceof Date)
            return DateUtil.toString((Date) valor);
        else
            return valor.toString();
    }

    public Date getDate(String key) {
        if (this.get(key) != null) {
            return (Date) (this.get(key));
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
        return (byte[]) (this.get(key));
    }

    public boolean getBoolean(String key) {
        int valor = Integer.parseInt(this.get(key).toString());
        return valor == 1;
    }

    public <T> T getValue(String key, Class<T> clazz) {
        return clazz.cast(this.get(key));
    }
}
