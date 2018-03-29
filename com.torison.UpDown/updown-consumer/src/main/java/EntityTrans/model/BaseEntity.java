package EntityTrans.model;

import java.io.Serializable;


/**
 * ========================================
 * baseEntity to transform into BytesArray
 * then transform into a String and return
 * @Author MiniSC
 * @GitHub MiniSC
 * @Date 18.3.29
 * @Time 17:34
 * @param <T>
 * ===================================
 */
public abstract class BaseEntity<T> implements Serializable {


    private static final long serialVersionUID = -820014228853045157L;

    /**
     * transform method
     * @param destObj
     * @return
     */
    public String transToBytesString(T destObj) {
       return new String(EntitySerialize.toByteArray(destObj));
    }
}
