package sv.gob.mh.dga.mta.common.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;

public class Sql {

	public static String BD;
	
	public static void setMyConfig(String _BD) {

		BD = _BD;	        
	}
	 
	public enum MOTORES_BD {
		
		ORACLE("ORA"),
		MICROSOFT_SQL_SERVER("MSSQL"),
		MYSQL("MYSQL"),
		POSTGRESQL("POSTGRESQL");
		
		private String nombre;
		
		MOTORES_BD(String nombre) {
			this.nombre = nombre;
		}

		public String getNombre() {
			return nombre;
		}
		
	}
	
	/**
	 * Propietario
	 * 
	 * @return String
	 */
	public static String propietario() {
		String strPripietario = "";
		
		if (BD.equals("MSSQL")) {
			strPripietario = "dbo.";
		}
		
		return strPripietario;
	}
	
	/**
	 * Concat
	 * 
	 * @param parameters
	 * @return String
	 */
	public static String concat(String... parameters){
		String strFinal = "";
		
		//concatenador ORACLE
		if (BD.equals("ORA")) {
			for (String string : parameters) {
				strFinal = strFinal + " || " + string;
			}
			
			strFinal = strFinal.substring(3);
		} else if (BD.equals("MSSQL")) {
			for (String string : parameters) {
				strFinal = strFinal + " + " + string;
			}
			
			strFinal = strFinal.substring(3);
		}
		
		return strFinal;
	}
	
	public static void main(String[] args){
		
		BD = "MSSQL";
		String identificadorPrincipal = "CAMPO";
		identificadorPrincipal = Sql.concat(identificadorPrincipal, "'@'", "");
		
	 }
	/**
	 * Concat
	 * 
	 * @param parameters
	 * @return String
	 */
	public static String operadorConcat(){
		String strFinal = "";
		
		//concatenador ORACLE
		if (BD.equals("ORA")) {
			strFinal = "||";
		} else if (BD.equals("MSSQL")) {
			strFinal = "+";
		}
		
		return strFinal;
	}

	/**
	 * SUBSTR
	 * 
	 * @param parameter
	 * @param star
	 * @param length
	 * @return String
	 */
	public static String SUBSTR(String parameter,int star,int length ){
		String strFinal = "";
		
		//concatenador ORACLE
		if (BD.equals("ORA")) {
				strFinal = "SUBSTR("+parameter+","+star+","+ length+")";
		} else if (BD.equals("MSSQL")) {
			strFinal = "SUBSTRING("+parameter+","+star+","+ length+")";
		}
		
		return strFinal;
	}
	
	/**
	 * Limit Compare
	 * 
	 * @param number
	 * @return String
	 */
	public static String limit_compare(Integer number, boolean esInicio){
		String strLimit ="";
		
		if (BD.equals("ORA")) {
			if (esInicio) {
				strLimit = "";
			} else {
				strLimit = "ROWNUM<=" + number;
			}
		} else if (BD.equals("MSSQL")) {
			if (esInicio) {
				strLimit = "TOP " + number + " ";
			} else {
				strLimit = "1=1";
			}
		}
		
		return strLimit;
	}
	
	/**
	 * Row Num
	 * 
	 * @return String
	 */
	public static String rownum(){
		String strLimit ="";
		
		if (BD.equals("ORA")) {
			strLimit = "ROWNUM";
		}
		
		return strLimit;
	}
	
	/**
	 * rownum
	 * 
	 * @param campo
	 * @param alias
	 * @return String
	 */
	public static String rownum(String campo, String alias){
		String strLimit ="";
		
		if (BD.equals("ORA")) {
			strLimit = "ROWNUM " + alias;
		} else if (BD.equals("MSSQL")) {
			strLimit = "ROW_NUMBER() OVER (Order by " + campo + ") " + alias;
		}
		
		return strLimit;
	}
	
