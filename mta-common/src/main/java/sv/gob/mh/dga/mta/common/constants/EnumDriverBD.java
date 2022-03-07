package sv.gob.mh.dga.mta.common.constants;

public enum EnumDriverBD {

	ORACLEJDBCORACLEDRIVER("oracle.jdbc.OracleDriver"),
	COMMICROSOFTSQLSERVERJDBCSQLSERVERDRIVER("com.microsoft.sqlserver.jdbc.SQLServerDriver"),
	MYSQL("com.mysql.jdbc.Driver");

	private String nombre;
	
	EnumDriverBD(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
        return nombre;
    }
	
	public String getDB( ) {
		switch (this) {
		    case ORACLEJDBCORACLEDRIVER:
		        return "ORA";
		    case COMMICROSOFTSQLSERVERJDBCSQLSERVERDRIVER:
		        return "MSSQL";
		    default:
		        return "";
		}
	}
	
}