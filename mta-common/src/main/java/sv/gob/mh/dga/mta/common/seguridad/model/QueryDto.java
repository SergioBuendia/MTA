package sv.gob.mh.dga.mta.common.seguridad.model;

import java.util.List;

import sv.gob.mh.dga.mta.common.sql.Row;

public class QueryDto {

	List<TableColumnTypeMap> metadata;
	List<Row> lista;
	Long total;
	String mensaje;
	int offSetLeft;
	int offSetRight;
	
	public List<TableColumnTypeMap> getMetadata() {
		return metadata;
	}
	public void setMetadata(List<TableColumnTypeMap> metadata) {
		this.metadata = metadata;
	}
	public List<Row> getLista() {
		return lista;
	}
	public void setLista(List<Row> lista) {
		this.lista = lista;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getOffSetLeft() {
		return offSetLeft;
	}
	public void setOffSetLeft(int offSetLeft) {
		this.offSetLeft = offSetLeft;
	}
	public int getOffSetRight() {
		return offSetRight;
	}
	public void setOffSetRight(int offSetRight) {
		this.offSetRight = offSetRight;
	}

    
}