	/**
	 * Get Auto Increment
	 * 
	 * @param campo
	 * @return String
	 */
	public static String getAutoincrement(String campo){
		String strIdNext = "";
		
		if (BD.equals("ORA")) {
			strIdNext =  campo + ".nextval";
		} else if(BD.equals("MSSQL")) {
			strIdNext = "NEXT VALUE FOR " + campo;//TODO MEJORAR	
		}
		return strIdNext;
	}
	
	/**
	 * Get Auto Increment
	 * 
	 * @param campo
	 * @return String
	 */
	public static String getCurrentValue(String campo){
		String strIdNext = "";
		
		if (BD.equals("ORA")) {
			strIdNext =  "select " + campo + ".CURRVAL cur_val from dual";
		} else if(BD.equals("MSSQL")) {
			strIdNext = "SELECT  CONVERT( INT ,current_value) cur_val FROM sys.sequences WHERE name = '"+ campo + "'"; 
		}
		return strIdNext;
	}
	
	
	/**
	 * Get Current Date
	 * 
	 * @return String
	 */
	public static String getNVL(){
		String strIdNext = "";
		
		if (BD.equals("ORA")){
			strIdNext = "NVL";
		} else if(BD.equals("MSSQL")){
			strIdNext = "ISNULL";	
		}
		
		return strIdNext;
	}
	
	/**
	 * Get Current Date
	 * 
	 * @return String
	 */
	public static String getCurrentDate(){
		String strIdNext = "";
		
		if (BD.equals("ORA")){
			strIdNext = "SYSDATE";
		} else if(BD.equals("MSSQL")){
			strIdNext = "GETDATE()";	
		}
		
		return strIdNext;
	}
	
	/**
	 * Get Convert To Number
	 * 
	 * @return String
	 */
	public static String getConvertToNumber(String valor){
		String convert = "";
		
		if (BD.equals("ORA")){
			convert = "TO_NUMBER(" + valor + ")";
		} else if(BD.equals("MSSQL")){
			convert = "CONVERT(NUMERIC," + valor + ")";	
		}
		
		return convert;
	}
	
	/**
	 * StringDate
	 * 
	 * @param parameters
	 * @return String
	 * @return ORA - 'MM/DD/YYYY HH24:MI'
	 * @return SQL - 120
	 */
	public static String StringDate(String parameters){
		String strFinal = "";
		
		//concatenador ORACLE
		if (BD.equals("ORA")) {
			strFinal = "TO_CHAR("+parameters+", 'DD/MM/YYYY HH24:MI')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "CONVERT(VARCHAR,"+parameters+",120) ";
		}
		
		return strFinal;
	}
	
	public static String dateStringDDMMYYYY(String valor){
		String strFinal = "";
		
		if (BD.equals("ORA")) {
			strFinal = "TO_CHAR(" + valor + ", 'DD/MM/YYYY')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "CONVERT(VARCHAR," + valor + ",103) ";
		}
		
		return strFinal;
	}
	
	/**
	 * DateString
	 * 
	 * @param parameters
	 * @return String
	 */
	public static String DateString(String parameters) {
		String strFinal = "";
		
		if (BD.equals("ORA")) {
			strFinal = "TO_DATE('" + parameters + "', 'DD/MM/YYYY')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "convert(date,'" + parameters + "',103) ";
		}
		
		return strFinal;
	}
	
	public static String dateStringDDMMYYYYHH24MISS(String valor) {
		String strFinal = "";
		
		if (valor==null || valor.equals("null"))
			return null; //SALIA ERROR DE SINTAXIS MV
		
		if (BD.equals("ORA")) {
			strFinal = "TO_DATE('" + valor + "', 'DD/MM/YYYY HH24:MI:SS')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "convert(datetime,'" + valor + "',105) "; //SALIA ERROR DE SINTAXIS MV
		}
		
		return strFinal;
	}
	
