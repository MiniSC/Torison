package com.model;

import com.common.model.Form;

/**
 * 路线显示表单
 * @author dongjj
 * @date 2018.2.23
 */
public class RouteListForm extends Form {

    private String Path;
    private String introduce;
    private String maker;

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }
}
