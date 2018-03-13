package com.login.model;

public enum UserRank {

    USER("0"),MAKER("1"),FREEZE("2");

    private String code;

    UserRank(String code){
        this.code = code;
    }
    public String code(){
        return this.code;
    }
}
