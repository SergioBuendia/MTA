package sv.gob.mh.dga.mta.common.seguridad.model;

import java.io.Serializable;
import org.apache.commons.beanutils.BeanUtils;

public class EntidadBase implements Serializable{


    public String toString() {
        try {
            return "[" + this.getClass().getName() + ":" + BeanUtils.describe(this).toString() + "]";
        } catch (Exception e) {
            return this.getClass() + ": vacio";
        }
    }

}
