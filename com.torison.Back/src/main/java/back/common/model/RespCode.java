package back.common.model;



public enum  RespCode {
    LOGSUCCESS("登录成功"),WRONG("用户名密码错误"),NONE("用户不存在");
    private String code;
    RespCode(String code){
        this.code = code;
    }
    public String code(){
        return this.code;
    }
}
