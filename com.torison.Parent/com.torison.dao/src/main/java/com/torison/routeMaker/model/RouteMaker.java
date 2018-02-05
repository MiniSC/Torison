package com.torison.routeMaker.model;

public class RouteMaker {
    private Integer userid;

    private String introduce;

    private String senioritypic;

    private String senioritypic2;

    private Byte isagency;

    private Byte seniorityyet;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getSenioritypic() {
        return senioritypic;
    }

    public void setSenioritypic(String senioritypic) {
        this.senioritypic = senioritypic == null ? null : senioritypic.trim();
    }

    public String getSenioritypic2() {
        return senioritypic2;
    }

    public void setSenioritypic2(String senioritypic2) {
        this.senioritypic2 = senioritypic2 == null ? null : senioritypic2.trim();
    }

    public Byte getIsagency() {
        return isagency;
    }

    public void setIsagency(Byte isagency) {
        this.isagency = isagency;
    }

    public Byte getSeniorityyet() {
        return seniorityyet;
    }

    public void setSeniorityyet(Byte seniorityyet) {
        this.seniorityyet = seniorityyet;
    }
}