package sv.gob.mh.dga.mta.common.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLFrmkUtil {

    public SQLFrmkUtil() {
        // TODO Auto-generated constructor stub
    }

    public static List<Row> listToRows(List<Map<String, Object>> list) {
        List<Row> rows = new ArrayList<Row>();

        for (Map<String, Object> map : list) {
            Row row = new Row();

            for (String key : map.keySet()) {
                //Logge("key:" + key + "-value" + map.get(key));
                row.put(key.toLowerCase(), map.get(key));
            }

            rows.add(row);
        }
        
        return rows;
    }
    
    public static List<Row> listToRowsNormal(List<Map<String, Object>> list) {
        List<Row> rows = new ArrayList<Row>();

        for (Map<String, Object> map : list) {
            Row row = new Row();

            for (String key : map.keySet()) {
                //Logge("key:" + key + "-value" + map.get(key));
                row.put(key, map.get(key));
            }

            rows.add(row);
        }
        
        return rows;
    }
    
    public static List<RowOrder> listToRows(List<Map<String, Object>> list, boolean lowerCase, boolean empty) {
        List<RowOrder> rows = new ArrayList<RowOrder>();

        for (Map<String, Object> map : list) {
        	RowOrder row = new RowOrder();

            for (String key : map.keySet()) {
                row.put(lowerCase ? key.toLowerCase() : key, !empty ? map.get(key) : (map.get(key) == null ? "" : map.get(key)));
            }

            rows.add(row);
        }
        
        return rows;
    }
    
    public static Row objectToRow(Map<String, Object> mapObject) {
    	Row row = new Row();
    	
        for (String key : mapObject.keySet()) {
            //Logge("key:" + key + "-value" + map.get(key));
            row.put(key.toLowerCase(), mapObject.get(key));
        }
        
        return row;
    }

    public static String getWhere(Param param) {
        int i = 0;

        String sql = "";
        
        if (param == null)
            return "";

        for (String key : param.keySet()) {
            Object valuekey = param.get(key);
            
            if (valuekey != null && !"".equals(valuekey.toString()) && !"%%".equals(valuekey.toString())) {
                String value = valuekey.toString();
                
                if (i == 0) {
                    if (value.indexOf("%") == -1)
                        sql = sql + " where " + key + "='" + value + "'";
                    else
                        sql = sql + " where lower(" + key + ") like '" + value.toLowerCase() + "'";
                } else {
                    if (value.indexOf("%") == -1)
                        sql = sql + " and " + key + "='" + value + "'";
                    else
                        sql = sql + " and lower(" + key + ") like '" + value.toLowerCase() + "'";
                }

                i++;
            }
        }

        return sql;
    }

    public static String getOrder(String[] order) {
        int i = 0;
        
        String sql = "";
        
        if (order != null) {
            for (String string : order) {
                if (i == 0)
                    sql = sql + " order by " + string;
                else
                    sql = sql + " , " + string;
                i++;
            }
        }
        
        return sql;
    }

}
