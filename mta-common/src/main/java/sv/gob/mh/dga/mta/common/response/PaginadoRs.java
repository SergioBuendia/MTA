package sv.gob.mh.dga.mta.common.response;

import java.util.List;

public class PaginadoRs {
    private Long total;
    private Integer offSetLeft;
    private Integer offSetRight;
    private List<?> lista;
    private String mensaje;
    private Integer paginas;

    public Long getTotal() {return total; }

    public void setTotal(Long total) {this.total = total; }

    public Integer getOffSetLeft() {return offSetLeft; }

    public void setOffSetLeft(Integer offSetLeft) {this.offSetLeft = offSetLeft; }

    public Integer getOffSetRight() {return offSetRight; }

    public void setOffSetRight(Integer offSetRight) {this.offSetRight = offSetRight; }

    public List<?> getLista() {return lista; }

    public void setLista(List<?> lista) {this.lista = lista; }

    public String getMensaje() {return mensaje; }

    public void setMensaje(String mensaje) {this.mensaje = mensaje; }

    public Integer getPaginas() {return paginas; }

    public void setPaginas(Integer paginas) {this.paginas = paginas; }
}