	/**
	 * Ya debe de venir en formato TO_CHAR
	 * @param valor
	 * @return
	 */
	public static String dateStringDDMMYYYYHH24MISSV2(String valor) {
		String strFinal = "";
		
		if (valor==null || valor.equals("null"))
			return null; 
		
		if (BD.equals("ORA")) {
			strFinal = "TO_DATE(" + valor + ", 'DD/MM/YYYY HH24:MI:SS')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "convert(datetime," + valor + ",105) "; 
		}
		
		return strFinal;
	}
	
	public static String dateString_ConsultaBatch(String valor) {
		String strFinal = "";
		
		if (valor==null || valor.equals("null"))
			return null; //SALIA ERROR DE SINTAXIS MV
		
		if (BD.equals("ORA")) {
			strFinal = "TO_DATE(''" +  valor + "'', ''DD/MM/YYYY HH24:MI:SS'')";
		} else if (BD.equals("MSSQL")) {
			strFinal = "convert(datetime,'" + valor + "',105) "; //SALIA ERROR DE SINTAXIS MV
		}
		
		return strFinal;
	}
	
	/**
	 * Get Convert To Char
	 * 
	 * @return String
	 */
	public static String getConvertToChar(){
		String convert = "";
		
		if (BD.equals("ORA")){
			convert = "TO_CHAR(valor)";
		} else if(BD.equals("MSSQL")){
			convert = "CONVERT(varchar,valor)";	
		}
		
		return convert;
	}
	
	/**
	 * Get Convert To char with format decimal 99.99.
	 * @return String
	 */
	public static String getConvertToCharFormatDecimal() {
		String convert = "";
		
		if(BD.equals("ora")) {
			convert = "TO_CHAR(valor, format)";
		}else if(BD.equals("MSSQL")) {
			convert = "CAST(valor AS DECIMAL(7,2)) AS [varchar]";
		}
		return convert;
	}
	
	/**
	 * Trunc
	 * 
	 * @param valor
	 * @return String
	 */
	public static String trunc(String valor) {
		String funcion = "";
		
		if (BD.equals("ORA")){
			funcion = "TRUNC(valor)";
		} else if(BD.equals("MSSQL")){
			funcion = "cast(valor As Date)";	
		}
		
		funcion = funcion.replace("valor", valor);
		
		return funcion;
	}
	
	/**
	 * Dual Tabla
	 * 
	 * @return String
	 */
	public static String dualTable() {
		String strPripietario = "";
		
		if (BD.equals("ORA")) {
			strPripietario = "FROM DUAL";
		}
		
		return strPripietario;
	}

	public static String getInstr() {
		String strFinal = "";
		
		//concatenador ORACLE
		if (BD.equals("ORA")) {
			strFinal = "INSTR";
		} else if (BD.equals("MSSQL")) {
			strFinal = "CHARINDEX";
		}
		
		return strFinal;
	}
	
	public static String ClobToString(Clob clob) {
   		StringBuffer result = new StringBuffer();
   		if(clob != null) {
   			int read = 0;
   	        Reader reader = null;
   	        char[] buffer = new char[1024];
   	        
   	     try
         {
             reader = new InputStreamReader(clob.getAsciiStream(), StandardCharsets.UTF_8);

             while((read = reader.read(buffer)) != -1) 
             {
                 result.append(buffer, 0, read);
             }
         }
         catch(SQLException ex)
         {
             throw new RuntimeException("Unable to read blob data.", ex);
         }
         catch(IOException ex)
         {
             throw new RuntimeException("Unable to read blob data.", ex);
         }
         finally
         {
             try { if(reader != null) reader.close(); } catch(Exception ex) {}
         }
   		}
   		return result.toString();
   	}
	
	public static Clob stringToClob(String data) {
		try {
			return new javax.sql.rowset.serial.SerialClob(data.toCharArray());
		}catch(Exception e) {
			return null;
		}
	}
	
	public static String  getVersion(){
		
		if (BD.equals("ORA")) {
			return "SELECT version FROM V$INSTANCE";
		} else if (BD.equals("MSSQL")) {
			return "select SERVERPROPERTY ('productversion') version";
		}else
			return null;
	}
}
