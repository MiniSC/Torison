package back.common.model;

public enum ConfirmStatus {

    PASS("0"),REJECT("1"),WAIT("2");
    private String code;
    ConfirmStatus(String code){
        this.code = code;
    }
    public String code(){
        return this.code;
    }
}
