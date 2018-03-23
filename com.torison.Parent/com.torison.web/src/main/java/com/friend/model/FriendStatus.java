package com.friend.model;

public enum FriendStatus {

    APPLY(1),CONFIRM(0);

    private Integer code;
    FriendStatus(Integer code){
        this.code = code;
    }
    public Integer code(){
        return this.code;
    }
}
