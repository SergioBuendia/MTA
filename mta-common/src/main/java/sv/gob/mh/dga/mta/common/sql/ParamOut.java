package sv.gob.mh.dga.mta.common.sql;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ParamOut extends HashMap<String, Integer> {


    private static final long serialVersionUID = -1567381564811151853L;
    private Map<String, Integer> rowValue = new HashMap<String, Integer>();

    public ParamOut() {
        super();
    }

    public ParamOut(HashMap<? extends String, ? extends Integer> t) {
        super(t);
    }

    public static ParamOut toParam(String alias, Integer object) {
        Field[] fields = object.getClass().getDeclaredFields();
        ParamOut param = new ParamOut();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                String name = fields[i].getName();
                Integer value = (Integer)fields[i].get(object);
                if (value != null)
                    param.put(alias + "." + name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    public ParamOut(String key, Integer value) {
        super.put(key, value);
        this.rowValue.put(key, value);
    }

    public Integer get(String key) {
        return this.rowValue.get(key);
    }

    public void set(Map<String, Integer> map) {
        this.rowValue = map;
    }

    @Override
    public Integer put(String key, Integer value) {
        super.put(key, value);
       return this.rowValue.put(key, value);
    }

}
