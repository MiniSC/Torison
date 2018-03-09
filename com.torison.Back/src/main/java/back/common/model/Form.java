package back.common.model;

import java.io.Serializable;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 用于接收前端传来的表单数据的基本类
 * BaseForm
 */
public abstract class Form implements Serializable{

    private static final long serialVersionUID = -3948125587165109895L;

    /**
     * Form转换工具，用于实体类的转换
     * @param destObj
     * @param <T>
     * @return
     */
    public <T> T transTo(T destObj){
        copyProperties(this, destObj);
        return destObj;
    }
}
