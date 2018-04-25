package com.model;

import com.common.model.Form;

/**
 * 路线显示表单
 * @author dongjj
 * @date 2018.2.23
 */
public class RouteListForm extends Form {

    private String path;
    private String routename;
    private String makername;
    private Integer routeid;

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getMakername() {
        return makername;
    }

    public void setMakername(String makername) {
        this.makername = makername;
    }
}
