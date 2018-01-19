package com.torison.model;

public class nomalUser {
    private Integer userid;

    private String useraccountnum;

    private String userpassword;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUseraccountnum() {
        return useraccountnum;
    }

    public void setUseraccountnum(String useraccountnum) {
        this.useraccountnum = useraccountnum == null ? null : useraccountnum.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }
}