package com.login.model;

public enum UserRank {

    USER("1"),MAKER("0"),FREEZE("2");

    private String code;

    UserRank(String code){
        this.code = code;
    }
    public String code(){
        return this.code;
    }
}
